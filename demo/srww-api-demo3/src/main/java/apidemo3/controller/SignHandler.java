package apidemo3.controller;

import org.noear.snack.ONode;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.core.handle.Context;
import org.noear.rock.RockClient;
import org.noear.rock.model.AppModel;
import org.noear.water.WaterClient;
import org.noear.water.utils.EncryptUtils;

@Controller
public class SignHandler {

    @Mapping("/SIGN/{cmd}")
    public void handle(Context ctx, String cmd) throws Exception {

        String ip = ctx.realIp();

        if (!WaterClient.Whitelist.existsOfServerIp(ip)) {
            ctx.statusSet(403);
            ctx.setHandled(true);
            return;
        }

        ctx.charset("UTF-8");

        String arg = ctx.param("p");//访问参数
        String cid = ctx.param("cid");//访问参数

        ONode n = new ONode();

        try {
            AppModel app = RockClient.getAppByID(Integer.parseInt(cid));

            String sign = buildSign(app, cmd, arg);

            n.set("sign", sign);
            String r = EncryptUtils.aesEncrypt(arg, app.app_secret_key, null);
            n.set("p64", r);

            ctx.outputAsJson(n.toJson());
        } catch (Exception ex) {
            n.set("error", ex.getMessage());
            ctx.outputAsJson(n.toJson());
        }
    }

    private String buildSign(AppModel app, String cmd, String params) {

        StringBuilder sb = new StringBuilder();

        sb.append(cmd).append("#").append(params).append("#").append(app.app_secret_key);

        return EncryptUtils.sha256(sb.toString());
    }
}
