package demo;

import org.noear.srww.uapi.UapiGateway;
import org.noear.srww.uapi.UapiCode;
import org.noear.srww.uapi.decoder.AesDecoder;
import org.noear.srww.uapi.encoder.AesEncoder;
import org.noear.srww.uapi.encoder.Sha1Encoder;
import org.noear.srww.uapi.handler.*;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;

@Mapping("/cmd/*")
@Controller
class _demo_cmd extends UapiGateway {
    @Override
    protected void register() {

        //
        //::执行前::
        //

        //开始计时
        before(new StartHandler());
        //构建参数
        before(new ParamsRebuildHandler(new AesDecoder()));
        //签权
        before(new ParamsSignCheckHandler(new Sha1Encoder()));

        //
        //::执行后::
        //

        //构建输出
        after(new OutputBuildHandler(new AesEncoder()));
        //签名
        after(new OutputSignHandler(new Sha1Encoder())); //可选
        //输出
        after(new OutputHandler());
        //结束计时
        after(new EndHandler("{tag}"));


        add(CMD_0_0_1.class);
    }

    public static class CMD_0_0_1 {
        @Mapping
        public Object call() {
            return new UapiCode(0, "接口不存在");
        }
    }
}
