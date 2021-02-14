package org.noear.srww.uapi.decoder;

import org.noear.rock.model.AppModel;
import org.noear.solon.core.handle.Context;

public class DefDecoder implements Decoder {
    @Override
    public String tryDecode(Context context, AppModel app, String text) throws Exception {
        return text;
    }
}
