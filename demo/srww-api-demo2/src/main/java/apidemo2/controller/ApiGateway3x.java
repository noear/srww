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
public class ApiGateway3x extends ApiGatewayBase {
    @Override
    protected void register() {
        // 快速体验：
        //
        // 通过单元测试走
        //
        // http://localhost:8080/api/v2/app/a.b.c
        //
        before(new StartInterceptor()); //开始
        before(new SentryInterceptor()); //融断

        before(new ParamsParseInterceptor()); //参数解析
        before(new ParamsSignCheckInterceptor(new Md5Encoder())); //参数签名较验
        before(new ParamsRebuildInterceptor(new AesDecoder())); //参数重构


        after(new OutputBuildInterceptor(new AesEncoder())); //输出构建
        after(new OutputSignInterceptor(new Md5Encoder())); //输出签名
        after(new OutputInterceptor()); //输出
        after(new LogInterceptor()); //日志
        after(new EndInterceptor("v2.api.app")); //结束

        super.register();
    }
}
