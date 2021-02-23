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
        //1.查找配置
        WaterCfgPropertiesDo cfg = configService.getConfig(tag, key);

        if (cfg.row_id == 0) {
            //2.如果没有，新增配置
            configService.addConfig(tag, key, value);
        } else {
            if (cfg.is_editable == false) {
                return;
            }

            //3.如果可以修改，则修改值
            configService.udpConfig(tag, key, value);
        }
    }
}
