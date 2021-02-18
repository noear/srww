package org.noear.srww.uapi.interceptor;

import org.noear.rock.model.AppModel;
import org.noear.srww.uapi.Uapi;
import org.noear.srww.uapi.encoder.DefEncoder;
import org.noear.srww.uapi.encoder.Encoder;
import org.noear.srww.uapi.UapiCodes;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.water.utils.TextUtils;

/**
 * 参数签权拦截器
 *
 * 对cmd有用；对api没用
 * */
public class ParamsAuthInterceptor implements Handler {
    private Encoder _encoder;

    public ParamsAuthInterceptor(){
        _encoder = new DefEncoder();
    }

    public ParamsAuthInterceptor(Encoder encoder) {
        if (encoder == null) {
            _encoder = new DefEncoder();
        } else {
            _encoder = encoder;
        }
    }

    @Override
    public void handle(Context ctx) throws Throwable {
        /** 如果已处理，不再执行 */
        if (ctx.getHandled()) {
            return;
        }


        /** 获取参数 */
        Uapi uapi = (Uapi) ctx.controller();

        if (uapi == null) {
            return;
        }

        boolean isOk = (uapi.getAppId() > 0);

        if (isOk) {
            String apiName = uapi.name();
            String orgInput = uapi.getOrgInput();
            String orgInputSign  = uapi.getOrgInputSign();


            /** 如果是CMD方案，则进行签名对比（签权） */
            if (uapi.getAppId() > 0 && orgInput != null) {
                isOk = checkSign(ctx, uapi.getApp(), apiName, orgInput, orgInputSign);
            } else {
                isOk = false;
            }
        }

        if (isOk == false) {
            throw UapiCodes.CODE_4001013;
        }
    }

    /**
     * 签权算法
     */
    private boolean checkSign(Context context, AppModel app, String cmd, String params, String sign) throws Exception {
        if (TextUtils.isEmpty(cmd)) {
            return false;
        }

        String key = app.app_key;

        StringBuilder sb = new StringBuilder();
        sb.append(cmd).append("#").append(params).append("#").append(key);

        String _sign_ = _encoder.tryEncode(context, app, sb.toString());

        return (_sign_.equalsIgnoreCase(sign));
    }
}