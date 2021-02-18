package admindemo2;

import org.noear.water.WW;
import org.noear.water.WaterClient;
import org.noear.water.model.ConfigM;
import org.noear.weed.DbContext;

/**
 * @author noear 2021/2/16 created
 */
public class Config {
    public static final DbContext water = cfg(WW.water).getDb(true);
    public static final DbContext water_paas = cfg(WW.water_paas).getDb(true);


    public static ConfigM cfg(String key) {
        if (key.indexOf("/") < 0) {
            return WaterClient.Config.get(WW.water, key);
        } else {
            return WaterClient.Config.getByTagKey(key);
        }
    }
}
