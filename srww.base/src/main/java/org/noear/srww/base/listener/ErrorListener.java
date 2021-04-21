package org.noear.srww.base.listener;


import org.noear.snack.ONode;
import org.noear.solon.annotation.Component;
import org.noear.solon.core.event.EventListener;
import org.noear.solon.core.handle.Context;
import org.noear.solon.logging.utils.TagsMDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 全局未处理异常监听
 *
 * @author noear 2021/4/21 created
 */
public class ErrorListener implements EventListener<Throwable> {
    static final Logger log = LoggerFactory.getLogger(ErrorListener.class);

    @Override
    public void onEvent(Throwable error) {
        Context ctx = Context.current();

        TagsMDC.tag0("global");

        if (ctx == null) {
            log.error("< Error: {}", error);
        } else {
            TagsMDC.tag1(ctx.path());
            log.error("> Header: {}\n> Param: {}\n\n< Error: {}", ONode.stringify(ctx.headerMap()), ONode.stringify(ctx.paramMap()), error);
        }
    }
}
