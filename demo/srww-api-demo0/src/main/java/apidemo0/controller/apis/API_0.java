package apidemo0.controller.apis;

import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Mapping;
import org.noear.srww.uapi.UapiCodes;

/**
 * @author noear 2021/2/17 created
 */
@Component(tag = "api")
public class API_0 {
    @Mapping
    public void exec(){
        throw  UapiCodes.CODE_4001011;
    }
}
