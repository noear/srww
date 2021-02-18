package org.noear.srww.uapi.interceptor;

import org.noear.srww.uapi.Uapi;
import org.noear.srww.uapi.encoder.DefEncoder;
import org.noear.srww.uapi.encoder.Encoder;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;

/** 输出签名拦截器（用于输出内容的签名） */
public class OutputSignInterceptor implements Handler {
    private Encoder _encoder;

    public OutputSignInterceptor(Encoder encoder) {
        if (encoder == null) {
            _encoder = new DefEncoder();
        } else {
            _encoder = encoder;
        }
    }

    @Override
    public void handle(Context ctx) throws Throwable {
        /** 获取参数 */
        Uapi uapi = (Uapi) ctx.controller();

        if (uapi == null) {
            return;
        }

        String orgOutput = uapi.getOrgOutput();

        if (orgOutput != null) {
            String x_sign = _encoder.tryEncode(ctx, uapi.getApp(), orgOutput);
            ctx.headerSet(Attrs.x_sign, x_sign);
        }
    }
}
