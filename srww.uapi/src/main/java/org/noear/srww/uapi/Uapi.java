package org.noear.srww.uapi;

import org.noear.rock.RockClient;
import org.noear.rock.model.AppModel;
import org.noear.srww.uapi.common.Attrs;
import org.noear.solon.annotation.Singleton;
import org.noear.solon.core.handle.Context;
import org.noear.water.utils.IPUtils;

import java.sql.SQLException;

@Singleton(false)
public class Uapi {
    protected final Context ctx;

    public Uapi() {
        ctx = Context.current();
    }


    public static <T extends Uapi> T current() {
        return (T) Context.current().controller();
    }

    /**
     * 接口名称（不一定会有）
     */
    public String name() {
        if (ctx == null) {
            return null;
        } else {
            return ctx.attr(Attrs.handler_name);
        }
    }

    /**
     * 上下文对象
     */
    public Context context() {
        return ctx;
    }


    private String _outs;
    private String _nouts;

    /**
     * 检查参数是否需要输出
     */
    protected boolean isOut(String key) {
        if (this._outs == null) {
            this._outs = ctx.param("outs");
        }

        if (this._outs == null) {
            return false;
        } else {
            return this._outs.indexOf(key) > -1;
        }
    }

    /**
     * 检查是否不需要输出
     */
    protected boolean isNotout(String key) {
        if (this._nouts == null) {
            this._nouts = ctx.param("nouts");
        }

        if (this._nouts == null) {
            return false;
        } else {
            return this._nouts.indexOf(key) > -1;
        }
    }


    ///////////
    private String _ip;

    public String ip() {
        if (_ip == null) {
            _ip = IPUtils.getIP(ctx);
        }

        return _ip;
    }

    private AppModel _app;

    public AppModel getApp() throws SQLException {
        if (_app == null) {
            if (getAppId() > 0) {
                _app = getApp(getAppId());
            } else {
                _app = new AppModel();
            }
        }

        return _app;
    }

    public AppModel getApp(int appID) throws SQLException {
        return RockClient.getAppByID(appID);
    }

    private int appId = -1;

    public int getAppId() {
        if (appId < 0) {
            appId = ctx.paramAsInt(Attrs.app_id);
        }

        return appId;
    }

    public int getAgroupId() throws SQLException {
        if (getAppId() > 0) {
            return getApp().agroup_id;
        } else {
            return 0;
        }
    }

    private int verId = -1;

    public int getVerId() {
        if (verId < 0) {
            verId = ctx.paramAsInt(Attrs.ver_id);
        }

        return verId;
    }

    public long getUserID() {
        return 0;
    }

    /**
     * 原始输入
     */
    public String getOrgInput() {
        return ctx.attr(Attrs.org_input);
    }

    /**
     * 原始输入签名
     */
    public String getOrgInputSign() {
        return ctx.attr(Attrs.org_input_sign);
    }

    /**
     * 原始输入签名
     */
    public String getOrgInputTimestamp() {
        return ctx.attr(Attrs.org_input_timestamp);
    }

    /**
     * 原始输出（即未加密之前）
     */
    public String getOrgOutput() {
        return ctx.attr(Attrs.org_output);
    }
}
