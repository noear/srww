package apidemo0;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.noear.snack.ONode;
import org.noear.solon.test.HttpTestBase;
import org.noear.solon.test.SolonJUnit4ClassRunner;
import org.noear.solon.test.SolonTest;
import org.noear.srww.uapi.common.Attrs;

import java.util.Map;

/**
 * @author noear 2021/2/11 created
 */
@RunWith(SolonJUnit4ClassRunner.class)
@SolonTest(App.class)
public class ApiTest extends HttpTestBase {
    public static final int app_id = 2;

    public ONode call(String method, Map<String, Object> args) throws Exception {

        args.put(Attrs.app_id, app_id);

        String json = path("/api/v1/" + method)
                .data(args)
                .post();

        return ONode.loadStr(json);
    }

    public ONode call(String method, String args) throws Exception {
        return call(method, (Map<String, Object>) ONode.loadStr(args).toData());
    }

    @Test
    public void A_0_1() throws Exception {
//        ONode node = call("config.set", new KvMap().set("tag", "demo").set("key","test").set("value","test"));
        ONode node = call("A.0.1", "{tag:'demo',key:'test',value:'test'}");
        assert node.get("code").getInt() == 200;
    }
}
