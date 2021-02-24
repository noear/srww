package apidemo2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.noear.rock.RockClient;
import org.noear.solon.test.SolonJUnit4ClassRunner;
import org.noear.solon.test.SolonTest;

import java.sql.SQLException;

/**
 * @author noear 2021/2/24 created
 */
@RunWith(SolonJUnit4ClassRunner.class)
@SolonTest(App.class)
public class I18nTest {
    @Test
    public void test0() throws SQLException {
        String note = RockClient.getServiceI18nsByLang("demoapi","")
                .get("title");

        System.out.println(note);
    }
}
