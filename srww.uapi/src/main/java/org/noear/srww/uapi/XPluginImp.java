package org.noear.srww.uapi;

import org.noear.srww.uapi.validation.NoRepeatLockNew;
import org.noear.srww.uapi.validation.ValidatorManagerNew;
import org.noear.srww.uapi.validation.WhitelistCheckerNew;
import org.noear.solon.SolonApp;
import org.noear.solon.core.Plugin;
import org.noear.solon.extend.validation.ValidatorManager;

public class XPluginImp implements Plugin {
    @Override
    public void start(SolonApp app) {
        ValidatorManager.globalSet(new ValidatorManagerNew());

        ValidatorManager.setNoRepeatLock(new NoRepeatLockNew());
        ValidatorManager.setWhitelistChecker(new WhitelistCheckerNew());
    }
}
