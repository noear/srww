package org.noear.srww.uapi.encoder;

import org.noear.rock.model.AppModel;
import org.noear.solon.core.handle.Context;
import org.noear.water.utils.EncryptUtils;

public class AesEncoder implements Encoder {
    private String algorithm = "AES/ECB/PKCS5Padding";
    private String offset;

    public AesEncoder() {

    }

    public AesEncoder algorithm(String algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    public AesEncoder offset(String offset) {
        this.offset = offset;
        return this;
    }

    @Override
    public String tryEncode(Context context, AppModel app, String text) throws Exception {
        return EncryptUtils.aesEncrypt(text, app.app_secret_key, algorithm, offset);
    }
}
