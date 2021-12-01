package cloud.aispring.mybatisplus.clickhouse.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import java.util.List;
import org.springframework.stereotype.Component;


/**
 * 注册 clickhouse方法到 mybatis-plus
 *
 * @author spring.li
 * @version v1.0
 */
@Component
public class ClickHouseSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        // 先要通过父类方法，获取到原有的集合，不然会自带的通用方法会失效的
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);

        // 添加自定义方法类
        if (tableInfo.havePK()) {
            methodList.add(new UpdateByIdClickHouse());
            methodList.add(new DeleteBatchIdsClickHouse());
        }
        methodList.add(new UpdateClickHouse());
        methodList.add(new DeleteByIdClickHouse());
        methodList.add(new DeleteClickHouse());
        methodList.add(new DeleteByMapClickHouse());
        return methodList;
    }

}
