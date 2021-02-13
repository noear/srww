package org.noear.srww.base;


import org.noear.snack.ONode;
import org.noear.solon.Solon;
import org.noear.solon.SolonApp;
import org.noear.solon.Utils;
import org.noear.solon.core.Plugin;
import org.noear.solon.core.handle.Context;
import org.noear.water.WW;
import org.noear.water.WaterClient;
import org.noear.water.utils.IPUtils;
import org.noear.weed.WeedConfig;

/**
 * @author noear 2021/2/13 created
 */
public class XPluginImp implements Plugin {
    boolean isDebugMode;
    boolean isWeedStyle2;

    @Override
    public void start(SolonApp app) {
        isDebugMode = Solon.cfg().isDebugMode() || Solon.cfg().isFilesMode();
        isWeedStyle2 = "text2".equals(Solon.cfg().get("weed.debug.style"));

        initWeed();
    }


    /**
     * 初始化Weed监听事件
     */
    protected void initWeed() {
        Class<?> bcfClz = Utils.loadClass(WW.clz_BcfClient);

        if (bcfClz == null) {
            initWeedForApi();
        } else {
            initWeedForAdmin();
        }
    }

    private void initWeedForApi() {
        //api项目
        WeedConfig.onExecuteAft(cmd -> {
            if (isDebugMode) {
                if (isWeedStyle2) {
                    System.out.println(cmd.toSqlString());
                } else {
                    System.out.println(cmd.text + "\n" + ONode.stringify(cmd.paramMap()));
                }
            }

            WaterClient.Track.track(Solon.cfg().appName(), cmd, 1000);
        });
    }

    private void initWeedForAdmin() {
        //admin 项目
        WeedConfig.onExecuteAft((cmd) -> {
            if (isDebugMode) {
                if (isWeedStyle2) {
                    System.out.println(cmd.toSqlString());
                } else {
                    System.out.println(cmd.text + "\n" + ONode.stringify(cmd.paramMap()));
                }
            }

            if (cmd.isLog < 0) {
                return;
            }

            Context ctx = Context.current();
            String user_name = user_name(ctx);
            int user_puid = user_puid(ctx);

            if (user_name == null) {
                return;
            }


            String sqlUp = cmd.text.toUpperCase();
            String chkUp = "User_Id=? AND Pass_Wd=? AND Is_Disabled=0".toUpperCase();

            if (cmd.timespan() > 2000 || cmd.isLog > 0 || sqlUp.indexOf("INSERT INTO ") >= 0 || sqlUp.indexOf("UPDATE ") >= 0 || sqlUp.indexOf("DELETE ") >= 0 || sqlUp.indexOf(chkUp) >= 0) {
                WaterClient.Track.track(service_name(), cmd, ctx.userAgent(), ctx.path(), user_puid + "." + user_name, IPUtils.getIP(ctx));
            }
        });
    }

    public String service_name() {
        return Solon.cfg().appName();
    }

    //用于作行为记录
    public int user_puid(Context ctx) {
        if (ctx != null) {
            String tmp = ctx.attr("user_puid", "0");
            return Integer.parseInt(tmp);
        } else {
            return 0;
        }
    }

    public String user_name(Context ctx) {
        if (ctx != null) {
            return ctx.attr("user_name", null);
        } else {
            return null;
        }
    }
}
