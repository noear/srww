package apidemo0.controller;

import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.srww.uapi.UapiGateway;

/**
 * @author noear 2021/2/17 created
 */
@Mapping("/API/V1/**")
@Controller
public class ApiGateway extends UapiGateway {
    @Override
    protected void register() {
        addBeans(bw -> "api".equals(bw.tag()));
    }
}
