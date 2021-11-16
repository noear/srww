package org.noear.srww.uapi.handler;

import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;

/**
 * 旧的公共参数兼容处理，放在 ParamsNeedCheckHandler 之前
 */
public class ParamsOldCompatibleHandler implements Handler {
    @Override
    public void handle(Context ctx) throws Throwable {
        ctx.paramMap().put("g_lang", ctx.header("Lang"));
        ctx.paramMap().put("g_deviceId", ctx.header("ClientId"));
    }
}
