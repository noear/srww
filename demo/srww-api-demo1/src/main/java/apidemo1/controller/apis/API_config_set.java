package apidemo1.controller.apis;


import apidemo1.controller.ApiBase;
import apidemo1.dso.mapper.ConfigMapper;
import apidemo1.dso.service.ConfigService;
import apidemo1.model.db.WaterCfgPropertiesDo;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.extend.validation.annotation.NotEmpty;
import org.noear.weed.annotation.Db;

/**
 * @author noear 2021/2/11 created
 */
@Component(tag = "api")
public class API_config_set extends ApiBase {

    @Inject
    ConfigService configService;

    @NotEmpty({"tag", "key"})
    @Mapping("config.set")
    public void exec(String tag, String key, String value) throws Throwable {
        configService.setConfig(tag, key, value);
    }
}
