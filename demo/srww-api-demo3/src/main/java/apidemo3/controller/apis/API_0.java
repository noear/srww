package apidemo3.controller.apis;

import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Mapping;
import apidemo3.controller.SysCodes;
import apidemo3.controller.UapiBase;

@Component(tag = "api")
public class API_0 extends UapiBase {

    @Mapping
    public void exec() throws Exception {
        throw SysCodes.CODE_4001011;
    }

}
