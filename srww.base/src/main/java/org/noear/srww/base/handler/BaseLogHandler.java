package org.noear.srww.base.handler;

import org.noear.snack.ONode;
import org.noear.solon.Utils;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.solon.logging.utils.TagsMDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author noear 2021/4/9 created
 */
public class BaseLogHandler implements Handler {
    static Logger log = LoggerFactory.getLogger(BaseLogHandler.class);

    @Override
    public void handle(Context ctx) throws Throwable {
        TagsMDC.tag0(ctx.path());

        StringBuilder sb = new StringBuilder(1000);

        sb.append("> Header: ").append(ONode.stringify(ctx.headerMap())).append("\n");
        sb.append("> Param: ").append(ONode.stringify(ctx.paramMap())).append("\n");
        sb.append("\n");

        if (ctx.result != null) {
            String output = ctx.attr("output");
            if (output == null) {
                if (ctx.result instanceof String) {
                    output = (String) ctx.result;
                } else {
                    output = ONode.stringify(ctx.result);
                }
            }

            sb.append("< Body: ").append(output);
            sb.append("\n");
        }


        if (ctx.errors != null) {
            sb.append("< Error: ").append(Utils.throwableToString(ctx.errors));
            log.error(sb.toString());
        } else {
            log.info(sb.toString());
        }
    }
}
