package admindemo1.dso;


import admindemo1.dso.dao.DbWaterCfgApi;
import admindemo1.model.water_cfg.EnumDo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

public class EnumUtil {
    public static List<EnumDo> get(String group) throws SQLException {
        List<EnumDo> enumList = new ArrayList<>();
        Properties prop = DbWaterCfgApi.getConfigByTagName("_system", group).getProp();
        prop.forEach((k, v) -> {
            EnumDo m = new EnumDo();
            m.value = k.toString();
            m.title = v.toString();
            m.enum_id = Integer.parseInt(m.value);
            enumList.add(m);
        });
        enumList.sort(Comparator.comparing(m -> m.enum_id));

        return enumList;
    }
}
