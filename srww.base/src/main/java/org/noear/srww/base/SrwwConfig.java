package org.noear.srww.base;

import org.noear.solon.Solon;
import org.noear.solon.Utils;

/**
 * @author noear 2022/4/9 created
 */
public class SrwwConfig {
    public static final String KEY_inputLimitSize = "srww.log.inputLimitSize";

    public static String weedPrintStyle() {
        return Solon.cfg().get("srww.weed.print.style");
    }

    public static boolean weedTrackEnable(boolean def) {
        return Solon.cfg().getBool("srww.weed.track.enable", def);
    }

    public static boolean weedErrorLogEnable() {
        return Solon.cfg().getBool("srww.weed.error.log.enable", true);
    }

    public static int logInputLimitSize() {
        return Solon.cfg().getInt(KEY_inputLimitSize, 0);
    }

    public static String i18nCodeBundleName() {
        String bundleName = Solon.cfg().get("srww.i18n.code.bundle");
        if (Utils.isEmpty(bundleName)) {
            return Solon.cfg().appName() + "__code";
        } else {
            return bundleName;
        }
    }
}
