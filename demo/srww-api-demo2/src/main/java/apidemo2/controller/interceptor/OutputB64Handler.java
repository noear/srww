package apidemo2.controller.interceptor;

import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author noear 2021/2/11 created
 */
public class OutputB64Handler implements Handler {
    @Override
    public void handle(Context c) throws Throwable {
        if (c.result != null) {
            String json = (String) c.result;
            String json_b64 = Base64.getEncoder().encodeToString(json.getBytes(StandardCharsets.UTF_8));

            c.output(json_b64);
        }
    }
}
