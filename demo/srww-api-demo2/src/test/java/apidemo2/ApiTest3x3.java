package apidemo2;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
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
@Slf4j
@RunWith(SolonJUnit4ClassRunner.class)
@SolonTest(App.class)
public class ApiTest3x3 extends HttpTestBase {
    public static final int app_id = 12;
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
        Response response = path("/api/v2/app/" + apiName)
                .header("Content-type","application/json")
                .header(Attrs.h_token, token)
                .header(Attrs.h_sign, sign)
                .bodyTxt(json_encoded0)
                .exec("POST");


        String sign2 = response.header(Attrs.h_sign);
        String token2 = response.header(Attrs.h_token);

        log.debug(token2);

        String json_encoded2 = response.body().string();

        String sign22 = EncryptUtils.md5(apiName+"#"+json_encoded2+"#"+app_secret);

        if(sign2.equals(sign22)==false){
            throw new RuntimeException("签名对不上，数据被串改了");
        }

        String json = EncryptUtils.aesDecrypt(json_encoded2, app_secret);

        System.out.println("Decoded: " + json);

        return ONode.loadStr(json);
    }

    public ONode call(String method, String args) throws Exception {
        return call(method, (Map<String, Object>) ONode.loadStr(args).toData());
    }


    @Test
    public void login_test() throws Exception {
        ONode node = call("login.test", "{tag:'demo'}");

        assert node.get("code").getInt() == 200;
    }
}
