package apidemo3.controller;

import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.srww.uapi.UapiGateway;
import org.noear.srww.uapi.decoder.AesDecoder;
import org.noear.srww.uapi.encoder.AesEncoder;
import org.noear.srww.uapi.encoder.Sha1Encoder;
import org.noear.srww.uapi.encoder.Sha256Encoder;
import org.noear.srww.uapi.handler.*;

@Mapping("/CMD/*")
@Controller
public class CmdGateway extends UapiGateway {

    @Override
    protected void register() {
        before(new StartHandler());//开始计时
        before(new ParamsRebuildHandler(new AesDecoder())); //构建参数
        before(new ParamsSignCheckHandler(new Sha256Encoder()));//签权，可以没有

        after(new OutputBuildHandler(new AesEncoder()));//构建输出内容
        after(new OutputSignHandler(new Sha1Encoder()));//输出签名
        after(new OutputHandler());//输出
        after(new EndBeforeLogHandler("demoapi_log"));//日志
        after(new EndHandler("CMD"));//结束计时，并上报

        addBeans(bw -> "cmd".equals(bw.tag()));
    }
}
