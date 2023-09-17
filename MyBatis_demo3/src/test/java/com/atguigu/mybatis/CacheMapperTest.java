package com.atguigu.mybatis;

import com.atguigu.mybatis.mapper.CacheMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Date:
 * Author:AAA
 * Description:
 */

/*一级缓存是SqlSession级别的，通过同一个SqlSession查询的数据会被缓存，下次查询相同的数据，就
会从缓存中直接获取，不会从数据库重新访问
使一级缓存失效的四种情况：
1) 不同的SqlSession对应不同的一级缓存
2) 同一个SqlSession但是查询条件不同
3) 同一个SqlSession两次查询期间执行了任何一次增删改操作
4) 同一个SqlSession两次查询期间手动清空了缓存*/
public class CacheMapperTest {
    @Test
    public void testOneCache(){
        SqlSession sqlSession1 = SqlSessionUtils.getSqlSession();
        CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
        Emp emp1 = mapper1.getEmpByEid(4);
        System.out.println(emp1);
       // mapper1.insertEmp(new Emp(null,"abc",23,"男","123@qq.com"));
        sqlSession1.clearCache();
        Emp emp2 = mapper1.getEmpByEid(4);
        System.out.println(emp2);
       /* SqlSession sqlSession2 = SqlSessionUtils.getSqlSession();
        CacheMapper mapper3 = sqlSession2.getMapper(CacheMapper.class);
        Emp emp3 = mapper3.getEmpByEid(1);
        System.out.println(emp3);*/
    }

/*二级缓存是SqlSessionFactory级别，通过同一个SqlSessionFactory创建的SqlSession查询的结果会被
缓存；此后若再次执行相同的查询语句，结果就会从缓存中获取
二级缓存开启的条件：
a>在核心配置文件中，设置全局配置属性cacheEnabled="true"，默认为true，不需要设置
b>在映射文件中设置标签<cache />
*/




            @Test
                public void testTwoCache(){
                try (
                        InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {
                    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
                    SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
                    CacheMapper mapper = sqlSession1.getMapper(CacheMapper.class);
                    System.out.println(mapper.getEmpByEid(5));
                    sqlSession1.close();
                    SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
                    CacheMapper mapper2 = sqlSession2.getMapper(CacheMapper.class);
                    System.out.println(mapper2.getEmpByEid(5));
                    sqlSession2.close();
                } catch (IOException e) {
                    e.printStackTrace();
        }

    }
}
