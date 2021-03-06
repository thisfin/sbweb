package win.opencode.boot.endpoint;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liyi
 */
@Configuration
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class MybatisEndpointConfiguration {
    @Bean
    public MybatisEndpoint mybatisEndpoint(MybatisProperties mybatisProperties, SqlSessionFactory sqlSessionFactory) {
        return new MybatisEndpoint(mybatisProperties, sqlSessionFactory);
    }
}
