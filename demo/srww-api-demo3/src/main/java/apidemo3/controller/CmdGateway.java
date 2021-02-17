package apidemo3.controller;

import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.srww.uapi.UapiGateway;
import org.noear.srww.uapi.decoder.AesDecoder;
import org.noear.srww.uapi.encoder.AesEncoder;
import org.noear.srww.uapi.encoder.Sha1Encoder;
import org.noear.srww.uapi.encoder.Sha256Encoder;
import org.noear.srww.uapi.interceptor.*;
import apidemo3.Config;

@Mapping("/CMD/*")
@Controller
public class CmdGateway extends UapiGateway {

    @Override
    public int agroup_id() {
        return Config.agroupId;
    }

    @Override
    protected void register() {
        before(new StartInterceptor());//开始计时
        before(new ParamsBuildInterceptor(new AesDecoder())); //构建参数
        before(new ParamsAuthInterceptor(new Sha256Encoder()));//签权，可以没有

        after(new OutputBuildInterceptor(new AesEncoder()));//构建输出内容
        after(new OutputSignInterceptor(new Sha1Encoder()));//输出签名
        after(new OutputInterceptor());//输出
        after(new LogInterceptor("demoapi_log"));//日志
        after(new EndInterceptor("CMD"));//结束计时，并上报

        addBeans(bw -> "cmd".equals(bw.tag()));
    }
}
