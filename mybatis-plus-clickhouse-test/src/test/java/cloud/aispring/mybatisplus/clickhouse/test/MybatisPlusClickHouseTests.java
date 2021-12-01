package cloud.aispring.mybatisplus.clickhouse.test;

import cloud.aispring.mybatisplus.clickhouse.test.service.UserDeleteService;
import cloud.aispring.mybatisplus.clickhouse.test.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisPlusClickHouseTests {

    @Autowired
    UserService userService;

    @Autowired
    UserDeleteService userDeleteService;

    @Test
    void mysqlTest() {
        userService.testMySql();
        userDeleteService.testMySql();
    }

    @Test
    void clickhouseTest() {
        userService.testClickhouse();
        userDeleteService.testClickhouse();
    }

}
