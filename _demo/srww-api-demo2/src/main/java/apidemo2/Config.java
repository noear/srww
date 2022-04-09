package apidemo2;

import com.zaxxer.hikari.HikariDataSource;
import org.noear.rock.solon.RockCodeI18nBundleFactory;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.cloud.CloudEventInterceptor;
import org.noear.solon.cloud.CloudJobInterceptor;
import org.noear.solon.cloud.annotation.CloudConfig;
import org.noear.solon.cloud.impl.CloudI18nBundleFactory;
import org.noear.solon.data.cache.CacheService;
import org.noear.solon.data.cache.CacheServiceSupplier;
import org.noear.solon.i18n.I18nBundleFactory;
import org.noear.srww.base.interceptor.BaseEventInterceptor;
import org.noear.srww.base.interceptor.BaseJobInterceptor;
import org.noear.srww.uapi.app.IAppFactory;
import org.noear.srww.uapi.app.impl.RockAppFactoryImpl;
import org.noear.srww.uapi.app.impl.WaterAppFactoryImpl;
import org.noear.weed.cache.LocalCache;
import org.noear.weed.solon.plugin.CacheWrap;

import javax.sql.DataSource;

/**
 * @author noear 2021/2/17 created
 */
@Configuration
public class Config {
    /**
     * 配置数据源
     */
    @Bean(value = "water")
    public DataSource db1(@CloudConfig(name = "water",group = "water") HikariDataSource ds) {
        return ds;
    }

    /**
     * 配置缓存
     * */
    @Bean
    public CacheService cache1(@CloudConfig(name = "cache1", group = "demo") CacheServiceSupplier supplier) {
        return supplier.get();
    }

    /**
     * 任务拦截器（用于记录自己的性能与日志）
     * */
    @Bean
    public CloudJobInterceptor jobInterceptor(){
        return new BaseJobInterceptor();
    }

    /**
     * 事件拦截器（用于记录自己的性能与日志）
     * */
    @Bean
    public CloudEventInterceptor eventInterceptor(){
        return new BaseEventInterceptor();
    }

    /**
     * 使用 Rock 语言包工厂（替换本地语言包）
     * */
    @Bean
    public I18nBundleFactory i18nBundleFactory(){
        return new RockCodeI18nBundleFactory();
    }

    /**
     * 使用 Rock 应用工厂（提供ak/sk）
     * */
    @Bean
    public IAppFactory iAppFactory(){
        return new RockAppFactoryImpl();
    }
}
