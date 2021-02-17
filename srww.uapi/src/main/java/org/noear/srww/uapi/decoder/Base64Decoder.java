package org.noear.srww.uapi.decoder;

import org.noear.rock.model.AppModel;
import org.noear.solon.core.handle.Context;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author noear 2021/2/17 created
 */
public class Base64Decoder implements Decoder{
    @Override
    public String tryDecode(Context context, AppModel app, String text) throws Exception {
        return new String(Base64.getDecoder().decode(text.getBytes(StandardCharsets.UTF_8)));
    }
}
