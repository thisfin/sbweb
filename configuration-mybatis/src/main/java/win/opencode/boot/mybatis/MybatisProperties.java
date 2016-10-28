package win.opencode.boot.mybatis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liyi
 */
@Component
@ConfigurationProperties("win.opencode.mybatis")    // 前缀
@Data
public class MybatisProperties {
    // Config file path
    private String configLocation;
    // scan package for mapper class
    private String basePackage;
}
