package apidemo2.controller.interceptor;

import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.srww.uapi.UapiParams;
import org.noear.srww.uapi.interceptor.Attrs;
import apidemo2.controller.UapiBase;
import apidemo2.dso.Logger;

public class LogInterceptor implements Handler {

    @Override
    public void handle(Context ctx) throws Exception {
        UapiBase api = UapiBase.current();

        UapiParams params = api.params();
        Throwable error = api.error();

        String output = ctx.attr(Attrs.output);

        if (null != output) {
            Logger.logOutput(api, params, output);
        }

        if (null != error) {
            Logger.logError(api, params, error);
        }
    }
}
