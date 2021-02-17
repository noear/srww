package apidemo3.dso;

import org.noear.snack.ONode;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.noear.water.utils.EncryptUtils;
import apidemo3.dso.db.CoProductService;
import apidemo3.model.CoProductDo;
import org.noear.water.utils.HttpUtils;

import java.util.*;

@Component
public class StandardApi {

    @Inject
    CoProductService coProductService;


    /**
     * 获取用户状态
     *
     * @param userId
     * @param mobile
     * @return
     */
    public ONode getUserStatus(long userId, String mobile) {

        ONode oNode = new ONode().asObject();
        oNode.set("mobile", mobile);

        return standardPost(userId, "O.A.0.1", oNode.toJson());
    }

    /**
     * 提交数据给合作方(进件)
     *
     * @param userId
     * @param lmNo
     * @param mobile
     * @return
     */
    public ONode submitUserInfo(long userId, String lmNo, String mobile, ONode info) {
        ONode oNode = new ONode().asObject();
        oNode.set("lmNo", lmNo);
        oNode.set("mobile", mobile);

        return new ONode().set("code", 1).set("data", new ONode().set("outNo", "TestOutNo001"));
    }

    /**
     * 获取订单详情
     *
     * @param outNo
     * @return
     */
    public ONode getOrderDetail(long userId, String outNo) {
        ONode oNode = new ONode().asObject();
        oNode.set("outNo", outNo);
        return standardPost(userId, "O.A.0.3", oNode.toJson());
    }

    public ONode standardPost(long userId, String cmd, String json) {

        try {
            ONode oNode = doPost("", cmd, json);

            Logger.logPartyOutput(C_ID + "", cmd, userId + "", json, oNode.toJson());

            return oNode;
        } catch (Exception e) {
            Logger.logPartyError(C_ID + "", cmd, userId + "", json, e);
        }

        return new ONode().set("code", 0).set("msg", "合作方接口错误");
    }

    private static final int C_ID = 52;
    private static final String V_ID = "100";

    private Map<String, String> buildQueryParam(String cmd, String json) throws Exception {

        json = ONode.loadStr(json).toJson();

        CoProductDo model = coProductService.get_co_product(C_ID);

        Map<String, String> query = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        sb.append(cmd).append("#").append(json).append("#").append(model.bull_product_key);
        String sign = EncryptUtils.sha256(sb.toString());
        String p64 = EncryptUtils.aesEncrypt(json, model.bull_product_key, null);
        query.put("p", p64);
        query.put("k", C_ID + "." + V_ID + "." + sign);
        return query;
    }

    /**
     * 请求合作方接口
     *
     * @param url
     * @param cmd
     * @param json
     * @return
     * @throws Exception
     */
    public ONode doPost(String url, String cmd, String json) throws Exception {

        StandardApi standardApi = new StandardApi();

        Map<String, String> map = standardApi.buildQueryParam(cmd, json);
        String result = HttpUtils.postString(url, map);

        return ONode.load(result);
    }

}
