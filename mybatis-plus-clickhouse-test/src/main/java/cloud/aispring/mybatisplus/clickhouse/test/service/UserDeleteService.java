package cloud.aispring.mybatisplus.clickhouse.test.service;


import cloud.aispring.mybatisplus.clickhouse.test.po.UserDelete;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 逻辑删除
 *
 * @author spring.li
 * @version v1.0
 * @date Created in 2021/11/29 11:24
 */
public interface UserDeleteService extends IService<UserDelete> {

    void testMySql();

    void testClickhouse();
}
