package org.noear.srww.uapi.handler;

import org.noear.snack.ONode;
import org.noear.solon.Utils;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.solon.logging.utils.TagsMDC;
import org.noear.srww.uapi.Uapi;
import org.noear.srww.uapi.common.Attrs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志拦截器
 * */
public class LogHandler implements Handler {
    Logger logger;

    public LogHandler() {
        logger = LoggerFactory.getLogger(LogHandler.class);
    }

    public LogHandler(String loggerName) {
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

        StringBuilder logInput = new StringBuilder();

        //构建输入项
        String orgInput = uapi.getOrgInput();
        if (null == orgInput) {
            orgInput = ONode.stringify(uapi.context().paramMap());
        }
        if (orgInput.length() > 2000) {
            orgInput = orgInput.substring(0, 2000);
        }


        String org_sign = uapi.context().attr(Attrs.org_sign);
        String org_token = uapi.context().header(Attrs.h_token);

        if (Utils.isNotEmpty(org_token)) {
            logInput.append("> Token: ").append(org_token).append("\r\n");
        }
        if (Utils.isNotEmpty(org_sign)) {
            logInput.append("> Sign: ").append(org_sign).append("\r\n");
        }
        logInput.append("> Body: ").append(orgInput).append("\r\n");


        //构建输出项
        StringBuilder logOutput = new StringBuilder();
        String out_sign = uapi.context().attr(Attrs.h_sign);
        String out_token = uapi.context().attr(Attrs.h_token);

        if(Utils.isNotEmpty(out_token)){
            logOutput.append("< Token: ").append(out_token).append("\r\n");
        }

        if(Utils.isNotEmpty(out_sign)) {
            logOutput.append("< Sign: ").append(out_sign).append("\r\n");
        }

        logOutput.append("< Body: ").append(orgOutput);



        int verId = uapi.getVerId();
        long userId = uapi.getUserID();

        TagsMDC.tag0(uapi.name()).tag1(String.valueOf(userId)).tag2(String.valueOf(verId));

        logger.info("{}\r\n{}", logInput.toString(), logOutput.toString());
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

        StringBuilder logInput = new StringBuilder();

        //构建输入项
        String orgInput = uapi.getOrgInput();
        if (null == orgInput) {
            orgInput = ONode.stringify(uapi.context().paramMap());
        }
        if (orgInput.length() > 2000) {
            orgInput = orgInput.substring(0, 2000);
        }


        String org_sign = uapi.context().attr(Attrs.h_sign);
        String org_token = uapi.context().header(Attrs.h_token);

        if (Utils.isNotEmpty(org_token)) {
            logInput.append("> Token: ").append(org_token).append("\r\n");
        }
        if (Utils.isNotEmpty(org_sign)) {
            logInput.append("> Sign: ").append(org_sign).append("\r\n");
        }
        logInput.append("> Body: ").append(orgInput).append("\r\n");

        int verId = uapi.getVerId();
        long userId = uapi.getUserID();

        TagsMDC.tag0(uapi.name()).tag1(String.valueOf(userId)).tag2(String.valueOf(verId));

        logger.error("{}\r\n{}", logInput.toString(), err);
    }
}
