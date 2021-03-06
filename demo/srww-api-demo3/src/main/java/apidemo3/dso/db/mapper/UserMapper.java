package apidemo3.dso.db.mapper;

import java.sql.SQLException;

import org.noear.weed.annotation.Db;
import org.noear.weed.xml.Namespace;
import apidemo3.model.*;

@Db("dobbin")
@Namespace("zm.data.dobbin.bull.dso.db.mapper.UserMapper")
public interface UserMapper{
    //根据user_id获取用户
    UserDo get_user_by_user_id(long user_id) throws SQLException;

    //根据lkey获取用户
    UserDo get_user_by_lkey(String lkey) throws SQLException;

    //根据用户ID获取用户认证信息
    UserValidateDo get_user_validate(long user_id) throws SQLException;
}
