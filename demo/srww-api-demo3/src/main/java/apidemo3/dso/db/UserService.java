package apidemo3.dso.db;

import org.noear.solon.annotation.Inject;
import org.noear.solon.extend.aspect.annotation.Service;
import apidemo3.dso.db.mapper.UserMapper;
import apidemo3.model.UserDo;
import apidemo3.model.UserValidateDo;

import java.sql.SQLException;

@Service
public class UserService {
    @Inject
    UserMapper mapper;

    public UserDo getUser(String lkey) throws Exception {
        return mapper.get_user_by_lkey(lkey);
    }

    public UserDo get_user_by_user_id(long user_id) throws SQLException {
        return mapper.get_user_by_user_id(user_id);
    }

    //根据lkey获取用户
    public UserValidateDo get_user_validate(long user_id) throws SQLException {
        return mapper.get_user_validate(user_id);
    }
}
