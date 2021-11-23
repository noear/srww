package apidemo2.controller.event;

import lombok.extern.slf4j.Slf4j;
import org.noear.solon.cloud.annotation.CloudEvent;
import org.noear.solon.cloud.model.Event;
import org.noear.srww.uapi.event.EventBase;

//接收消息
@Slf4j
@CloudEvent("user.created")
public class MSG_user_created extends EventBase {
    @Override
    protected boolean exec(Event event) throws Throwable {
        log.info("有个用户创建了...");

// 发送消息
//        CloudClient.event().publish(
//                new Event("user.created", "test")
//                        .tags("demo"));

        return true;
    }
}
