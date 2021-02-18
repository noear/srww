package apidemo1.controller.apis;

import apidemo1.controller.ApiBase;
import apidemo1.dso.mapper.RegisterMapper;
import apidemo1.model.db.WaterRegServiceDo;
import cn.hutool.crypto.SecureUtil;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.extend.validation.annotation.NotEmpty;
import org.noear.weed.annotation.Db;

/**
 * @author noear 2021/2/11 created
 */
@Component(tag = "api")
public class API_service_reg extends ApiBase {

    @Db
    RegisterMapper registerMapper;

    @NotEmpty({"service", "address"})
    @Mapping("service.reg")
    public void exec(String service, String address, String meta, int is_unstable, String check_url, String code_location, int check_type) throws Throwable {
        WaterRegServiceDo model = new WaterRegServiceDo();

        //1.拼装一个模型
        model.key = SecureUtil.md5(service + "#" + address);
        model.name = service;
        model.address = address;
        model.meta = meta;
        model.is_unstable = is_unstable;

        //2.更新服务信息
        boolean isOk = registerMapper.udpService(model) > 0;

        if (!isOk) {
            //3.如果更新影响数为0，则需要插入
            registerMapper.addService(model);
        }
    }
}
