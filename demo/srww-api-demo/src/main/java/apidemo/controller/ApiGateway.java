package apidemo.controller;

import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.core.Aop;
import org.noear.srww.uapi.UapiGateway;
import org.noear.srww.uapi.decoder.AesDecoder;
import org.noear.srww.uapi.encoder.AesEncoder;
import org.noear.srww.uapi.encoder.Sha1Encoder;
import org.noear.srww.uapi.encoder.Sha256Encoder;
import org.noear.srww.uapi.interceptor.*;
import apidemo.Config;
import apidemo.controller.interceptor.LogInterceptor;
import org.noear.srww.uapi.interceptor.ParamsAuthInterceptor;

@Mapping("/API/*")
@Controller
public class ApiGateway extends UapiGateway {

    @Override
    public int agroup_id() {
        return Config.agroupId;
    }

    @Override
    protected void register() {
        before(new StartInterceptor()); //开始计时
        before(new ParamsBuildInterceptor(new AesDecoder())); //构建参数
        before(new ParamsAuthInterceptor(new Sha256Encoder()));//签权，可以没有

        after(new OutputBuildInterceptor(new AesEncoder()));//构建输出内容
        after(new OutputSignInterceptor(new Sha1Encoder()));//输出签名
        after(new OutputInterceptor());//输出
        after(new LogInterceptor());//记录日志
        after(new EndInterceptor("API"));//结束计时，并上报

        Aop.beanOnloaded(() -> {
            Aop.beanForeach((bw) -> {
                if ("api".equals(bw.tag())) {
                    add(bw);
                }
            });
        });
    }
}