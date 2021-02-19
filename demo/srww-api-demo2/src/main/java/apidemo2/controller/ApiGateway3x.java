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
        // 快速体验：
        //
        // 通过单元测试走
        //
        // http://localhost:8080/api/v2/app/a.b.c
        //
        before(new StartInterceptor());
        before(new ParamsRebuildInterceptor(new AesDecoder()));
        before(new ParamsSignCheckInterceptor(new Md5Encoder()));

        after(new ParamsParseInterceptor());
        after(new OutputSignInterceptor(new Md5Encoder()));
        after(new OutputBuildInterceptor(new AesEncoder()));
        after(new OutputInterceptor());
        after(new LogInterceptor());
        after(new EndInterceptor("v2.api.app"));

        super.register();
    }
}
