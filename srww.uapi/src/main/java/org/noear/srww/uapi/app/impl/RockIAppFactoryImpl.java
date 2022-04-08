package org.noear.srww.uapi.app.impl;

import org.noear.rock.RockClient;
import org.noear.srww.uapi.app.IApp;
import org.noear.srww.uapi.app.IAppFactory;

/**
 * @author noear 2022/4/8 created
 */
public class RockIAppFactoryImpl implements IAppFactory {
    @Override
    public IApp getAppById(int appId) throws Exception {
        return new RockAppImpl(RockClient.getAppByID(appId));
    }

    @Override
    public IApp getAppByKey(String accessKey) throws Exception {
        return new RockAppImpl(RockClient.getAppByKey(accessKey));
    }
}
