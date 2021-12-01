package cloud.aispring.mybatisplus.clickhouse.test.service.impl;


import cloud.aispring.mybatisplus.clickhouse.service.impl.ClickhouseServiceImpl;
import cloud.aispring.mybatisplus.clickhouse.test.mapper.UserDeleteMapper;
import cloud.aispring.mybatisplus.clickhouse.test.po.UserDelete;
import cloud.aispring.mybatisplus.clickhouse.test.service.UserDeleteService;
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
public class UserDeleteServiceImpl extends ClickhouseServiceImpl<UserDeleteMapper, UserDelete> implements UserDeleteService {

    @DS("clickhouse")
    @Override
    public void testClickhouse() {
        this.testMySql();
    }

    @SneakyThrows
    private void assertName(UserDelete userDelete) {
        Thread.sleep(200);
        assert userDelete.getName().equals(this.getById(userDelete.getId()).getName());
    }

    @SneakyThrows
    private void assertNull(Integer userId) {
        Thread.sleep(200);
        assert this.getById(userId) == null;
    }

    @DS("master")
    @Override
    public void testMySql() {
        // 清除所有数据
        QueryWrapper<UserDelete> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().isNotNull(UserDelete::getId);
        this.remove(queryWrapper);
        Integer userId = 0;
        UserDelete user = UserDelete.builder().id(userId).name(UUID.randomUUID().toString()).isDeleted(Boolean.FALSE).build();
        this.save(user);
        assertName(user);
        user.setName(UUID.randomUUID().toString());
        this.updateById(user);
        assertName(user);
        user.setName(UUID.randomUUID().toString());
        this.updateBatchById(Collections.singletonList(user));
        assertName(user);
        user.setName(UUID.randomUUID().toString());
        LambdaUpdateWrapper<UserDelete> updateWrapper = (new UpdateWrapper<UserDelete>()).lambda();
        updateWrapper.set(UserDelete::getName, user.getName());
        updateWrapper.eq(UserDelete::getId, user.getId());
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
