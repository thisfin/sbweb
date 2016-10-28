package win.opencode.sbtest.core.db;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wenyou on 16/9/8.
 */
@Data
public class PageResult<T>
        implements Serializable {
    private Long pageNum;           // 页码
    private Long pageSize;          // 页数
    private Long resultCount;       // 总数
    private List<T> resultList;     // 结果
}
