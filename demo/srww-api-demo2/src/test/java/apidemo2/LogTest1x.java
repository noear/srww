package apidemo2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.noear.mlog.Logger;
import org.noear.solon.test.SolonJUnit4ClassRunner;
import org.noear.solon.test.SolonTest;

/**
 * @author noear 2021/2/23 created
 */
@RunWith(SolonJUnit4ClassRunner.class)
@SolonTest(App.class)
public class LogTest1x {
    Logger logger = Logger.get(LogTest1x.class);

    @Test
    public void test() throws Throwable{
        logger.trace("test");
        logger.debug("test");
        logger.info("test");
        logger.warn("test");
        logger.error("test");

        Thread.sleep(1000);
    }
}
