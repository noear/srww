package apidemo2;

import com.zaxxer.hikari.HikariDataSource;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Inject;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Result;
import org.noear.solon.extend.validation.ValidatorFailureHandler;
import org.noear.srww.uapi.UapiCodes;

import javax.sql.DataSource;
import java.lang.annotation.Annotation;

/**
 * @author noear 2021/2/17 created
 */
public class Config {
    public static final String logger = "demo03_api_log";

    /**
     * 配置数据源
     * */
    @Bean
    public DataSource db1(@Inject("${test.db1}") HikariDataSource ds) {
        return ds;
    }

    /**
     * 配置验证注解的错误处理
     * */
    @Bean
    public ValidatorFailureHandler failureHandler(){
        return new ValidatorFailureHandler() {
            @Override
            public boolean onFailure(Context ctx, Annotation ano, Result result, String message) {
                throw UapiCodes.CODE_4001012;
            }
        };
    }
}
