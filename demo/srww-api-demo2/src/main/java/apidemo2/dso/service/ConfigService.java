package apidemo2.dso.service;

import java.math.*;
import java.sql.SQLException;
import java.time.*;
import java.util.*;

import org.noear.solon.annotation.Inject;
import org.noear.solon.extend.aspect.annotation.Service;
import org.noear.weed.DataItem;
import org.noear.weed.DataList;
import org.noear.weed.annotation.Db;
import apidemo2.model.db.*;

@Service
public class ConfigService{
    @Inject
    apidemo2.dso.mapper.ConfigMapper mapper;

    //获取配置列表
    public List<WaterCfgPropertiesDo> getConfigsByTag(String tag) throws SQLException{
        return mapper.getConfigsByTag(tag);
    }

    //获取配置
    public WaterCfgPropertiesDo getConfig(String tag, String key) throws SQLException{
        return mapper.getConfig(tag,key);
    }

    //设置配置
    public long addConfig(String tag, String key, String value) throws SQLException{
        return mapper.addConfig(tag,key,value);
    }

    //设置配置
    public long udpConfig(String tag, String key, String value) throws SQLException{
        return mapper.udpConfig(tag,key,value);
    }
}
