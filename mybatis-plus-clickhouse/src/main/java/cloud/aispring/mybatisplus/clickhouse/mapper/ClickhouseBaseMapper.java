package cloud.aispring.mybatisplus.clickhouse.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * base mapper 支持 clickhouse自定义方法
 *
 * @author spring.li
 * @version v1.0
 */
public interface ClickhouseBaseMapper<T> extends BaseMapper<T> {

    /**
     * 根据主键id 更新对象
     *
     * @param entity 对象
     * @return boolean
     * @author spring.li
     */
    int updateByIdClickHouse(@Param("et") T entity);


    /**
     * 根据wrapper 更新对象
     *
     * @param entity        对象
     * @param updateWrapper where
     * @return boolean
     * @author spring.li
     */
    int updateClickHouse(@Param("et") T entity, @Param("ew") Wrapper<T> updateWrapper);

    /**
     * 主键删除
     *
     * @param id id
     * @return int 操作影响行
     */
    int deleteByIdClickHouse(Serializable id);

    /**
     * 根据实体(ID)删除
     *
     * @param entity 实体对象
     * @return int 操作影响行数
     * @since 3.4.4
     */
    int deleteByIdClickHouse(T entity);

    /**
     * 删除（根据ID 批量删除）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     * @return int 操作影响行数
     */
    int deleteBatchIdsClickhouse(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

    /**
     * 根据 columnMap 条件，删除记录
     *
     * @param columnMap 表字段 map 对象
     * @return int 操作影响行数
     */
    int deleteByMapClickhouse(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    /**
     * 根据 entity 条件，删除记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     * @return int 操作影响行数
     */
    int deleteClickhouse(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
}
