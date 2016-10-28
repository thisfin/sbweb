package win.opencode.sbtest.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import win.opencode.sbtest.core.db.BaseMapper;
import win.opencode.sbtest.db.dataobject.SimpleUserDO;

/**
 * Created by wenyou on 2016/10/27.
 */
@Mapper
public interface SimpleUserMapper
        extends BaseMapper<SimpleUserDO> {
}
