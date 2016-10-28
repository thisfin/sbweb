package win.opencode.sbtest.db.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import win.opencode.sbtest.core.db.BaseDAO;
import win.opencode.sbtest.db.dataobject.SimpleUserDO;
import win.opencode.sbtest.db.mapper.SimpleUserMapper;

/**
 * Created by wenyou on 2016/10/27.
 */
@Component
@Slf4j
public class SimpleUserDAO
        extends BaseDAO<SimpleUserDO, SimpleUserMapper> {
}
