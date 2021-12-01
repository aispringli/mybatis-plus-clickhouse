package cloud.aispring.mybatisplus.clickhouse.test.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 用户
 *
 * @author spring.li
 * @version v1.0
 * @date Created in 2021/11/30 11:14
 */
@Data
@SuperBuilder
@ToString
@TableName("user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @TableId("id")
    Integer id;

    @TableField("name")
    String name;

    @TableField("is_deleted")
    Boolean isDeleted;

}
