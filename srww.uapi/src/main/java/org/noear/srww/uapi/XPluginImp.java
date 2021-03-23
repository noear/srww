package org.noear.srww.uapi;

import org.noear.srww.uapi.validation.ValidatorFailureHandlerImp;
import org.noear.solon.SolonApp;
import org.noear.solon.core.Plugin;
import org.noear.solon.extend.validation.ValidatorManager;

import java.security.Security;

public class XPluginImp implements Plugin {
    @Override
    public void start(SolonApp app) {
        //Security.addProvider(new BouncyCastleProvider());

        ValidatorManager.global().onFailure(new ValidatorFailureHandlerImp());
    }
}
