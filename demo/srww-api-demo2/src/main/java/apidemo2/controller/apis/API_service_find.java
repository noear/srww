package apidemo2.controller.apis;


import apidemo2.controller.ApiBase;
import apidemo2.dso.service.ConfigService;
import apidemo2.dso.service.RegisterService;
import apidemo2.model.db.WaterCfgPropertiesDo;
import apidemo2.model.db.WaterRegServiceDo;
import apidemo2.model.view.DiscoverVo;
import apidemo2.model.view.ServiceVo;
import org.noear.snack.ONode;
import org.noear.solon.Utils;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.extend.validation.annotation.NotEmpty;
import org.noear.srww.uapi.UapiCodes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author noear 2021/2/12 created
 */
@Component(tag = "api")
public class API_service_find extends ApiBase {

    @Inject
    RegisterService registerService;

    @Inject
    ConfigService configService;

    @NotEmpty({"service"})
    @Mapping("service.find")
    public DiscoverVo exec(String service) throws Throwable {
        DiscoverVo discoverVo = new DiscoverVo();

        WaterCfgPropertiesDo configD = configService.getConfig("_gateway", service);
        List<WaterRegServiceDo> listD = registerService.getServiceList(service);

        if(Utils.isEmpty(configD.value)){
            discoverVo.url = "";
            discoverVo.policy = "default";
        }else{
            if(configD.is_enabled != 1){
                throw UapiCodes.CODE_4001012;
            }

            ONode node = ONode.loadStr(configD.value);
            discoverVo.url = node.get("url").getString();
            discoverVo.policy = node.get("policy").getString();
            if(Utils.isEmpty(discoverVo.policy)){
                discoverVo.policy = "default";
            }
        }

        discoverVo.list = new ArrayList<>();

        for(WaterRegServiceDo s1: listD){
            ServiceVo s2 = new ServiceVo();
            s2.protocol = "http";
            s2.address = s1.address;
            s2.meta = s1.meta;
            s2.weight = 1.0D;

            discoverVo.list.add(s2);
        }

        return discoverVo;
    }
}
