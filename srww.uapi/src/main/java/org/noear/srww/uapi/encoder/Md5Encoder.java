package org.noear.srww.uapi.encoder;

import org.noear.solon.core.handle.Context;
import org.noear.srww.uapi.app.IApp;
import org.noear.water.utils.EncryptUtils;

public class Md5Encoder implements Encoder {
    @Override
    public String tryEncode(Context ctx, IApp app, String text) throws Exception {
        return EncryptUtils.md5(text);
    }
}
