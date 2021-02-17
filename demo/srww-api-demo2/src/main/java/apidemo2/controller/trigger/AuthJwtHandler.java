package apidemo2.controller.trigger;

import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.srww.uapi.UapiCodes;

/**
 * @author noear 2021/2/11 created
 */
public class AuthJwtHandler implements Handler {
    @Override
    public void handle(Context c) throws Throwable {
        if (c.header("JWT") == null) {
            throw UapiCodes.CODE_4001012;
        }
    }
}
