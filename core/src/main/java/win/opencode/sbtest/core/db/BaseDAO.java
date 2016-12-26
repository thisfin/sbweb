package win.opencode.sbtest.core.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by wenyou on 16/9/7.
 */
@Slf4j
public class BaseDAO<DOT extends BaseDO, MapT extends BaseMapper<DOT>> {
    @Autowired
    protected MapT mapper;

    // 新增
    public Long insert(DOT doT)
            throws DBException {
        this.mapper.insert(doT);
        return doT.getId();
    }

    // 根据id更新
    public Long updateById(DOT doT)
            throws DBException {
        return this.mapper.updateById(doT);
    }

    // 根据主键查询
    public DOT selectById(Long id)
            throws DBException, IllegalAccessException, InstantiationException {
        DOT doT = ((Class<DOT>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
        doT.setId(id);
        PageQuery<DOT> pageQuery = new PageQuery();
        pageQuery.setObj(doT);
        List<DOT> list = this.mapper.selectPaginationByDO(pageQuery);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    // 根据do查询
    public List<DOT> selectByDO(DOT doT)
            throws DBException {
        return this.mapper.selectPaginationByDO(new PageQuery<DOT>() {{
            this.setObj(doT);
        }});
    }

    // 根据do分页查询
    public PageResult<DOT> selectPaginationByDO(DOT doT, Long pageNum, Long pageSize)
            throws DBException {
        Long pn = (pageNum == null || pageNum.longValue() < 1) ? 1L : pageNum;
        Long ps = (pageSize == null || pageSize.longValue() < 1) ? 20L : pageSize;

        return new PageResult<DOT>() {{
            this.setPageNum(pn);
            this.setPageSize(ps);
            this.setResultCount(BaseDAO.this.mapper.selectCountByDO(doT));
            this.setResultList(BaseDAO.this.mapper.selectPaginationByDO(new PageQuery<DOT>() {{
                this.setObj(doT);
                this.setPageSize(ps);
                this.setStartRow((pn - 1) * ps);
            }}));
        }};
    }
}
