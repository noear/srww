package apidemo3.dso.db.mapper;

import java.sql.SQLException;
import java.util.*;

import apidemo3.model.data.CoProductDo;
import org.noear.weed.annotation.Db;
import org.noear.weed.xml.Namespace;

@Db("sponge_sugar")
@Namespace("zm.data.dobbin.bull.dso.db.mapper.CoProductMapper")
public interface CoProductMapper{
    //获取产品详情
    CoProductDo get_co_product(long product_id) throws SQLException;

    //获取产品列表
    List<CoProductDo> list_co_product() throws SQLException;
}
