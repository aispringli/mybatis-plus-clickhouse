package cloud.aispring.mybatisplus.clickhouse.enumeration;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import lombok.Getter;

/**
 * clickhouse 方法 枚举
 *
 * @author spring.li
 * @version v1.0
 * @see SqlMethod
 */
@Getter
public enum ClickhouseSqlMethodEnum {

    /**
     * 物理删除
     */
    DELETE_BY_ID("deleteByIdClickHouse", "根据ID 删除一条数据", "<script>\nALTER TABLE %s DELETE WHERE %s=#{%s}\n</script>"),
    DELETE_BATCH_IDS("deleteBatchIdsClickhouse", "根据 批量 ID 删除数据", "<script>\nALTER TABLE %s DELETE WHERE %s IN (%s)\n</script>"),
    DELETE("deleteClickhouse", "根据 entity 条件删除记录", "<script>\nALTER TABLE %s DELETE %s %s\n</script>"),
    DELETE_BY_MAP("deleteByMapClickhouse", "根据columnMap 条件删除记录", "<script>\nALTER TABLE %s DELETE %s\n</script>"),

    /**
     * 逻辑删除
     */
    LOGIC_DELETE_BY_ID("deleteByIdClickHouse", "根据ID 逻辑删除一条数据", "<script>\nALTER TABLE %s UPDATE %s where %s=#{%s} %s\n</script>"),
    LOGIC_DELETE_BATCH_IDS("deleteBatchIdsClickhouse", "根据ID 批量 逻辑删除数据", "<script>\nALTER TABLE %s UPDATE %s where %s IN (%s) %s\n</script>"),
    LOGIC_DELETE("deleteClickhouse", "根据 entity 条件逻辑删除记录", "<script>\nALTER TABLE %s UPDATE %s %s %s\n</script>"),
    LOGIC_DELETE_BY_MAP("deleteByMapClickhouse", "根据columnMap 条件逻辑删除记录", "<script>\nALTER TABLE %s UPDATE %s %s\n</script>"),

    /**
     * 更新
     */
    UPDATE_BY_ID("updateByIdClickHouse", "根据ID 选择修改数据", "<script>\nALTER TABLE %s UPDATE %s where %s=#{%s} %s\n</script>"),
    UPDATE("updateClickHouse", "根据 whereEntity 条件，更新记录", "<script>\nALTER TABLE %s UPDATE  %s %s %s\n</script>");

    private final String method;
    private final String desc;
    private final String sql;

    ClickhouseSqlMethodEnum(String method, String desc, String sql) {
        this.method = method;
        this.desc = desc;
        this.sql = sql;
    }
}
