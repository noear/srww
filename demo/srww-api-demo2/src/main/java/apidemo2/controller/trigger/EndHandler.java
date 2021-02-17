package apidemo2.controller.trigger;

import org.noear.mlog.Logger;
import org.noear.mlog.LoggerFactory;
import org.noear.mlog.utils.Tags;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;

/**
 * @author noear 2021/2/10 created
 */
public class EndHandler implements Handler {
    Logger logger = LoggerFactory.get(EndHandler.class);

    @Override
    public void handle(Context c) throws Throwable {
        long _start = c.attr("_start", 0L);
        long _timespan = System.currentTimeMillis() - _start;

        //记录接口用时


        //记录接口输入输出日志
        if (c.result != null) {
            logger.info(Tags.tag0(c.pathNew()), "\ni:: {}\no:: {}", c.paramMap(), c.result);
        }

        //记录异常
        if (c.errors != null) {
            logger.error(Tags.tag0(c.pathNew()), "\ni:: {}\no:: {}", c.paramMap(), c.errors);
        }
    }
}
