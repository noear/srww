package apidemo1;

import com.zaxxer.hikari.HikariDataSource;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.cloud.annotation.CloudConfig;
import org.noear.solon.core.cache.CacheService;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Result;
import org.noear.solon.extend.validation.ValidatorFailureHandler;
import org.noear.srww.uapi.UapiCodes;
import org.noear.water.utils.CacheWrap;
import org.noear.weed.cache.LocalCache;

import javax.sql.DataSource;
import java.lang.annotation.Annotation;

/**
 * @author noear 2021/2/17 created
 */
@Configuration
public class Config {
    /**
     * 配置数据源
     */
    @Bean(value = "water")
    public DataSource db1(@CloudConfig("water") HikariDataSource ds) {
        return ds;
    }

    /**
     * 配置缓存
     * */
    @Bean
    public CacheService cache1() {
        return CacheWrap.wrap(new LocalCache());
    }

//    @Bean
//    public CacheService cache1(@CloudConfig("water_cache") MemCache cache) {
//        return CacheWrap.wrap(cache);
//    }
}
