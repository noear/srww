package org.noear.srww.base.validation;

import org.noear.solon.Solon;
import org.noear.solon.validation.annotation.NoRepeatSubmitChecker;
import org.noear.water.utils.LockUtils;

public class NoRepeatSubmitCheckerNew implements NoRepeatSubmitChecker {
    @Override
    public boolean check(String key, int seconds) {
        return LockUtils.tryLock(Solon.cfg().appName(), key, seconds);
    }
}
