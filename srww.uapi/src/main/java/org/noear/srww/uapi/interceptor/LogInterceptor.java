package org.noear.srww.uapi.interceptor;

import org.noear.mlog.Logger;
import org.noear.mlog.LoggerFactory;
import org.noear.mlog.utils.Tags;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.srww.uapi.Uapi;
import org.noear.srww.uapi.UapiParams;

public class LogInterceptor implements Handler {
    Logger logger;

    public LogInterceptor(String loggerName){
        logger = LoggerFactory.get(loggerName);
    }

    @Override
    public void handle(Context ctx) throws Exception {
        Uapi api = (Uapi) ctx.controller();

        UapiParams params = api.params();

        String output = ctx.attr(Attrs.output);

        if (null != output) {
            logOutput(api, params, output);
        }

        if (null != ctx.errors) {
            logError(api, params, ctx.errors);
        }
    }

    /**
     * 日志拦截器中使用
     *
     * @param uapi
     * @param params
     * @param result
     */
    protected void logOutput(Uapi uapi, UapiParams params, String result) {

        String summary = (null == params) ? "" : params.org_param;
        if (null == summary) {
            summary = "";
        }
        if (summary.length() > 900) {
            summary = summary.substring(0, 900);
        }

        String content = (null == result) ? "" : result.toString();

        int ver = 0;
        if (null != params) {
            ver = params.verID;
        }

        long user_id = uapi.getUserID();

        logger.info(
                Tags.tag0(uapi.name()).tag1(String.valueOf(user_id)).tag2(String.valueOf(ver)),
                "{}\n\n{}",
                summary,
                content
        );
    }

    /**
     * 日志拦截器中使用
     *
     * @param uapi
     * @param params
     * @param e
     */
    protected void logError(Uapi uapi, UapiParams params, Throwable e) {

        String summary = null != params ? params.org_param : "";
        String tag2 = null != params ? String.valueOf(params.verID) : "";
        long user_id = uapi.getUserID();

        logger.error(
                Tags.tag0(uapi.name()).tag1(String.valueOf(user_id)).tag2(tag2),
                "{}\n\n{}",
                summary,
                e
        );
    }
}
