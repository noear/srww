package apidemo0.controller;

import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.srww.uapi.UapiGateway;
import org.noear.srww.uapi.encoder.AesEncoder;
import org.noear.srww.uapi.interceptor.*;

/**
 * @author noear 2021/2/17 created
 */
@Mapping("/API/V1/**")
@Controller
public class ApiGateway extends UapiGateway {
    @Override
    protected void register() {
        before(new StartInterceptor()); //开始计时

        after(new OutputBuildInterceptor(new AesEncoder()));//构建输出内容
        after(new OutputInterceptor());//输出
        after(new LogInterceptor());//记录日志
        after(new EndInterceptor("API"));//结束计时，并上报

        addBeans(bw -> "api".equals(bw.tag()));
    }
}