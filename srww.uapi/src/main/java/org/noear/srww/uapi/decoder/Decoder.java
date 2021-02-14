package org.noear.srww.uapi.decoder;

import org.noear.rock.model.AppModel;
import org.noear.solon.core.handle.Context;

public interface Decoder {
    String tryDecode(Context context, AppModel app, String text)  throws Exception;
}
