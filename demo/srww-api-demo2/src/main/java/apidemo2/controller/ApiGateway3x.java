package apidemo2.controller;

import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.srww.uapi.decoder.AesDecoder;
import org.noear.srww.uapi.encoder.AesEncoder;
import org.noear.srww.uapi.interceptor.*;

/**
 * @author noear 2021/2/10 created
 */
@Mapping("/api/v2/app/**")
@Controller
public class ApiGateway3x extends ApiGateway {
    @Override
    protected void register() {
        before(new StartInterceptor());
        before(new ParamsBuildInterceptor(new AesDecoder()));
        before(new ParamsAuthInterceptor());

        after(new OutputBuildInterceptor(new AesEncoder()));
        after(new OutputInterceptor());
        after(new EndInterceptor("api.web"));

        super.register();
    }
}
