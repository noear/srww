package org.noear.srww.uadmin;


import org.noear.solon.Solon;
import org.noear.solon.SolonApp;
import org.noear.solon.core.Plugin;
import org.noear.water.WW;
import org.noear.water.WaterClient;

/**
 * @author noear 2021/2/16 created
 * @since 1.0
 */
public class XPluginImp implements Plugin {
    @Override
    public void start(SolonApp app) {
        app.beanScan(XPluginImp.class);

        WaterClient.Config.getProperties(WW.water_session).forEach((k, v) -> {
            if (Solon.cfg().isDebugMode()) {
                String key = k.toString();
                if (key.indexOf(".session.") < 0) {
                    Solon.cfg().put(k, v);
                }
            } else {
                Solon.cfg().put(k, v);
            }
        });
    }
}
