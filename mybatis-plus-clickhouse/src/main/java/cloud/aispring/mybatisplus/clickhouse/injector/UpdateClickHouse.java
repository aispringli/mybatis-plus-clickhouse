package cloud.aispring.mybatisplus.clickhouse.injector;

import cloud.aispring.mybatisplus.clickhouse.enumeration.ClickhouseSqlMethodEnum;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 根据 wrapper 更新 clickhouse 对象
 *
 * @author spring.li
 * @version v1.0
 */
public class UpdateClickHouse extends AbstractClickHouseUpdate {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        ClickhouseSqlMethodEnum sqlMethod = ClickhouseSqlMethodEnum.UPDATE;
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(),
            sqlSet(true, true, tableInfo, true, ENTITY, ENTITY_DOT),
            sqlWhereEntityWrapper(true, tableInfo), sqlComment());
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addUpdateMappedStatement(mapperClass, modelClass, sqlMethod.getMethod(), sqlSource);
    }
}
