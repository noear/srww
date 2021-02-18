package apidemo1.dso;


import apidemo1.App;
import org.noear.mlog.utils.Tags;
import org.noear.solon.annotation.Component;
import org.noear.solon.cloud.CloudLogger;
import org.noear.solon.core.event.EventListener;
import org.noear.solon.core.handle.Context;

/**
 * 全局未控制的异常监听
 *
 * @author noear 2021/2/17 created
 */
@Component
public class ErrorListener implements EventListener<Throwable> {
    CloudLogger logger = CloudLogger.get(App.class);

    @Override
    public void onEvent(Throwable err) {
        Context ctx = Context.current();

        if (ctx == null) {
            logger.error(err);
        } else {
            logger.error(Tags.tag0(ctx.path()), err);
        }
    }
}