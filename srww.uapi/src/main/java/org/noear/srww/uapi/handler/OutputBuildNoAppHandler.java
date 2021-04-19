package org.noear.srww.uapi.handler;

import org.noear.snack.ONode;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.srww.uapi.Uapi;
import org.noear.srww.uapi.common.Attrs;
import org.noear.srww.uapi.encoder.DefEncoder;
import org.noear.srww.uapi.encoder.Encoder;

/**
 * 输出拦截器（用于内容格式化并输出；没有通道支持）
 * */
public class OutputBuildNoAppHandler implements Handler {

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

        ctx.attrSet(Attrs.org_output, output);
        ctx.attrSet(Attrs.output, output);
    }
}
