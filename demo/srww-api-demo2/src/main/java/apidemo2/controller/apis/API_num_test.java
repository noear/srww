package apidemo2.controller.apis;

import apidemo2.controller.ApiBase;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.core.handle.Result;
import org.noear.solon.extend.validation.annotation.Logined;
import org.noear.solon.extend.validation.annotation.NotEmpty;
import org.noear.solon.extend.validation.annotation.NotZero;

/**
 * @author noear 2021/4/2 created
 */
@Component(tag = "api")
public class API_num_test extends ApiBase {
    @Mapping("num.test")
    @NotZero({"product_type"})
    @NotEmpty({"is_home"})
    public Result exec(Integer product_type, Integer is_home, Integer page, Integer page_size, String coin_type){
        return Result.succeed();
    }
}
