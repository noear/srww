package apidemo2.controller;

import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.srww.uapi.decoder.AesDecoder;
import org.noear.srww.uapi.encoder.AesEncoder;
import org.noear.srww.uapi.encoder.Md5Encoder;
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
        before(new ParamsAuthInterceptor(new Md5Encoder()));

        after(new OutputBuildInterceptor(new AesEncoder()));
        after(new OutputSignInterceptor(new Md5Encoder()));
        after(new OutputInterceptor());
        after(new EndInterceptor("v2.api.app"));

        super.register();
    }
}
