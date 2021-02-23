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
public class RegisterService{
    @Inject
    apidemo2.dso.mapper.RegisterMapper mapper;

    //添加服务
    public long addService(WaterRegServiceDo model) throws SQLException{
        return mapper.addService(model);
    }

    //更新服务
    public long udpService(WaterRegServiceDo model) throws SQLException{
        return mapper.udpService(model);
    }

    //删除服务
    public long delService(String key) throws SQLException{
        return mapper.delService(key);
    }

    //查找服务
    public List<WaterRegServiceDo> getServiceList(String name) throws SQLException{
        return mapper.getServiceList(name);
    }
}
