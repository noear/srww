package org.noear.srww.base.handler;

import org.noear.solon.cloud.CloudClient;
import org.noear.solon.cloud.model.Instance;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.water.WaterClient;
import org.noear.water.utils.Timecount;

/**
 * 结束计时拦截器（完成计时，并发送到WATER）
 * */
public class BaseEndHandler implements Handler {
    private String _tag;

    public BaseEndHandler(String tag) {
        _tag = tag;
    }

    public BaseEndHandler(){
        this("api");
    }

    @Override
    public void handle(Context ctx) throws Throwable {
        /** 获取一下计时器（开始计时的时候设置的） */
        Timecount timecount = ctx.attr("timecount", null);

        if (timecount == null) {
            return;
        }

        String _from = CloudClient.trace().getFromId();

        String service = Instance.local().service();
        String path = ctx.pathNew();

        String node = Instance.local().address();

        WaterClient.Track.track(service, _tag, path, timecount.stop().milliseconds(), node, _from);
    }
}
