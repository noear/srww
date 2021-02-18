package org.noear.srww.uapi.interceptor;

import org.noear.mlog.Logger;
import org.noear.mlog.LoggerFactory;
import org.noear.mlog.utils.Tags;
import org.noear.snack.ONode;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.srww.uapi.Uapi;

public class LogInterceptor implements Handler {
    Logger logger;

    public LogInterceptor() {
        logger = LoggerFactory.get(LogInterceptor.class);
    }

    public LogInterceptor(String loggerName) {
        logger = LoggerFactory.get(loggerName);
    }

    @Override
    public void handle(Context ctx) throws Exception {
        Uapi api = (Uapi) ctx.controller();

        String orgOutput = api.getOrgOutput();

        if (null != orgOutput) {
            logOutput(api, orgOutput);
        }

        if (null != ctx.errors) {
            logError(api, ctx.errors);
        }
    }

    /**
     * 日志拦截器中使用
     *
     * @param uapi
     * @param orgOutput
     */
    protected void logOutput(Uapi uapi, String orgOutput) {
        if (orgOutput == null) {
            return;
        }

        String orgInput = uapi.getOrgInput();
        if (null == orgInput) {
            orgInput = ONode.stringify(uapi.context().paramMap());
        }
        if (orgInput.length() > 900) {
            orgInput = orgInput.substring(0, 900);
        }


        int verId = uapi.getVerId();
        long userId = uapi.getUserID();

        logger.info(
                Tags.tag0(uapi.name()).tag1(String.valueOf(userId)).tag2(String.valueOf(verId)),
                "{}\n\n{}",
                orgInput,
                orgOutput
        );
    }

    /**
     * 日志拦截器中使用
     *
     * @param uapi
     * @param err
     */
    protected void logError(Uapi uapi, Throwable err) {
        if (err == null) {
            return;
        }

        String orgInput = uapi.getOrgInput();
        if (null == orgInput) {
            orgInput = ONode.stringify(uapi.context().paramMap());
        }
        if (orgInput.length() > 900) {
            orgInput = orgInput.substring(0, 900);
        }

        int verId = uapi.getVerId();
        long userId = uapi.getUserID();

        logger.error(
                Tags.tag0(uapi.name()).tag1(String.valueOf(userId)).tag2(String.valueOf(verId)),
                "{}\n\n{}",
                orgInput,
                err
        );
    }
}
