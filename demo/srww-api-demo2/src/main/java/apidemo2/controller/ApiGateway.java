package apidemo2.controller;

import org.noear.snack.ONode;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Gateway;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author noear 2021/2/10 created
 */
public abstract class ApiGateway extends Gateway {
    @Override
    protected void register() {
        addBeans(bw -> "api".equals(bw.tag()));
    }
}
