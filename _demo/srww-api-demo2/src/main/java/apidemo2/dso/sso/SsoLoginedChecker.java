package apidemo2.dso.sso;

import org.noear.solon.Solon;
import org.noear.solon.Utils;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.noear.solon.core.handle.Context;
import org.noear.solon.validation.annotation.Logined;
import org.noear.solon.validation.annotation.LoginedChecker;
import org.noear.srww.uapi.common.Attrs;

/**
 * 单点登录检测器（增加登录注解检查器）
 * <code><pre>
 * //使用时用 @Logined 进行控制
 * @Controller
 * public class DemoController{
 *     @Logined
 *     @Mapping("/get")
 *     public String get(){
 *         return "test";
 *     }
 * }
 * </pre></code>
 *
 * @author noear 2021/9/5 created
 */
@Component
public class SsoLoginedChecker implements LoginedChecker {

    public static final String SESSION_USER_ID = "user_id";
    public static final String SESSION_SSO_KEY = "sso_key";

    @Inject
    SsoService ssoService;

    @Override
    public boolean check(Logined anno, Context ctx, String userKeyName) {
        if (ctx.header(Attrs.h_token) == null) {
            return false;
        }

        long user_id = ctx.sessionAsLong(SESSION_USER_ID, 0L);
        String sso_key = ctx.session(SESSION_SSO_KEY, "");

        if (user_id == 0) {
            return false;
        }


        //为单测增加去sso支持（不然没法跑） //下面的代码是控制sso的关键
        if(Solon.cfg().isDebugMode()){
            return true;
        }

        if (Utils.isEmpty(sso_key)) {
            return false;
        }

        String sso_key0 = ssoService.getUserSsoKey(user_id);
        return sso_key.equals(sso_key0);
    }
}
