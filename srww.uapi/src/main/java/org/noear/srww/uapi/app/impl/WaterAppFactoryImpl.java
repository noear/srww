package org.noear.srww.uapi.app.impl;

import org.noear.srww.uapi.app.IApp;
import org.noear.srww.uapi.app.IAppFactory;
import org.noear.water.WaterClient;

/**
 * @author noear 2022/4/8 created
 */
public class WaterAppFactoryImpl implements IAppFactory {
    @Override
    public IApp getAppById(int appId) throws Exception{
        return new WaterAppImpl(WaterClient.Key.getKeyById(appId));
    }

    @Override
    public IApp getAppByKey(String accessKey) throws Exception {
        return new WaterAppImpl(WaterClient.Key.getKeyByAccessKey(accessKey));
    }
}
