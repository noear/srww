package org.noear.srww.base.handler;

import org.noear.snack.ONode;
import org.noear.solon.cloud.model.Instance;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.water.WaterClient;
import org.noear.water.utils.FromUtils;
import org.noear.water.utils.Timecount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 结束计时拦截器（完成计时，并发送到WATER）
 * */
public class BaseEndHandler implements Handler {
    static Logger log = LoggerFactory.getLogger(BaseEndHandler.class);

    private String _tag;

    public BaseEndHandler(String tag) {
        _tag = tag;
    }

    @Override
    public void handle(Context ctx) throws Throwable {

        log.info("> Handler: {}\n> Body: {}",
                ONode.stringify(ctx.headerMap()),
                ONode.stringify(ctx.paramMap()));


        /** 获取一下计时器（开始计时的时候设置的） */
        Timecount timecount = ctx.attr("timecount", null);

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
