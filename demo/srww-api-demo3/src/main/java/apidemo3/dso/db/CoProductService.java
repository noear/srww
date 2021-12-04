package apidemo3.dso.db;

import org.noear.solon.annotation.Inject;
import org.noear.solon.data.annotation.Cache;
import org.noear.solon.extend.aspect.annotation.Service;
import apidemo3.dso.db.mapper.CoProductMapper;
import apidemo3.model.data.CoProductDo;

import java.sql.SQLException;
import java.util.List;

@Service
public class CoProductService {
    @Inject
    CoProductMapper mapper;

    //获取产品详情
    public CoProductDo get_co_product(long product_id) throws SQLException {
        return mapper.get_co_product(product_id);
    }

    //获取产品列表
    @Cache
    public List<CoProductDo> list_co_product() throws SQLException {
        return mapper.list_co_product();
    }
}
