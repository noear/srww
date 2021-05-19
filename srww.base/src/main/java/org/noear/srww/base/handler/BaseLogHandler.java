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
            if (ctx.result instanceof String) {
                sb.append("< Body: ").append((String) ctx.result);
            } else {
                sb.append("< Body: ").append(ONode.stringify(ctx.result));
            }
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
