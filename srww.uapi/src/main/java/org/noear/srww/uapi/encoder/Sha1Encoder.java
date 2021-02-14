package org.noear.srww.uapi.encoder;

import org.noear.rock.model.AppModel;
import org.noear.solon.core.handle.Context;
import org.noear.water.utils.EncryptUtils;

public class Sha1Encoder implements Encoder {
    @Override
    public String tryEncode(Context context, AppModel app, String text) throws Exception {
        return EncryptUtils.sha1(text);
    }
}
