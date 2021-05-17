package org.noear.srww.uapi.handler;

import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.srww.uapi.UapiCodes;
import org.noear.water.WaterClient;

/**
 * 白名单拦截器
 * */
public class WhitelistHandler implements Handler {

    @Override
    public void handle(Context ctx) throws Exception {

        String ip = ctx.realIp();

        if (!WaterClient.Whitelist.existsOfServerIp(ip)) {
            throw UapiCodes.CODE_4001016;
        }
    }
}
