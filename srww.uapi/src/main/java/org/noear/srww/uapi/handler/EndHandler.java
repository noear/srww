package org.noear.srww.uapi.handler;

import org.noear.solon.cloud.CloudClient;
import org.noear.solon.cloud.model.Instance;
import org.noear.solon.core.handle.Action;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.srww.uapi.common.Attrs;
import org.noear.water.WaterClient;
import org.noear.water.utils.Timecount;

/**
 * 结束计时拦截器（完成计时，并发送到WATER）
 * */
public class EndHandler implements Handler {
    private String _tag;
    private boolean _usePath = false;

    public EndHandler(String tag) {
        _tag = tag;
    }

    public EndHandler(String tag, boolean usePath) {
        _tag = tag;
        _usePath = usePath;
    }

    @Override
    public void handle(Context ctx) throws Throwable {
        /** 获取一下计时器（开始计时的时候设置的） */
        Timecount timecount = ctx.attr(Attrs.timecount, null);

        if (timecount == null) {
            return;
        }


        String _from = CloudClient.trace().getFromId();

        String service = Instance.local().service();
        String path = null;

        if(_usePath){
            path = ctx.pathNew();
        }else {
            Action action = ctx.action();

            if (action != null) {
                path = action.name();
            }

            if (path == null) {
                path = ctx.pathNew();
            }
        }

        String node = Instance.local().address();

        WaterClient.Track.track(service, _tag, path, timecount.stop().milliseconds(), node, _from);
    }
}
