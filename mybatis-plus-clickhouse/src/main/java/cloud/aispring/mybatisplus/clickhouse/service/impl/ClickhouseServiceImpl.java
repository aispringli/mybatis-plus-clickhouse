package cloud.aispring.mybatisplus.clickhouse.service.impl;

import cloud.aispring.mybatisplus.clickhouse.enumeration.ClickhouseSqlMethodEnum;
import cloud.aispring.mybatisplus.clickhouse.mapper.ClickhouseBaseMapper;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.ds.ItemDataSource;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.zaxxer.hikari.HikariDataSource;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * repository clickhouse 实现根据数据源自动调整内部实现调用mapper方法
 *
 * @author spring.li
 * @version v1.0
 */
public class ClickhouseServiceImpl<M extends ClickhouseBaseMapper<T>, T> extends ServiceImpl<M, T> {

    @Autowired
    private DataSource dynamicRoutingDataSource;

    private boolean notClickhouse() {
        String clickhouseDriverClassName = "ClickHouseDriver";
        if (dynamicRoutingDataSource instanceof DynamicRoutingDataSource) {
            DataSource dataSource = ((DynamicRoutingDataSource) dynamicRoutingDataSource).getDataSource(DynamicDataSourceContextHolder.peek());
            if (dataSource instanceof ItemDataSource) {
                if (((ItemDataSource) dataSource).getDataSource() instanceof HikariDataSource) {
                    return !((HikariDataSource) ((ItemDataSource) dataSource).getDataSource()).getDriverClassName().contains(clickhouseDriverClassName);
                }
            }
        }

        return true;
    }

    /**
     * 获取mapperStatementId
     *
     * @param sqlMethod 方法名
     * @return 命名id
     * @since 3.4.0
     */
    @Override
    public String getSqlStatement(SqlMethod sqlMethod) {
        if (notClickhouse()) {
            return super.getSqlStatement(sqlMethod);
        }
        ClickhouseSqlMethodEnum clickhouseSqlMethod = null;
        if (SqlMethod.UPDATE_BY_ID.equals(sqlMethod)) {
            clickhouseSqlMethod = ClickhouseSqlMethodEnum.UPDATE_BY_ID;
        }
        if (clickhouseSqlMethod == null) {
            return super.getSqlStatement(sqlMethod);
        }
        return mapperClass.getName() + StringPool.DOT + clickhouseSqlMethod.getMethod();
    }

    @Override
    public boolean updateById(T entity) {
        if (notClickhouse()) {
            return super.updateById(entity);
        }
        return SqlHelper.retBool(getBaseMapper().updateByIdClickHouse(entity));
    }

    /**
     * 根据 whereEntity 条件，更新记录
     *
     * @param entity        实体对象
     * @param updateWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper}
     */
    @Override
    public boolean update(T entity, Wrapper<T> updateWrapper) {
        if (notClickhouse()) {
            return super.update(entity, updateWrapper);
        }
        return SqlHelper.retBool(getBaseMapper().updateClickHouse(entity, updateWrapper));
    }

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     */
    @Override
    public boolean removeById(Serializable id) {
        if (notClickhouse()) {
            return super.removeById(id);
        }
        return SqlHelper.retBool(getBaseMapper().deleteByIdClickHouse(id));
    }

    /**
     * 根据实体(ID)删除
     *
     * @param entity 实体
     * @since 3.4.4
     */
    @Override
    public boolean removeById(T entity) {
        if (notClickhouse()) {
            return super.removeById(entity);
        }
        return SqlHelper.retBool(getBaseMapper().deleteByIdClickHouse(entity));
    }

    /**
     * 根据 columnMap 条件，删除记录
     *
     * @param columnMap 表字段 map 对象
     */
    @Override
    public boolean removeByMap(Map<String, Object> columnMap) {
        if (notClickhouse()) {
            return super.removeByMap(columnMap);
        }
        return SqlHelper.retBool(getBaseMapper().deleteByMapClickhouse(columnMap));
    }

    /**
     * 根据 entity 条件，删除记录
     *
     * @param queryWrapper 实体包装类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    @Override
    public boolean remove(Wrapper<T> queryWrapper) {
        if (notClickhouse()) {
            return super.remove(queryWrapper);
        }
        return SqlHelper.retBool(getBaseMapper().deleteClickhouse(queryWrapper));
    }

    /**
     * 删除（根据ID 批量删除）
     *
     * @param idList 主键ID列表
     */
    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        if (notClickhouse()) {
            return super.removeByIds(idList);
        }
        return SqlHelper.retBool(getBaseMapper().deleteBatchIdsClickhouse(idList));
    }
}
