package admindemo2.controller.dev;

import admindemo2.controller.BaseController2;
import admindemo2.dso.db.DbWaterCfgApi;
import admindemo2.model.TagCountsVo;
import admindemo2.model.water_cfg.ConfigDo;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.core.handle.ModelAndView;
import org.noear.srww.uadmin.model.ViewModel;
import org.noear.water.utils.TextUtils;
import org.noear.weed.DbContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Mapping("dev/ddl")
public class DDLController extends BaseController2 {

    private static final int CFG_TYPE_DB = 10;

    private static final String DDL_COLUMN_NAME = "Create Table";

    private static String buildSqlGetDDL(String table) {
        return "SHOW CREATE TABLE `" + table + "`;";
    }

    @Mapping("")
    public ModelAndView ddl(String tag_name) throws SQLException {
        List<TagCountsVo> tags = DbWaterCfgApi.getConfigTagsByType(CFG_TYPE_DB);

        if (!TextUtils.isEmpty(tag_name)) {
            viewModel.put("tag_name", tag_name);
        } else {
            if (!tags.isEmpty()) {
                viewModel.put("tag_name", tags.get(0).tag);
            } else {
                viewModel.put("tag_name", null);
            }
        }
        viewModel.put("resp", tags);

        return view("dev/ddl");
    }

    @Mapping("inner/{tag}")
    public ModelAndView inner(String tag) throws SQLException {
        List<ConfigDo> cfgs = DbWaterCfgApi.getConfigsByType(tag, CFG_TYPE_DB);

        viewModel.put("cfgs", cfgs);

        viewModel.put("tag_name", tag);

        return view("dev/ddl_inner");
    }

    @Mapping("ajax/tb")
    public ViewModel tb(String tag, String key) throws SQLException {

        ConfigDo cfg = DbWaterCfgApi.getConfigByTagName(tag, key);
        DbContext db = cfg.getDb();

        List<String> tbs = new ArrayList<>();
        db.dbTables().forEach((tw)->{
            tbs.add(tw.getName());
        });
        tbs.sort(String::compareTo);

        return new ViewModel().code(1, "成功").set("tbs", tbs);

    }

    @Mapping("ajax/getddl")
    public ViewModel get(String tag,
                         String key,
                         String tb) throws SQLException {

        DbContext db = DbWaterCfgApi.getConfigByTagName(tag, key).getDb();

        String ddl = db.sql(buildSqlGetDDL(tb)).getDataItem().getString(DDL_COLUMN_NAME);

        return new ViewModel().code(1, "成功").set("ddl", ddl);

    }

}
