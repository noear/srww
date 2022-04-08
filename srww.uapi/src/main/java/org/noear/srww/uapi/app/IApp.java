package org.noear.srww.uapi.app;

/**
 * @author noear 2022/4/8 created
 */
public interface IApp {
    int getAppId();
    int getAppGroupId();
    String getAccessKey();
    String getAppSecretKey();
    String getAppSecretSalt();
    String getLabel();
}
