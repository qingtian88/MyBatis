package com.atguigu.mybatis;

import com.atguigu.mybatis.mapper.DeptMapper;
import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Dept;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Date:
 * Author:AAA
 * Description:
 *
 *
 *  /**
 *      * 解决字段名和属性名不一致的情况：
 *      * a>为字段起别名，保持和属性名的一致
 *      * b>设置全局配置，将_自动映射为驼峰
 *      * <setting name="mapUnderscoreToCamelCase" value="true"/>
 *      *
 *
 *
 *      通过resultMap设置自定义的映射关系
 *      id:唯一标识，不能重复
 *      type：设置映射关系中的实体类类型
 *      子标签：
 *      id设置主键的映射关系
 *      result：设置普通字段的映射关系
 *      property：设置映射关系中的属性名，必须是type属性所设置的实体类类型中的属性名
 *
 *      column：设置映射关系中的字段名，必须是sql语句查询出的字段名
 *
 *      * <resultMap id="empResultMap" type="Emp">
 *      *     <id property="eid" column="eid"></id>
 *      *     <result property="empName" column="emp_name"></result>
 *      *     <result property="age" column="age"></result>
 *      *     <result property="sex" column="sex"></result>
 *      *     <result property="email" column="email"></result>
 *      * </resultMap>
 *      *
 *      * 处理多对一的映射关系：
 *      * a>级联属性赋值
 *      * b>association
 *      * c>分步查询

 *      */


public class ResultMapTest {

    @Test
    public void testGetAllEmp(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> list = mapper.getAllEmp();
        list.forEach(emp -> System.out.println(emp));

    }

    @Test
    public void testGetEmpAndDept(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.getEmpAndDept(5);
        System.out.println(emp);

    }

    @Test
    public void testGetEmpAndDeptByStep(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.getEmpAndDeptByStepOne(5);
        System.out.println(emp.getEmpName());
        System.out.println("++++++++++ ");
        System.out.println(emp.getDept());

    }

    @Test
    public void testGetDeptAndEmp(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptAndEmp(3);
        System.out.println(dept);
    }
    @Test
    public void testGetDeptAndEmpByStep(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptAndEmpByStepOne(3);
        System.out.println(dept);
    }
}
