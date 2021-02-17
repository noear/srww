package apidemo2.controller.cmds;

import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Mapping;
import apidemo2.controller.SysCodes;
import apidemo2.controller.UapiBase;

@Component(tag = "cmd")
public class CMD_0 extends UapiBase {

    @Mapping
    public void exec() throws Exception {
        throw SysCodes.CODE_4001011;
    }
}
