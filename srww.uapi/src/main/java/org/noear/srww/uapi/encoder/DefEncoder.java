package org.noear.srww.uapi.encoder;

import org.noear.solon.core.handle.Context;
import org.noear.srww.uapi.app.IApp;

public class DefEncoder implements Encoder {
    @Override
    public String tryEncode(Context ctx, IApp app, String text) throws Exception {
        return text;
    }
}
