package apis;

import apidemo2.App;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.noear.solon.test.SolonJUnit4ClassRunner;
import org.noear.solon.test.SolonTest;

/**
 * @author noear 2021/12/4 created
 */
@Slf4j
@RunWith(SolonJUnit4ClassRunner.class)
@SolonTest(App.class)
public class Api_service extends ApiTestBaseOfApp{
}
