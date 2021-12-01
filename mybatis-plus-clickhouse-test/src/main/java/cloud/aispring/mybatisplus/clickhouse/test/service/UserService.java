package cloud.aispring.mybatisplus.clickhouse.test.service;


import cloud.aispring.mybatisplus.clickhouse.test.po.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * TODO
 *
 * @author spring.li
 * @version v1.0
 * @date Created in 2021/11/29 11:24
 */
public interface UserService extends IService<User> {

    void testMySql();

    void testClickhouse();

}
