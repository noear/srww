package org.noear.srww.uapi.event;

import org.noear.solon.Utils;
import org.noear.solon.cloud.CloudEventHandler;
import org.noear.solon.cloud.model.Event;
import org.noear.solon.logging.utils.TagsMDC;
import org.noear.water.utils.Timecount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 事件基类
 *
 * @author noear
 */
public abstract class EventBase implements CloudEventHandler {
    static Logger log = LoggerFactory.getLogger(EventBase.class);

    @Override
    public boolean handler(Event event) throws Throwable {
        TagsMDC.tag0("event");
        TagsMDC.tag1(event.topic());

        if (Utils.isNotEmpty(event.tags())) {
            TagsMDC.tag2(event.tags());
        }

        TagsMDC.tag3(event.key());
        Timecount timecount = new Timecount().start();

        try {
            boolean succeeded = exec(event);
            long timespan = timecount.stop().milliseconds();

            if (succeeded) {
                log.info("Event execution succeeded @{}ms", timespan);
                return true;
            } else {
                log.warn("Event execution failed @{}ms", timespan);
                return false;
            }
        } catch (Throwable e) {
            long timespan = timecount.stop().milliseconds();
            log.error("Event execution error @{}ms: {}", timespan, e);
            throw e;
        }
    }

    protected abstract boolean exec(Event event) throws Throwable;
}
