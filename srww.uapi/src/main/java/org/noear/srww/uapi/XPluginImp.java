package org.noear.srww.uapi;

import org.noear.solon.cloud.impl.CloudI18nBundleFactory;
import org.noear.solon.core.Aop;
import org.noear.solon.core.event.BeanLoadEndEvent;
import org.noear.solon.i18n.I18nBundleFactory;
import org.noear.srww.uapi.app.IAppFactory;
import org.noear.srww.uapi.app.impl.WaterAppFactoryImpl;
import org.noear.srww.uapi.validation.ValidatorFailureHandlerSrwwImp;
import org.noear.solon.SolonApp;
import org.noear.solon.core.Plugin;
import org.noear.solon.validation.ValidatorManager;

public class XPluginImp implements Plugin {
    @Override
    public void start(SolonApp app) {
        ValidatorManager.setFailureHandler(new ValidatorFailureHandlerSrwwImp());

        app.onEvent(BeanLoadEndEvent.class, e -> {
            if (Aop.get(IAppFactory.class) == null) {
                Aop.wrapAndPut(IAppFactory.class, new WaterAppFactoryImpl());
            }

            if (Aop.get(I18nBundleFactory.class) == null) {
                Aop.wrapAndPut(I18nBundleFactory.class, new CloudI18nBundleFactory());
            }
        });
    }
}
