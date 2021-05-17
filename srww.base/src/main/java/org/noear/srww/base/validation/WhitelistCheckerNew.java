package org.noear.srww.base.validation;

import org.noear.solon.core.handle.Context;
import org.noear.solon.extend.validation.annotation.Whitelist;
import org.noear.solon.extend.validation.annotation.WhitelistChecker;
import org.noear.water.WaterClient;

public class WhitelistCheckerNew implements WhitelistChecker {
    @Override
    public boolean check(Whitelist anno, Context ctx) {
        String ip = ctx.realIp();

        return WaterClient.Whitelist.existsOfServerIp(ip);
    }
}
