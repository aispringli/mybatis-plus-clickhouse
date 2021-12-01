package cloud.aispring.mybatisplus.clickhouse.test.service.impl;


import cloud.aispring.mybatisplus.clickhouse.service.impl.ClickhouseServiceImpl;
import cloud.aispring.mybatisplus.clickhouse.test.mapper.UserMapper;
import cloud.aispring.mybatisplus.clickhouse.test.po.User;
import cloud.aispring.mybatisplus.clickhouse.test.service.UserService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ClickhouseServiceImpl<UserMapper, User> implements UserService {

    @DS("clickhouse")
    @Override
    public void testClickhouse() {
        this.testMySql();
    }

    @SneakyThrows
    private void assertName(User user){
        Thread.sleep(200);
        assert user.getName().equals(this.getById(user.getId()).getName());
    }

    @SneakyThrows
    private void assertNull(Integer userId){
        Thread.sleep(200);
        assert this.getById(userId) == null;
    }

    @DS("master")
    @Override
    public void testMySql() {
        // 清除所有数据
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().isNotNull(User::getId);
        this.remove(queryWrapper);
        Integer userId = 0;
        User user = User.builder().id(userId).name(UUID.randomUUID().toString()).build();
        this.save(user);
        assertName(user);
        user.setName(UUID.randomUUID().toString());
        this.updateById(user);
        assertName(user);
        user.setName(UUID.randomUUID().toString());
        this.updateBatchById(Collections.singletonList(user));
        assertName(user);
        user.setName(UUID.randomUUID().toString());
        LambdaUpdateWrapper<User> updateWrapper = (new UpdateWrapper<User>()).lambda();
        updateWrapper.set(User::getName, user.getName());
        updateWrapper.eq(User::getId, user.getId());
        this.update(updateWrapper);
        assertName(user);
        user.setName(UUID.randomUUID().toString());
        this.saveOrUpdateBatch(Collections.singletonList(user));
        assertName(user);
        this.removeById(userId);
        assertNull(userId);
        this.saveOrUpdateBatch(Collections.singletonList(user));
        assertName(user);
        this.removeById(userId);
        assertNull(userId);
        this.save(user);
        this.removeByIds(Collections.singletonList(userId));
        assertNull(userId);
        this.save(user);
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        this.removeByMap(map);
        assertNull(userId);
    }
}
