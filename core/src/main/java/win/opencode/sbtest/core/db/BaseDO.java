package win.opencode.sbtest.core.db;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wenyou on 16/9/7.
 */
@Data
public class BaseDO
        implements Serializable {
    // 主键
    protected Long id;
    // 创建时间
    protected Date gmtCreate;
    // 修改时间
    protected Date gmtModified;
}
