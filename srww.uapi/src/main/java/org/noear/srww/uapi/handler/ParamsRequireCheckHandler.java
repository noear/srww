package org.noear.srww.uapi.handler;

import org.noear.solon.Utils;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.srww.uapi.UapiCodes;

/**
 * 参数必要性较验拦截器
 */
public class ParamsRequireCheckHandler implements Handler {
    private String[] paramNames;

    public ParamsRequireCheckHandler(String... names) {
        paramNames = names;
    }

    @Override
    public void handle(Context ctx) throws Throwable {
        for (String name : paramNames) {
            if (Utils.isEmpty(ctx.param(name))) {
                throw UapiCodes.CODE_4001014(name);
            }
        }
    }
}
