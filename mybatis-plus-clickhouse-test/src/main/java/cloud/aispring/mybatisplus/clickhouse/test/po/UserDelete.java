package cloud.aispring.mybatisplus.clickhouse.test.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 用户 逻辑删除
 *
 * @author spring.li
 * @version v1.0
 * @date Created in 2021/11/30 11:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class UserDelete extends User {

    @TableField("is_deleted")
    @TableLogic(value = "0", delval = "1")
    Boolean isDeleted;

}
