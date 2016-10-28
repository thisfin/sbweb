package win.opencode.sbtest.db.dataobject;

import lombok.Data;
import win.opencode.sbtest.core.db.BaseDO;

/**
 * Created by wenyou on 2016/10/27.
 */
@Data
public class SimpleUserDO
        extends BaseDO {
    private String name;
    private Integer age;
}
