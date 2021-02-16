package org.noear.srww.admin.ui;


import org.noear.solon.SolonApp;
import org.noear.solon.core.Plugin;

/**
 * @author noear 2021/2/16 created
 * @since 1.0
 */
public class XPluginImp implements Plugin {
    @Override
    public void start(SolonApp app) {
        app.beanScan(XPluginImp.class);
    }
}
