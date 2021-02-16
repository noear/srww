package org.noear.srww.uadmin.dso;

import org.noear.bcf.BcfSessionBase;
import org.noear.bcf.models.BcfUserModel;
import org.noear.solon.Solon;

/**
 * @author noear 2014-10-19
 * @since 1.0
 */
public final class Session extends BcfSessionBase {
    private static final Session _current = new Session();
    public static Session current() {
        return _current;
    }


    @Override
    public String service() {
        return Solon.cfg().appName();
    }

    //////////////////////////////////
    //当前项目的扩展

    @Override
    public void loadModel(BcfUserModel user) throws Exception {
        if (user == null) {
            return;
        }

        setPUID(user.puid);
        setUserId(user.user_id);
        setUserName(user.cn_name);
    }


    public final String getValidation() {
        return get("Validation_String", null);
    }

    public final void setValidation(String validation) {
        set("Validation_String", validation.toLowerCase());
    }
}
