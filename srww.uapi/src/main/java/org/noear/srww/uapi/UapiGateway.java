package org.noear.srww.uapi;

import org.noear.snack.ONode;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Gateway;
import org.noear.solon.core.handle.ModelAndView;
import org.noear.solon.core.handle.Result;
import org.noear.srww.uapi.interceptor.Attrs;

public abstract class UapiGateway extends Gateway {

    /**
     * 语言
     */
    public String g_lang(Context c) {
        return c.param(Attrs.g_lang);
    }

    @Override
    public void render(Object obj, Context c) throws Throwable {
        if (c.getRendered()) {
            return;
        }

        c.setRendered(true);

        //
        // 有可能根本没数据过来
        //
        if (obj instanceof ModelAndView) {
            //如果有模板，则直接渲染
            //
            c.result = obj;
            c.render(obj);
        } else {
            //如果没有按Result tyle 渲染
            //
            if (obj instanceof UapiCode) {
                //处理标准的状态码
                UapiCode err = (UapiCode) obj;
                String description = UapiCodes.CODE_note(g_lang(c), err);

                c.result = Result.failure(err.getCode(), description);
            } else if (obj instanceof Throwable) {
                //处理未知异常
                String description = UapiCodes.CODE_note(g_lang(c), UapiCodes.CODE_400);

                c.result = Result.failure(Result.FAILURE_CODE, description);
            } else if (obj instanceof ONode) {
                //处理ONode数据（为兼容旧的）
                c.result = Result.succeed(obj);
            } else if (obj instanceof Result) {
                //处理Result结构
                c.result = obj;
            } else {
                //处理java bean数据（为扩展新的）
                c.result = Result.succeed(obj);
            }
        }
    }
}
