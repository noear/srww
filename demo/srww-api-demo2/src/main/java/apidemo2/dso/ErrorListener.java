package apidemo2.dso;


import apidemo2.App;
import org.noear.solon.annotation.Component;
import org.noear.solon.core.event.EventListener;
import org.noear.solon.core.handle.Context;
import org.noear.solon.logging.utils.TagsMDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 全局未控制的异常监听
 *
 * @author noear 2021/2/17 created
 */
@Component
public class ErrorListener implements EventListener<Throwable> {
    Logger logger = LoggerFactory.getLogger(App.class);

    @Override
    public void onEvent(Throwable err) {
        Context ctx = Context.current();

        if (ctx != null) {
            TagsMDC.tag0(ctx.path());
        }

        logger.error("", err);
    }
}
