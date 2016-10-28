package win.opencode.boot.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by wenyou on 2016/10/27.
 */
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
//@Configuration
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
@ConditionalOnBean(DataSource.class)
@EnableConfigurationProperties(MybatisProperties.class)
//@MapperScan(basePackages = {"win.opencode.sbtest.db.mapper"}, annotationClass = Mapper.class)
public class MybatisAutoConfiguration {
    @Autowired
    private MybatisProperties properties;
    @Autowired(required = false)
    private Interceptor[] interceptors;
    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void checkConfigFileExists() {
        Assert.state(properties.getConfigLocation() != null, "Cannot find config location:  (please add config file or check your Mybatis configuration)");
        if (this.properties.getConfigLocation() != null) {
            Resource resource = this.resourceLoader.getResource(this.properties.getConfigLocation());
            Assert.state(resource.exists(), "Cannot find config location: " + resource + " (please add config file or check your Mybatis configuration)");
        }
    }

//    @Bean
//    @ConditionalOnMissingBean(SqlSessionFactory.class)
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource)
//            throws Exception {
//        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
//        factory.setDataSource(dataSource);
//        factory.setVfs(SpringBootVFS.class);
//        factory.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
//        if (interceptors != null) {
//            factory.setPlugins(this.interceptors);
//        }
//        return factory.getObject();
//    }

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    //    @Bean
    //    public MetricsInterceptor mybatisMetricsInterceptor() {
    //        return new MetricsInterceptor(properties);
    //    }
    //
    //    @Bean
    //    public MybatisEndpoint mybatisEndpoint(MybatisProperties properties, SqlSessionFactory sqlSessionFactory, MetricsInterceptor interceptor) {
    //        return new MybatisEndpoint(properties, sqlSessionFactory, interceptor);
    //    }
}
