package cloud.aispring.mybatisplus.clickhouse.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;

/**
 * clickhouse 更新统一处理
 *
 * @author spring.li
 * @version v1.0
 */
public abstract class AbstractClickHouseUpdate extends AbstractMethod {


    /**
     * SQL Update 语句， 去掉set
     * 错误 : ALTER TABLE tmp UPDATE SET name='123' where id=0
     * 正确 : ALTER TABLE tmp UPDATE name='123' where id=0
     *
     * @param logic  是否逻辑删除注入器
     * @param ew     是否存在 UpdateWrapper 条件
     * @param table  表信息
     * @param alias  别名
     * @param prefix 前缀
     * @return sql
     */
    @Override
    protected String sqlSet(boolean logic, boolean ew, TableInfo table, boolean judgeAliasNull, final String alias, final String prefix) {
        String sqlScript = table.getAllSqlSet(logic, prefix);
        if (judgeAliasNull) {
            sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", alias), true);
        }
        if (ew) {
            sqlScript += NEWLINE;
            sqlScript += SqlScriptUtils.convertIf(SqlScriptUtils.unSafeParam(U_WRAPPER_SQL_SET),
                String.format("%s != null and %s != null", WRAPPER, U_WRAPPER_SQL_SET), false);
        }
        return "<trim prefix=\"\" suffixOverrides=\",\"> " + sqlScript + NEWLINE + "</trim>";
    }


}
