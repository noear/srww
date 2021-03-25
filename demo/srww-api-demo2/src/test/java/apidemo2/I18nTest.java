package apidemo2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.noear.rock.utils.I18nUtils;
import org.noear.solon.test.SolonJUnit4ClassRunner;
import org.noear.solon.test.SolonTest;

/**
 * @author noear 2021/2/24 created
 */
@RunWith(SolonJUnit4ClassRunner.class)
@SolonTest(App.class)
public class I18nTest {
    @Test
    public void test0() throws Exception {
        String note = I18nUtils.getByName("title", "");

        System.out.println(note);
    }

    @Test
    public void test1() throws Exception {
        String note = I18nUtils.getByNameAndFormat("hello", "", "noear");

        System.out.println(note);
    }
}
