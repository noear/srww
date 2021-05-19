package org.noear.srww.base.handler;

import org.noear.snack.ONode;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.solon.logging.utils.TagsMDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author noear 2021/4/9 created
 */
public class BaseLogErrorHandler implements Handler {
    static Logger log = LoggerFactory.getLogger(BaseLogErrorHandler.class);

    @Override
    public void handle(Context ctx) throws Throwable {
        TagsMDC.tag0(ctx.path());

        if (ctx.errors != null) {
            log.error("> Header: {}\n> Param: {}\n\n< Error: {}",
                    ONode.stringify(ctx.headerMap()),
                    ONode.stringify(ctx.paramMap()),
                    ctx.errors);
        }
    }
}
