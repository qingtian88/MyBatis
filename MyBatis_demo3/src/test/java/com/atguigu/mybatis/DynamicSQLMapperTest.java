package com.atguigu.mybatis;

import com.atguigu.mybatis.mapper.DynamicSQLMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Date:
 * Author:AAA
 * Description:
 */

/**
 * 动态SQL：
 * 1、if：根据标签中test属性所对应的表达式决定标签中的内容是否需要拼接到SQL中
 * 2、where：
 * 当where标签中有内容时，会自动生成where关键字，并且将内容前多余的and或or去掉
 * 当where标签中没有内容时，此时where标签没有任何效果
 * 注意：where标签不能将其中内容后面多余的and或or去掉
 * 3、trim：
 * 若标签中有内容时：
 * prefix|suffix：将trim标签中内容前面或后面添加指定内容
 * suffixOverrides|prefixOverrides：将trim标签中内容前面或后面去掉指定内容
 * 若标签中没有内容时，trim标签也没有任何效果
 * 4、choose、when、otherwise，相当于if...else if...else
 * when至少要有一个，otherwise最多只能有一个
 * 5、foreach
 * collection:设置需要循环的数组或集合
 * item:表示数组或集合中的每一个数据
 * separator:循环体之间的分割符
 * open:foreach标签所循环的所有内容的开始符
 * close:foreach标签所循环的所有内容的结束符
 * 6、sql标签
 * 设置SQL片段：<sql id="empColumns">eid,emp_name,age,sex,email</sql>
 * 引用SQL片段：<include refid="empColumns"></include>
 */
public class DynamicSQLMapperTest {


    @Test
    public void testGetEmpByCondition() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> List = mapper.getEmpByCondition(new Emp(null, "", null, "", ""));
        System.out.println(List);

    }

    @Test
    public void testGetEmpByChoose() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> list = mapper.getEmpByChoose(new Emp(null, "秦天柱", 45, "女", "1233@qq.com"));
        System.out.println(list);
    }
    @Test
    public void testDeleteMoreByArray() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        int result = mapper.deleteMoreByArray(new Integer[]{12, 13, 14, 15});
        System.out.println(result);
    }
    @Test
    public void testInsertMoreByList() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
       Emp emp1 = new Emp(null, "艾莉",123,"男","111111@qq.com");
        Emp emp2 = new Emp(null, "艾地",342,"男","111111@qq.com");
        Emp emp3 = new Emp(null, "艾王",6654,"男","111111@qq.com");
        Emp emp4 = new Emp(null, "艾六",345,"男","111111@qq.com");
        Emp emp5 = new Emp(null, "艾请问",2343,"男","111111@qq.com");
        Emp emp6 = new Emp(null, "艾人",4356,"男","111111@qq.com");
        List<Emp> emps = Arrays.asList(emp1, emp2, emp3, emp4, emp5, emp6);
        System.out.println(mapper.insertMoreByList(emps));


    }
}
