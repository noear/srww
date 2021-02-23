package apidemo1.dso.service;

import java.math.*;
import java.sql.SQLException;
import java.time.*;
import java.util.*;

import org.noear.solon.annotation.Inject;
import org.noear.solon.extend.aspect.annotation.Service;
import org.noear.solon.extend.data.annotation.Cache;
import org.noear.solon.extend.data.annotation.CachePut;
import org.noear.solon.extend.data.annotation.CacheRemove;
import org.noear.solon.extend.data.annotation.Tran;
import org.noear.weed.DataItem;
import org.noear.weed.DataList;
import org.noear.weed.annotation.Db;
import apidemo1.model.db.*;

@Service
public class ConfigService{
    @Inject
    apidemo1.dso.mapper.ConfigMapper mapper;

    //获取配置列表
    @Cache(tags = "config_tag_${tag}")
    public List<WaterCfgPropertiesDo> getConfigsByTag(String tag) throws SQLException {
        return mapper.getConfigsByTag(tag);
    }

    //获取配置
    @Cache(tags = "config_tagKey_${tag}_${key}")
    public WaterCfgPropertiesDo getConfig(String tag, String key) throws SQLException {
        return mapper.getConfig(tag, key);
    }

    //设置配置（cache::批量的只能移掉）
    @Tran
    @CacheRemove(tags = "config_tag_${tag}")
    public long addConfig(String tag, String key, String value) throws SQLException {
        return mapper.addConfig(tag, key, value);
    }

    //设置配置（cache::单个的可以直接更新）
    @CachePut(tags = "config_tagKey_${tag}_${key}")
    public WaterCfgPropertiesDo udpConfig(String tag, String key, String value) throws SQLException {
        mapper.udpConfig(tag, key, value);
        return mapper.getConfig(tag, key);
    }
}
