package org.noear.srww.uapi.handler;

import org.noear.rock.model.AppModel;
import org.noear.srww.uapi.Uapi;
import org.noear.srww.uapi.UapiCodes;
import org.noear.srww.uapi.encoder.DefEncoder;
import org.noear.snack.ONode;

import org.noear.srww.uapi.encoder.Encoder;
import org.noear.srww.uapi.common.Attrs;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 输出拦截器（用于内容格式化并输出）
 * */
public class OutputBuildHandler implements Handler {
    static final Logger log = LoggerFactory.getLogger(OutputBuildHandler.class);

    Encoder _encoder;

    public OutputBuildHandler() {
        _encoder = new DefEncoder();
    }

    public OutputBuildHandler(Encoder encoder) {
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

        ctx.attrSet(Attrs.org_output, output);

        if (uapi.getAppId() > 0) {
            AppModel app = uapi.getApp();
            if (app.app_id == uapi.getAppId()) {
                ctx.attrSet(Attrs.output, _encoder.tryEncode(ctx, app, output));
                return;
            }
        }

        //用于记录日志，不能去掉（如果前后没成，保个底）
        ctx.attrSet(Attrs.output, output);
        ctx.statusSet(400);

    }
}
