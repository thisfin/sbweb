package win.opencode.sbtest.core.db;

import java.util.List;

/**
 * Created by wenyou on 16/9/7.
 */
public interface BaseMapper<T extends BaseDO> {
    // 增加
    Long insert(T t);
    // 根据id更新
    Long updateById(T t);
    // 分页查询
    List<T> selectPaginationByDO(PageQuery<T> pageQuery);
    // 查询总数
    Long selectCountByDO(T t);
}
