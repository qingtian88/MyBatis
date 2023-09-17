package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.pojo.EmpExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Date:
 * Author:AAA
 * Description:
 */
public class MBGTest {

    @Test
    public void testMBG(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            //查询所有数据
          /*  List<Emp> list = mapper.selectByExample(null);
            list.forEach(emp -> System.out.println(emp));
*/
            //根据条件查询、

            /*EmpExample example = new EmpExample();
            example.createCriteria().andEmpNameEqualTo("艾迪").andAgeGreaterThanOrEqualTo(20);
            example.or().andDidIsNull();
            List<Emp> list = mapper.selectByExample(example);
            list.forEach(emp -> System.out.println(emp));
*/
            //修改
            // mapper.updateByPrimaryKeySelective(new Emp(4,"qwer",11111,null,"12344",1));
           //批量添加

          /*  mapper.insertSelective(new Emp(null,"qqqqq",11,"男","1111222@qq.com",null));
            mapper.insertSelective(new Emp(null,"qqqqqq",11,"男","1111222@qq.com",null));
            mapper.insertSelective(new Emp(null,"qqqqqq",11,"男","1111222@qq.com",null));
            mapper.insertSelective(new Emp(null,"qqqqqq",11,"男","1111222@qq.com",null));*/

            //批量删除
            mapper.deleteByPrimaryKey(69);
            mapper.deleteByPrimaryKey(68);

            mapper.deleteByPrimaryKey(67);

            mapper.deleteByPrimaryKey(66);



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
