package apidemo1.controller;

import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.srww.uapi.interceptor.*;

/**
 * @author noear 2021/2/10 created
 */
@Mapping("/api/v1/app/**")
@Controller
public class ApiGateway1x extends ApiGateway {
    @Override
    protected void register() {
        // 快速体验：
        //
        // http://localhost:8080/api/v1/app/config.get?tag=water
        //
        // http://localhost:8080/api/v1/app/a.b.c
        //
        before(new StartInterceptor());

        after(new OutputBuildInterceptor());
        after(new OutputInterceptor());
        after(new LogInterceptor());
        after(new EndInterceptor("v1.api.app"));

        super.register();
    }
}
