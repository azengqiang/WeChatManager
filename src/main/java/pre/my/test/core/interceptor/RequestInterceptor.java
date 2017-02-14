package pre.my.test.core.interceptor;

import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Interceptor;
import java.util.Properties;

/**
 * Author:qiang.zeng@hand-china.com on 2017/1/11.
 */
public class RequestInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return null;
    }

    @Override
    public Object plugin(Object o) {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
