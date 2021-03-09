package org.noear.srww.uapi.handler;

import org.noear.solon.cloud.model.Instance;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.srww.uapi.common.Attrs;
import org.noear.water.WaterClient;
import org.noear.water.utils.FromUtils;
import org.noear.water.utils.Timecount;

/**
 * 结束计时拦截器（完成计时，并发送到WATER）
 * */
public class EndHandler implements Handler {
    private String _tag;
    public EndHandler(String tag){
        _tag = tag;
    }

    @Override
    public void handle(Context ctx) throws Throwable {
        /** 获取一下计时器（开始计时的时候设置的） */
        Timecount timecount = ctx.attr(Attrs.timecount, null);

        if (timecount == null) {
            return;
        }

        String _from = FromUtils.getFrom(ctx);

        String service = Instance.local().service();
        String path = ctx.pathNew();
        String node = Instance.local().address();

        WaterClient.Track.track(service, _tag, path, timecount.stop().milliseconds(), node, _from);
    }
}