package com.example.demo.Dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Map;

@Mapper
@Component
public interface StudentDao {

    //向数据库中插入学生的前三条数据,并返回新增的主键，用于后续插入
    @Insert("Insert into student set stu_name=#{stuName},stu_term=#{stuTerm},stu_num=#{stuNum}")
//    @Select("select @@IDENTITY")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addStudent(@Param("stuName") String stuName,@Param("stuTerm") String stuTerm,@Param("stuNum") String stuNum);

    //向数据库中提取数据
    @Select("select * from student where stu_num=#{stuNum}")
    Map<String,Object> queryStuByStuNum(@Param("stuNum") String stuNum);

    @Select("select * from student where stu_name=#{stuName}")
    Map<String,Object> queryStuByStuName(@Param("stuName") String stuName);


}
