package org.noear.srww.uapi;

import org.noear.rock.solon.RockCodeI18nBundleFactory;
import org.noear.solon.Solon;
import org.noear.solon.core.Aop;
import org.noear.solon.core.AopContext;
import org.noear.solon.core.event.BeanLoadEndEvent;
import org.noear.solon.i18n.I18nBundleFactory;
import org.noear.srww.uapi.app.IAppFactory;
import org.noear.srww.uapi.app.impl.RockAppFactoryImpl;
import org.noear.srww.uapi.validation.ValidatorFailureHandlerSrwwImp;
import org.noear.solon.core.Plugin;
import org.noear.solon.validation.ValidatorManager;

public class XPluginImp implements Plugin {
    @Override
    public void start(AopContext context) {
        ValidatorManager.setFailureHandler(new ValidatorFailureHandlerSrwwImp());

        Solon.global().onEvent(BeanLoadEndEvent.class, e -> {
            if (Aop.get(IAppFactory.class) == null) {
                Aop.wrapAndPut(IAppFactory.class, new RockAppFactoryImpl());
            }

            if (Aop.get(I18nBundleFactory.class) == null) {
                Aop.wrapAndPut(I18nBundleFactory.class, new RockCodeI18nBundleFactory());
            }
        });
    }
}
