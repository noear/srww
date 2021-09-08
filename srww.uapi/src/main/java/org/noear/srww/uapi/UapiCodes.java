package org.noear.srww.uapi;

import org.noear.rock.i18n.I18nUtils;
import org.noear.solon.Solon;
import org.noear.solon.Utils;
import org.noear.solon.annotation.Note;
import org.noear.water.utils.TextUtils;

import java.sql.SQLException;

public class UapiCodes {
    /**
     * 成功
     */
    @Note("成功")
    public static final UapiCode CODE_200 = new UapiCode(200, "Succeed");

    /**
     * 失败，未知错误
     */
    @Note("失败，未知错误")
    public static final UapiCode CODE_400 = new UapiCode(400, "Unknown error");

    /**
     * 失败，未知错误
     */
    @Note("请求的通道不存在或不再支持")
    public static final UapiCode CODE_4001010 = new UapiCode(4001010, "The channel not exist");


    /**
     * 请求的接口不存在或不再支持
     */
    @Note("请求的接口不存在或不再支持")
    public static final UapiCode CODE_4001011 = new UapiCode(4001011, "The api not exist");

    /**
     * 请求的不符合规范
     */
    @Note("请求的不符合规范")
    public static final UapiCode CODE_4001012 = new UapiCode(4001012, "The request is not up to par");


    /**
     * 请求的签名校验失败
     */
    @Note("请求的签名校验失败")
    public static final UapiCode CODE_4001013 = new UapiCode(4001013, "The signature error");

    /**
     * 请求的参数缺少或有错误
     */
    @Note("请求的参数缺少或有错误")
    public static final UapiCode CODE_4001014(String names) {
        return new UapiCode(4001014, names);
    }

    /**
     * 请求太频繁了
     */
    @Note("请求太频繁了")
    public static final UapiCode CODE_4001015 = new UapiCode(4001015, "Too many requests");
    /**
     * 请求不在白名单
     */
    @Note("请求不在白名单")
    public static final UapiCode CODE_4001016 = new UapiCode(4001016, "The request is not in the whitelist");
    /**
     * 请求容量超限
     */
    @Note("请求容量超限")
    public static final UapiCode CODE_4001017 = new UapiCode(4001017, "Request capacity exceeds limit");


    @Note("请求加解密失败")
    public static final UapiCode CODE_4001018 = new UapiCode(4001018, "The request for encryption and decryption failed");


    /**
     * 登录已失效
     */
    @Note("登录已失效或未登录")
    public static final UapiCode CODE_4001021 = new UapiCode(4001021, "Login is invalid or not logged in");



    public static final String CODE_note(String lang, UapiCode uapiCode) throws SQLException {
        if (Utils.isNotEmpty(Solon.cfg().appName())) {
            if (lang == null) {
                lang = "";
            }

            String description = I18nUtils.getByCode(uapiCode.getCode(), lang);

            if (TextUtils.isEmpty(description) == false) {
                return description;
            }
        }

        if (uapiCode.getCode() == 4001014) {
            return "Parameter missing or error" + (uapiCode.getDescription() == null ? "" : "(" + uapiCode.getDescription() + ")");
        } else {
            return uapiCode.getDescription();
        }
    }

    public static final String CODE_note(String lang, int uapiCode) throws SQLException {
        if (Utils.isNotEmpty(Solon.cfg().appName())) {
            if (lang == null) {
                lang = "";
            }

            String description = I18nUtils.getByCode(uapiCode, lang);

            if (TextUtils.isEmpty(description) == false) {
                return description;
            }
        }

        return "";
    }
}
