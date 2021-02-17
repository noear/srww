package apidemo3.controller.cmds;

import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.extend.validation.annotation.NotNull;
import apidemo3.controller.SysCodes;
import apidemo3.controller.UapiBase;
import apidemo3.dso.db.mapper.CoProductMapper;
import apidemo3.model.CoProductDo;

import java.util.Map;

@Component(tag = "cmd")
public class CMD_A_A_0_1 extends UapiBase {

    @Inject
    CoProductMapper productService;

    @NotNull({"pId"})
    @Mapping("A.A.0.1")
    public Map<String,Object> exec(int pId) throws Exception {

        // 判断用户是否登录
        if (!isLogin()) {
            throw SysCodes.CODE_102;
        }

        CoProductDo model = productService.get_co_product(pId);

        data.put("logo", model.logo);
        data.put("name", model.product_name);
        data.put("intro", model.bull_product_intro);
        data.put("amount", "₹" + model.loan_min + " - ₹" + model.loan_max);
        data.put("interest", model.loan_interest + "%");
        data.put("tenure", model.loan_limit_time);
        data.put("putDate", model.loan_giventime);
        data.put("conditions", model.loan_qualification);

        return data;
    }
}
