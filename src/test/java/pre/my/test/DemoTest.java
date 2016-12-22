package pre.my.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pre.my.test.demo.dto.User;
import pre.my.test.demo.mapper.UserMapper;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by win on 2016/11/16.
 */
public class DemoTest {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader  = Resources.getResourceAsReader("mybatis-configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }
    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            User user = userMapper.selectUserById((long)1);
            System.out.println("name:"+user.getName());
            System.out.println("pwd:"+user.getPwd());
        } finally {
            session.close();
        }
    }
}
