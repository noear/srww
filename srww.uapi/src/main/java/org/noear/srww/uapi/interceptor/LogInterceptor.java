package org.noear.srww.uapi.interceptor;

import org.noear.snack.ONode;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.solon.logging.utils.TagsMDC;
import org.noear.srww.uapi.Uapi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志拦截器
 * */
public class LogInterceptor implements Handler {
    Logger logger;

    public LogInterceptor() {
        logger = LoggerFactory.getLogger(LogInterceptor.class);
    }

    public LogInterceptor(String loggerName) {
        logger = LoggerFactory.getLogger(loggerName);
    }

    @Override
    public void handle(Context ctx) throws Exception {
        Uapi uapi = (Uapi) ctx.controller();

        if (uapi == null) {
            return;
        }

        String orgOutput = uapi.getOrgOutput();

        if (null != orgOutput) {
            logOutput(uapi, orgOutput);
        }

        if (null != ctx.errors) {
            logError(uapi, ctx.errors);
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

        TagsMDC.tag0(uapi.name()).tag1(String.valueOf(userId)).tag2(String.valueOf(verId));

        logger.info("::{}\r\n::{}", orgInput, orgOutput);
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

        TagsMDC.tag0(uapi.name()).tag1(String.valueOf(userId)).tag2(String.valueOf(verId));

        logger.error("::{}\r\n::{}", orgInput, err);
    }
}
