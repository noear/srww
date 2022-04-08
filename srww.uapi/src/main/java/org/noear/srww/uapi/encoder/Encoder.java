package org.noear.srww.uapi.encoder;

import org.noear.solon.core.handle.Context;
import org.noear.srww.uapi.app.IApp;

public interface Encoder {
    String tryEncode(Context ctx, IApp app, String text) throws Exception;
}
