package org.noear.srww.base.handler;


import org.noear.solon.Solon;
import org.noear.solon.cloud.CloudClient;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;

/**
 * @author noear 2021/5/19 created
 */
public class BaseIpHandler implements Handler {
    @Override
    public void handle(Context ctx) throws Throwable {
        if (Solon.cfg().isWhiteMode()) {
            String ip = ctx.realIp();

            if (CloudClient.list().inListOfIp("server", ip) == false) {
                ctx.setHandled(true);
                ctx.statusSet(403);
                ctx.output(ip + ", not whitelit!");
            }
        }
    }
}
