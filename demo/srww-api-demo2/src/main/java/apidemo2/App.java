package apidemo2;

import org.noear.mlog.Level;
import org.noear.mlog.LoggerFactory;
import org.noear.solon.Solon;

/**
 * @author noear 2021/2/17 created
 */
public class App {
    public static void main(String[] args) {
        LoggerFactory.setLevel(Level.INFO);

        Solon.start(App.class, args);
    }
}
