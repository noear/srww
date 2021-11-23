package apidemo2.model.view;

import lombok.Builder;

import java.io.Serializable;

/**
 * @author noear 2021/2/11 created
 */
@Builder
public class ConfigVo implements Serializable {
    public String key;
    public String value;
    public long lastModified;
}
