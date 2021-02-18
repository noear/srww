package apidemo2.controller;

import apidemo2.controller.interceptor.*;
import org.noear.snack.ONode;
import org.noear.solon.Utils;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.core.handle.Context;
import org.noear.srww.uapi.UapiCodes;
import org.noear.srww.uapi.encoder.Base64Encoder;
import org.noear.srww.uapi.interceptor.*;

import java.util.Base64;

/**
 * @author noear 2021/2/10 created
 */
@Mapping("/api/v1/web/")
@Controller
public class ApiGateway2x extends ApiGateway {
    @Override
    protected void register() {
        // 快速体验：
        //
        // 通过单元测试走
        //
        // http://localhost:8080/api/v1/web/a.b.c
        //
        before(new StartInterceptor());
        before(new AuthJwtInterceptor());

        after(new OutputBuildInterceptor(new Base64Encoder()));
        after(new OutputInterceptor());
        after(new LogInterceptor());
        after(new EndInterceptor("v1.api.web"));

        super.register();
    }

    @Override
    protected boolean allowPathMerging() {
        //网关内的路由，不合并路径
        return false;
    }

    @Override
    protected void handlePre(Context c) throws Throwable {
        String josn_b64 = c.body();
        if(Utils.isEmpty(josn_b64)){
            throw UapiCodes.CODE_4001012;
        }

        String json = new String(Base64.getDecoder().decode(josn_b64));

        ONode node = ONode.loadStr(json);

        //1.设定新路径（网关，将使用新路径做路由）
        c.pathNew(node.get("method").getString());

        //2.转换参数
        node.get("data").forEach((k,v)->{
            c.paramSet(k,v.getString());
        });
    }
}
