package apidemo2.controller.trigger;

import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;

/**
 * @author noear 2021/2/10 created
 */
public class StartHandler implements Handler {
    @Override
    public void handle(Context c) throws Throwable {
        c.attrSet("_start", System.currentTimeMillis());
    }
}
