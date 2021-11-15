package org.noear.srww.uapi.decoder;

import org.noear.rock.model.AppModel;
import org.noear.solon.core.handle.Context;
import org.noear.water.utils.EncryptUtils;

import java.net.URLDecoder;

public class AesDecoder implements Decoder {
    private String algorithm = "AES/ECB/PKCS5Padding";
    private String offset;

    public AesDecoder() {

    }

    public AesDecoder algorithm(String algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    public AesDecoder offset(String offset) {
        this.offset = offset;
        return this;
    }

    @Override
    public String tryDecode(Context context, AppModel app, String text) throws Exception {
        if (text.indexOf("{") < 0 && text.indexOf("<") < 0) {
            if (text.indexOf('%') >= 0) {
                text = URLDecoder.decode(text, "UTF-8");
            }

            return EncryptUtils.aesDecrypt(text, app.app_secret_key, algorithm, offset);
        }

        return text;
    }
}
