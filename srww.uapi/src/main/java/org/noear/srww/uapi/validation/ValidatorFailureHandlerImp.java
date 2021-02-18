package org.noear.srww.uapi.validation;

import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Result;
import org.noear.solon.extend.validation.ValidatorFailureHandler;
import org.noear.solon.extend.validation.annotation.NoRepeatSubmit;
import org.noear.solon.extend.validation.annotation.Whitelist;
import org.noear.srww.uapi.UapiCodes;

import java.lang.annotation.Annotation;

/**
 * @author noear 2021/2/18 created
 */
public class ValidatorFailureHandlerImp implements ValidatorFailureHandler {
    @Override
    public boolean onFailure(Context ctx, Annotation ano, Result result, String message) {
        ctx.setHandled(true);

        Class<?> type = ano.annotationType();

        if (type.equals(NoRepeatSubmit.class)) {
            throw UapiCodes.CODE_4001015;
        } else if (type.equals(Whitelist.class)) {
            throw UapiCodes.CODE_4001016;
        } else {
            throw UapiCodes.CODE_4001014(result.getDescription());
        }
    }
}
