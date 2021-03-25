package apidemo2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.noear.rock.utils.I18nUtils;
import org.noear.solon.test.SolonJUnit4ClassRunner;
import org.noear.solon.test.SolonTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author noear 2021/2/24 created
 */
@RunWith(SolonJUnit4ClassRunner.class)
@SolonTest(App.class)
public class I18nTest {
    Logger logger = LoggerFactory.getLogger(LogTest1x.class);

    @Test
    public void test0() throws Exception {
        String note = I18nUtils.getByName("title", "");

        assert note != null;

        logger.debug(note);
    }

    @Test
    public void test1() throws Exception {
        String note = I18nUtils.getByNameAndFormat("hello", "", "noear");

        assert note != null;
        assert note.indexOf("noear") >= 0;

        logger.debug(note);
    }
}
