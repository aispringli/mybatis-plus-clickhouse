package cloud.aispring.mybatisplus.clickhouse.test.mapper;

import cloud.aispring.mybatisplus.clickhouse.mapper.ClickhouseBaseMapper;
import cloud.aispring.mybatisplus.clickhouse.test.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户 mapper
 *
 * @author spring.li
 * @version v1.0
 * @date Created in 2021/11/30 11:14
 */
@Mapper
public interface UserMapper extends ClickhouseBaseMapper<User> {

}
