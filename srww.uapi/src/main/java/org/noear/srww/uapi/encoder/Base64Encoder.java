package org.noear.srww.uapi.encoder;

import org.noear.rock.model.AppModel;
import org.noear.solon.core.handle.Context;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author noear 2021/2/17 created
 */
public class Base64Encoder implements Encoder {
    @Override
    public String tryEncode(Context context, AppModel app, String text) throws Exception {
        return Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
    }
}
