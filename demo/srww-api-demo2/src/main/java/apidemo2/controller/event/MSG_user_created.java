package apidemo2.controller.event;

import lombok.extern.slf4j.Slf4j;
import org.noear.solon.cloud.CloudClient;
import org.noear.solon.cloud.CloudEventHandler;
import org.noear.solon.cloud.annotation.CloudEvent;
import org.noear.solon.cloud.model.Event;
import org.noear.water.utils.Datetime;

/**
 * @author noear 2021/3/12 created
 */
@Slf4j
@CloudEvent("user.created")
public class MSG_user_created implements CloudEventHandler {
    @Override
    public boolean handler(Event event) throws Throwable {
        log.info("有个用户创建了...");

//        Datetime datetime = Datetime.Now();
//        datetime.addDay(1);
//
//        CloudClient.event().publish(
//                new Event("user.created", "test")
//                        .tags("demo")
//                        .scheduled(datetime.getFulltime()));

        return true;
    }
}
