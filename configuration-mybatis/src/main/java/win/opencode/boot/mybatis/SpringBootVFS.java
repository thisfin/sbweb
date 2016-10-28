package win.opencode.boot.mybatis;

import org.apache.ibatis.io.VFS;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * @author liyi
 * @link https://github.com/mybatis/spring-boot-starter
 */
public class SpringBootVFS
        extends VFS {
    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    protected List<String> list(URL url, String path)
            throws IOException {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver(getClass().getClassLoader());
        Resource[] resources = resourceResolver.getResources("classpath*:" + path + "/**/*.class");
        List<String> resourcePaths = new LinkedList<>();
        for (Resource resource : resources) {
            resourcePaths.add(preserveSubpackageName(resource.getURI(), path));
        }
        return resourcePaths;
    }

    private static String preserveSubpackageName(final URI uri, final String rootPath) {
        final String uriStr = uri.toString();
        final int start = uriStr.indexOf(rootPath);
        return uriStr.substring(start);
    }
}
