package cloud.aispring.mybatisplus.clickhouse.injector;

import cloud.aispring.mybatisplus.clickhouse.enumeration.ClickhouseSqlMethodEnum;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 根据 id 更新 clickhouse 对象
 *
 * @author spring.li
 * @version v1.0
 */
public class UpdateByIdClickHouse extends AbstractClickHouseUpdate {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        ClickhouseSqlMethodEnum sqlMethod = ClickhouseSqlMethodEnum.UPDATE_BY_ID;
        final String additional = optlockVersion(tableInfo) + tableInfo.getLogicDeleteSql(true, true);
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(),
            this.sqlSet(tableInfo.isWithLogicDelete(), false, tableInfo, false, ENTITY, ENTITY_DOT),
            tableInfo.getKeyColumn(), ENTITY_DOT + tableInfo.getKeyProperty(), additional);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addInsertMappedStatement(mapperClass, modelClass,
            sqlMethod.getMethod(), sqlSource, new NoKeyGenerator(), null, null);
    }
}
