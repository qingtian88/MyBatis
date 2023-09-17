package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.ParameterMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterMapperTest {


        /*MyBatis获取参数值的两种方式：${}和#{}
    ${}的本质就是字符串拼接，
    #{}的本质就是占位符赋值
    ${}使用字符串拼接的方式拼接sql，若为字符串类型或日期类型的字段进行赋值时，需要手动加单引
    号；但是#{}使用占位符赋值的方式拼接sql，此时为字符串类型或日期类型的字段进行赋值时，可以自
    动添加单引号
    2mapper接口方法的参数为多个时候
    此时MyBatis会将这些参数放在一个mapp集合中，以两种方式进行存储
    a>以arg0,arg1...为建，以参数为值
    b>以parm1.parm2、、为键，以参数为值
    因此只需要通过#{}${}以键的方式访问即可，但是需要注意${}的单引号问题
   3.若mapper接口中的方法参数有多个时，可以手动将这些参数放在一个mapper中存储
    4.mapper接口方法的参数是yi实体类类类型的参数
    只需要通过#{}${}以键的方式访问即可，但是需要注意${}的单引号问题
    5可以通过@Param注解标识mapper接口中的方法参数
        此时，会将这些参数放在map集合中，以@Param注解的value属性值为键，以参数为值；以
        param1,param2...为键，以参数为值；只需要通过${}和#{}访问map集合的键就可以获取相对应的值，
        注意${}需要手动加单引号
    */
    @Test
    public void testGetAllUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        List<User> list = mapper.getAllUser();
        list.forEach(user -> System.out.println(user));

    }

    /* @Test
     public void testJDBC() throws Exception {
         String username = "admin";
         Class.forName("");
         Connection connection = DriverManager.getConnection("", "", "");
         // PreparedStatement ps = connection.prepareStatement("select  * from t_user where usetname = '" + username + "'");
         PreparedStatement ps = connection.prepareStatement("select * from t_user where username = ?");
         ps.setString(1, username);
     }*/
    @Test
    public void testGetUserByUsername() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.getUserByUsername("admin");
        System.out.println(user);
    }

    @Test
    public void testchecklongin() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.checkLogin("王五","15364456");
        System.out.println(user);
  }

    @Test
    public void testchecklonginByMap() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        Map<String,Object> map = new HashMap<>();
        map.put("username","admin");
        map.put("password","12334");
        User user = mapper.checkLoginBayMap(map);
        System.out.println(user);
    }

    @Test
    public void testInsertUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        int result = mapper.insertUser(new User(null, "王五", "433211", 12, "男", "1212121@qq.com"));
        System.out.println(result);
    }

    @Test
    public void testchecklonginByParam() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.checkLoginByParam("admin", "12334");
        System.out.println(user);

    }
}


