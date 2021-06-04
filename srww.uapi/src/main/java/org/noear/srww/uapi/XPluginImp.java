package org.noear.srww.uapi;

import org.noear.srww.uapi.validation.ValidatorFailureHandlerSrwwImp;
import org.noear.solon.SolonApp;
import org.noear.solon.core.Plugin;
import org.noear.solon.extend.validation.ValidatorManager;

public class XPluginImp implements Plugin {
    @Override
    public void start(SolonApp app) {
        ValidatorManager.global().onFailure(new ValidatorFailureHandlerSrwwImp());
    }
}
