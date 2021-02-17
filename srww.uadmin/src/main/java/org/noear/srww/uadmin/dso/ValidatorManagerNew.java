package org.noear.srww.uadmin.dso;

import org.noear.solon.core.event.EventBus;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Result;
import org.noear.solon.extend.validation.ValidatorManager;
import org.noear.solon.extend.validation.annotation.NoRepeatSubmit;
import org.noear.solon.extend.validation.annotation.Whitelist;

import java.lang.annotation.Annotation;

public class ValidatorManagerNew extends ValidatorManager {
    public ValidatorManagerNew() {
        super();
    }

    @Override
    protected boolean failureDo(Context ctx, Annotation ano, Result result, String message) {
        ctx.setHandled(true);

        try {
            ctx.render(result);
        } catch (Throwable ex) {
            EventBus.push(ex);
        }

        return true;

//        Class<?> type = ano.annotationType();
//
//        if (type.equals(NoRepeatSubmit.class)) {
//            throw UapiCodes.CODE_4001015;
//        } else if (type.equals(Whitelist.class)) {
//            throw UapiCodes.CODE_4001016;
//        } else {
//            throw UapiCodes.CODE_4001014(result.getDescription());
//        }
    }
}
