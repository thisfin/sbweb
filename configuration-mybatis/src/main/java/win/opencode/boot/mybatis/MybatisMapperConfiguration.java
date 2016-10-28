package win.opencode.boot.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author liyi
 */
@AutoConfigureAfter(DataSourceAutoConfiguration.class)  // 顺序
//@Configuration
@EnableConfigurationProperties(MybatisProperties.class) // 将此配置加载到上线文环境
@Slf4j
public class MybatisMapperConfiguration
        implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    //    @Autowired
    //    private static MybatisProperties mybatisProperties;
    //    private ApplicationContext applicationContext;
    //
    //    public void setApplicationContext(ApplicationContext applicationContext)
    //            throws BeansException {
    //        this.applicationContext = applicationContext;
    //    }
    //
    //    @Bean
    //    @ConditionalOnProperty(value = "spring.mybatis.scan-base-package")
    //    @ConditionalOnMissingBean(MapperScannerConfigurer.class)
    //    public MapperScannerConfigurer mapperScannerConfigurer() {
    //        MapperScannerConfigurer scanner = new MapperScannerConfigurer();
    //        Environment env = applicationContext.getEnvironment();
    //        String scanBasePackage = env.getProperty("spring.mybatis.scan-base-package");
    //        scanner.setBasePackage(scanBasePackage);
    //        scanner.setAnnotationClass(Mapper.class);
    //        return scanner;
    //    }

    // basePackage属性是让你为映射器接口文件设置基本的包路径. 可以使用分号或逗号作为分隔符设置多于一个的包路径.
    // 每个映射器将会在指定的包路径中递归地被搜索到.
    // MapperScannerConfigurer属性不支持使用了PropertyPlaceholderConfigurer的属性替换, 因为会在Spring其中之前来它加载.
    // 但是可以使用PropertiesFactoryBean和SpEL表达式来作为替代.
//    @Bean
//    @ConditionalOnMissingBean(SqlSessionFactory.class)  // 如果未注入时才运行
//    public MapperScannerConfigurer mapperScannerConfigurer() {
//        Environment env = this.applicationContext.getEnvironment();
//        String scanBasePackage = env.getProperty("win.opencode.mybatis.basePackage");
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setBasePackage(scanBasePackage);
//        return mapperScannerConfigurer;
//    }

    //    @Bean
    //    @ConditionalOnMissingBean(SqlSessionFactory.class)  // 如果未注入时才运行
    //    public MapperScannerConfigurer mapperScannerConfigurer(MybatisProperties mybatisProperties) {
    //                    Environment env = this.applicationContext.getEnvironment();
    //                    String scanBasePackage = env.getProperty("win.opencode.mybatis.basePackage");
    //        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
    //        mapperScannerConfigurer.setBasePackage(mybatisProperties.getBasePackage());
    //        return mapperScannerConfigurer;
    //    }
}
