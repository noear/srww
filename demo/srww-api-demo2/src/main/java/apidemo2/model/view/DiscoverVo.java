package apidemo2.model.view;

import java.io.Serializable;
import java.util.List;

/**
 * @author noear 2021/2/12 created
 */
public class DiscoverVo implements Serializable {
    public String url;
    public String policy;
    public List<ServiceVo> list;
}
