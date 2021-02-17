package org.noear.srww.uapi.interceptor;

import org.noear.srww.uapi.Uapi;
import org.noear.srww.uapi.encoder.DefEncoder;
import org.noear.snack.ONode;

import org.noear.srww.uapi.encoder.Encoder;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;

/** 输出拦截器（用于内容格式化并输出） */
public class OutputBuildInterceptor implements Handler {

    Encoder _encoder;

    public OutputBuildInterceptor() {
        _encoder = new DefEncoder();
    }

    public OutputBuildInterceptor(Encoder encoder) {
        if (encoder == null) {
            _encoder = new DefEncoder();
        } else {
            _encoder = encoder;
        }
    }

    @Override
    public void handle(Context ctx) throws Throwable {
        if (ctx.result == null) {
            return;
        }

        Object result = ctx.result;
        String output = null;

        if (result instanceof ONode) {
            output = ((ONode) result).toJson();
        } else {
            output = ONode.stringify(result);
        }

        ctx.result = output;

        Uapi uapi = (Uapi) ctx.controller();

        ctx.attrSet(Attrs.output, _encoder.tryEncode(ctx, uapi.getApp(), output));
    }
}
