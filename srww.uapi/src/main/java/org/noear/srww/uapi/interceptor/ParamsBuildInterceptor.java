package org.noear.srww.uapi.interceptor;

import org.noear.snack.ONode;
import org.noear.srww.uapi.Uapi;
import org.noear.srww.uapi.decoder.Decoder;
import org.noear.srww.uapi.decoder.DefDecoder;
import org.noear.solon.Utils;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;

/**
 * 参数构建拦截器（将输出内容构建为统一的参数模型）
 *
 * 对cmd 有用；对api 没用（api 不要使用了）
 *
 * 支持1:
 *  form : p, k
 * 支持2:
 *  header: Authorization (相当于 form:k)
 *  body: (content type: application/json)（相当于 form:p）
 * */
public class ParamsBuildInterceptor implements Handler {

    private Decoder _decoder;


    public ParamsBuildInterceptor() {
        _decoder = new DefDecoder();
    }

    public ParamsBuildInterceptor(Decoder decoder) {
        if (decoder == null) {
            _decoder = new DefDecoder();
        } else {
            _decoder = decoder;
        }
    }

    @Override
    public void handle(Context ctx) throws Throwable {
        /** 如果已处理，不再执行 */
        if (ctx.getHandled()) {
            return;
        }


        /** 处理CMD风格的参数 */

        //1.获取参数与令牌
        //
        String org_input = ctx.param("p"); //参数
        String org_sign = ctx.param("k"); //令牌
        String org_input_sgin;
        int app_id = 0;
        int ver_id = 0;

        if (org_sign == null) {
            //支持 header 传
            org_sign = ctx.header(Attrs.h_sign); //令牌
        }

        if (org_input == null) {
            //支持 body 传
            org_input = ctx.body();
        }

        //2.尝试解析令牌
        //
        if (!Utils.isEmpty(org_sign)) {
            //
            //sign:{appid}.{verid}.{sgin}
            //
            String[] sign = org_sign.split("\\.");

            if (sign.length >= 3) {
                app_id = Integer.parseInt(sign[0]);
                ver_id = Integer.parseInt(sign[1]);
                org_input_sgin = sign[2];

                ctx.paramSet(Attrs.app_id, String.valueOf(app_id));
                ctx.paramSet(Attrs.ver_id, String.valueOf(ver_id));
                ctx.attrSet(Attrs.org_input_sign, org_input_sgin);
            }
        }

        //3.尝试解析参数（涉及解码器）
        //
        if (!Utils.isEmpty(org_input)) {

            //如果有应用id
            if (app_id > 0) {
                //尝试解码
                //
                Uapi uapi = (Uapi) ctx.controller();
                org_input = _decoder.tryDecode(ctx, uapi.getApp(), org_input);

                //解析数据
                //
                ONode tmp = ONode.load(org_input);

                if (tmp.isObject()) {
                    //转到上下文参数
                    //
                    tmp.obj().forEach((k, v) -> {
                        if (v.isValue()) {
                            ctx.paramSet(k, v.getString());
                        }
                    });
                }
            }


            ctx.attrSet(Attrs.org_input, org_input);
        }
    }
}