package org.noear.srww.uapi.handler;

import org.noear.srww.uapi.Uapi;
import org.noear.srww.uapi.app.IApp;
import org.noear.srww.uapi.encoder.DefEncoder;
import org.noear.srww.uapi.encoder.Encoder;
import org.noear.srww.uapi.UapiCodes;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.water.utils.TextUtils;

/**
 * 参数签名检测拦截器
 *
 * 对cmd有用；对api没用
 * */
public class ParamsSignCheckHandler implements Handler {
    private Encoder _encoder;

    public ParamsSignCheckHandler() {
        _encoder = new DefEncoder();
    }

    public ParamsSignCheckHandler(Encoder encoder) {
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


            /** 如果是CMD方案，则进行签名对比（签权） */
            if (uapi.getAppId() > 0 && orgInput != null) {
                IApp app = uapi.getApp();

                if (app.getAppId() != uapi.getAppId()) {
                    throw UapiCodes.CODE_4001010;
                }

                isOk = checkSign(ctx, uapi, app, apiName, orgInput);

            } else {
                isOk = false;
            }
        } else {
            throw UapiCodes.CODE_4001010;
        }

        if (isOk == false) {
            throw UapiCodes.CODE_4001013;
        }
    }

    /**
     * 签权算法
     */
    private boolean checkSign(Context context, Uapi uapi, IApp app, String apiName, String orgInput) throws Exception {
        if (TextUtils.isEmpty(apiName)) {
            return false;
        }

        String orgInputSign = uapi.getOrgInputSign();
        String orgInputTimestamp = uapi.getOrgInputTimestamp();


        int verId = uapi.getVerId();

        //{name}#{verId}#{orgInput}#{secretKey}#{orgInputTimestamp}
        StringBuilder sb = new StringBuilder();
        sb.append(apiName).append("#").append(verId).append("#").append(orgInput).append("#").append(app.getAppSecretKey()).append("#").append(orgInputTimestamp);

        String _sign_ = _encoder.tryEncode(context, app, sb.toString());

        return (_sign_.equalsIgnoreCase(orgInputSign));
    }
}