package org.noear.srww.uapi.encoder;

import org.noear.sponge.rock.models.AppModel;
import org.noear.solon.core.handle.Context;

public interface Encoder {
    String tryEncode(Context context, AppModel app, String text) throws Exception;
}
