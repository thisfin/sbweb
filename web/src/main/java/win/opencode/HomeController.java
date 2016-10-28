package win.opencode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import win.opencode.boot.mybatis.MybatisProperties;
import win.opencode.sbtest.core.db.DBException;
import win.opencode.sbtest.db.dao.SimpleUserDAO;
import win.opencode.sbtest.db.dataobject.SimpleUserDO;

/**
 * Created by wenyou on 2016/10/25.
 */
@RestController
@Slf4j
public class HomeController {
    @Autowired
    private SimpleUserDAO simpleUserDAO;

    @Autowired
    private MybatisProperties mybatisProperties;

    @RequestMapping("/")
    String home()
            throws DBException, JsonProcessingException {
        log.error("home");

        String result = new ObjectMapper().writeValueAsString(simpleUserDAO.selectByDO(new SimpleUserDO() {{
            this.setId(1L);
        }}));
        log.error(result);

//        String result = this.mybatisProperties.getBasePackage();

        return result;
        //        return "hello";
    }
}
