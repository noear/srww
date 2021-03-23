package apidemo2;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.noear.snack.ONode;
import org.noear.solon.extend.sessionstate.jwt.JwtUtils;
import org.noear.solon.test.HttpTestBase;
import org.noear.solon.test.KvMap;
import org.noear.solon.test.SolonJUnit4ClassRunner;
import org.noear.solon.test.SolonTest;
import org.noear.srww.uapi.common.Attrs;
import org.noear.water.utils.EncryptUtils;

import java.util.Date;
import java.util.Map;

/**
 * @author noear 2021/2/11 created
 */
@RunWith(SolonJUnit4ClassRunner.class)
@SolonTest(App.class)
public class ApiTest3x extends HttpTestBase {
    public static final int app_id = 4;
    public static final String app_secret = "ZVJ4swhbNUV3Uc36";
    public static final int client_ver_id = 10001; //1.0.1

    public ONode call(String apiName, Map<String, Object> args) throws Exception {

        String json0 = ONode.stringify(args);
        String json_encoded0 = EncryptUtils.aesEncrypt(json0, app_secret);

        //生成领牌
        Claims claims = new DefaultClaims();
        claims.put("user_id", 1);
        claims.setExpiration(new Date(System.currentTimeMillis() + 60 * 2 * 1000));
        String token = JwtUtils.buildJwt(claims, 0);

        //生成签名
        long timestamp = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append(apiName)
                .append("#")
                .append(client_ver_id)
                .append("#")
                .append(json_encoded0)
                .append("#")
                .append(app_secret)
                .append("#")
                .append(timestamp);
        String sign = String.format("%d.%d.%s.%d", app_id, client_ver_id, EncryptUtils.md5(sb.toString()), timestamp);

        //请求
        String json_encoded2 = path("/api/v2/app/" + apiName)
                .header("Content-type","application/json")
                .header(Attrs.h_token, token)
                .header(Attrs.h_sign, sign)
                .bodyTxt(json_encoded0)
                .post();

        String json = EncryptUtils.aesDecrypt(json_encoded2, app_secret);

        System.out.println("Decoded: " + json);

        return ONode.loadStr(json);
    }

    public ONode call(String method, String args) throws Exception {
        return call(method, (Map<String, Object>) ONode.loadStr(args).toData());
    }


    @Test
    public void config_set() throws Exception {
        //ONode node = call("config.set", new KvMap().set("tag", "demo").set("key","test").set("value","test"));
        ONode node = call("config.set", "{tag:'demo',key:'test',value:'test',map:{k1:1,k2:2}}");

        assert node.get("code").getInt() == 200;
    }

    @Test
    public void config_get() throws Exception {
        ONode node = call("config.get", new KvMap().set("tag", "demo"));

        assert node.get("code").getInt() == 200;
        assert node.get("data").count() > 0;
    }

    @Test
    public void login_test() throws Exception {
        ONode node = call("login.test", "{tag:'demo'}");

        assert node.get("code").getInt() == 200;
    }
}
