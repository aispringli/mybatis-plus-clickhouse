package cloud.aispring.mybatisplus.clickhouse.injector;

import cloud.aispring.mybatisplus.clickhouse.enumeration.ClickhouseSqlMethodEnum;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 根据 id 删除 clickhouse 对象
 *
 * @author spring.li
 * @version v1.0
 */
@Slf4j
public class DeleteByMapClickHouse extends AbstractClickHouseUpdate {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql;
        ClickhouseSqlMethodEnum sqlMethod = ClickhouseSqlMethodEnum.LOGIC_DELETE_BY_MAP;
        if (tableInfo.isWithLogicDelete()) {
            sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), tableInfo.getLogicDeleteSql(false, false), sqlWhereByMap(tableInfo));
            SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, Object.class);
            return addUpdateMappedStatement(mapperClass, modelClass, sqlMethod.getMethod(), sqlSource);
        } else {
            sqlMethod = ClickhouseSqlMethodEnum.DELETE_BY_MAP;
            sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), this.sqlWhereByMap(tableInfo));
            SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, Object.class);
            return this.addDeleteMappedStatement(mapperClass, sqlMethod.getMethod(), sqlSource);
        }
    }
}
