package win.opencode.sbtest.core.db;

import lombok.Data;

/**
 * Created by wenyou on 16/9/8.
 */
@Data
public class PageQuery<T> {
    private Long startRow;  // 开始条目
    private Long pageSize;  // 页数
    private T obj;          // 查询对象
}
