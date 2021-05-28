package org.noear.srww.base.handler;

import org.noear.solon.Solon;
import org.noear.solon.cloud.CloudClient;
import org.noear.solon.cloud.model.Instance;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.water.WW;
import org.noear.water.utils.Timecount;

/**
 * 结束计时拦截器（完成计时，并发送到WATER）
 * */
public class BaseEndHandler implements Handler {
    private String _tag;

    public BaseEndHandler(String tag) {
        _tag = tag;
    }

    public BaseEndHandler() {
        this("api");
    }

    @Override
    public void handle(Context ctx) throws Throwable {
        /** 获取一下计时器（开始计时的时候设置的） */
        Timecount timecount = ctx.attr("timecount", null);

        if (timecount == null) {
            return;
        }

        String path = ctx.pathNew();

        String service = Solon.cfg().appName();


        long milliseconds = timecount.stop().milliseconds();
        String _from = CloudClient.trace().getFromId();
        String _node = Instance.local().serviceAndAddress();


        CloudClient.metric().addMeter(service, _tag, path, milliseconds);
        CloudClient.metric().addMeter(WW.track_service, service, _node, milliseconds);
        CloudClient.metric().addMeter(WW.track_from, service, _from, milliseconds);

//        WaterClient.Track.track(service, _tag, path, timecount.stop().milliseconds(), node, _from);
    }
}
