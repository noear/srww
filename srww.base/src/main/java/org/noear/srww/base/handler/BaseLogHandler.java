package org.noear.srww.base.handler;

import org.noear.snack.ONode;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author noear 2021/4/9 created
 */
public class BaseLogHandler implements Handler {
    static Logger log = LoggerFactory.getLogger(BaseEndHandler.class);

    @Override
    public void handle(Context ctx) throws Throwable {
        if (ctx.errors == null) {
            log.info("> Handler: {}\n> Body: {}",
                    ONode.stringify(ctx.headerMap()),
                    ONode.stringify(ctx.paramMap()));
        } else {
            log.info("> Handler: {}\n> Body: {}\r\n{}",
                    ONode.stringify(ctx.headerMap()),
                    ONode.stringify(ctx.paramMap()),
                    ctx.errors);
        }
    }
}
