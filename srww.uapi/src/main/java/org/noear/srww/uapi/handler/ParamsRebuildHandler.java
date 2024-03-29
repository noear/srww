package org.noear.srww.uapi.handler;

import org.noear.snack.ONode;
import org.noear.srww.uapi.Uapi;
import org.noear.srww.uapi.UapiCodes;
import org.noear.srww.uapi.app.IApp;
import org.noear.srww.uapi.decoder.Decoder;
import org.noear.srww.uapi.decoder.DefDecoder;
import org.noear.solon.Utils;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.srww.uapi.common.Attrs;

/**
 * 参数重构拦截器（将输出内容构建为统一的参数模型）
 *
 * 对cmd 有用；对api 没用（api 不要使用了）
 *
 * 支持1:
 *  form : p, k
 * 支持2:
 *  header: Authorization (相当于 form:k)
 *  body: (content type: application/json)（相当于 form:p）
 * */
public class ParamsRebuildHandler implements Handler {

    private Decoder _decoder;


    public ParamsRebuildHandler() {
        _decoder = new DefDecoder();
    }

    public ParamsRebuildHandler(Decoder decoder) {
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

        Uapi uapi = (Uapi) ctx.controller();

        String orgInput = uapi.getOrgInput();


        //3.尝试解析参数（涉及解码器）
        //
        if (Utils.isNotEmpty(orgInput)) {

            //如果有应用id
            if (uapi.getAppId() > 0) {
                //尝试解码
                //
                IApp app = uapi.getApp();
                if (app.getAppId() != uapi.getAppId()) {
                    throw UapiCodes.CODE_4001010;
                }

                try {
                    orgInput = _decoder.tryDecode(ctx, app, orgInput);
                }catch (Exception ex){
                    throw UapiCodes.CODE_4001018;
                }

                ctx.contentType();
                ctx.headerMap().put("Content-type","application/json");
                ctx.bodyNew(orgInput);

                //解析数据
                //
                ONode tmp = ONode.load(orgInput);

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


            ctx.attrSet(Attrs.org_input, orgInput);
        }
    }
}