package org.noear.srww.uapi.encoder;

import org.noear.solon.core.handle.Context;
import org.noear.srww.uapi.app.IApp;
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
    public String tryEncode(Context ctx, IApp app, String text) throws Exception {
        return EncryptUtils.aesEncrypt(text, app.getAppSecretKey(), algorithm, offset);
    }
}
