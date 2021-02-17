package apidemo2.controller;

import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.srww.uapi.interceptor.EndInterceptor;
import org.noear.srww.uapi.interceptor.OutputBuildInterceptor;
import org.noear.srww.uapi.interceptor.OutputInterceptor;
import org.noear.srww.uapi.interceptor.StartInterceptor;

/**
 * @author noear 2021/2/10 created
 */
@Mapping("/api/v1/app/**")
@Controller
public class ApiGatewayForApp extends ApiGateway {
    @Override
    protected void register() {
        before(new StartInterceptor());

        after(new OutputBuildInterceptor());
        after(new OutputInterceptor());
        after(new EndInterceptor("api.app"));

        super.register();
    }
}
