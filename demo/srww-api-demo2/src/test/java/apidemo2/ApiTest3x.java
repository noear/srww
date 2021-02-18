package apidemo2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.noear.snack.ONode;
import org.noear.solon.test.HttpTestBase;
import org.noear.solon.test.KvMap;
import org.noear.solon.test.SolonJUnit4ClassRunner;
import org.noear.solon.test.SolonTest;
import org.noear.srww.uapi.interceptor.Attrs;
import org.noear.water.utils.EncryptUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

/**
 * @author noear 2021/2/11 created
 */
@RunWith(SolonJUnit4ClassRunner.class)
@SolonTest(App.class)
public class ApiTest3x extends HttpTestBase {
    public static final int app_id = 2;
    public static final String app_sign_secret = "djjePPbqBz35U328";

    public ONode call(String method, Map<String, Object> args) throws Exception {

        String json0 = ONode.stringify(args);
        String json_b640 = EncryptUtils.aesEncrypt(json0, app_sign_secret);

        String authorization = String.format("%d.1.%s", app_id, EncryptUtils.md5(json_b640));

        String josn_b64 = path("/api/v2/app/" + method)
                .header(Attrs.h_token, "noear")
                .header(Attrs.h_authorization, authorization)
                .bodyTxt(json_b640)
                .post();

        String json = EncryptUtils.aesDecrypt(josn_b64, app_sign_secret);

        System.out.println("Decoded: " + json);

        return ONode.loadStr(json);
    }

    public ONode call(String method, String args) throws Exception {
        return call(method, (Map<String, Object>) ONode.loadStr(args).toData());
    }


    @Test
    public void config_set() throws Exception {
        ONode node = call("config.set", new KvMap().set("tag", "demo").set("key","test").set("value","test"));

        assert node.get("code").getInt() == 200;
    }

    @Test
    public void config_get() throws Exception {
        ONode node = call("config.get", new KvMap().set("tag", "demo"));

        assert node.get("code").getInt() == 200;
        assert node.get("data").count() > 0;
    }
}
