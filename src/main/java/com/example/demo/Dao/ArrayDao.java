package com.example.demo.Dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface ArrayDao {

    //向数据库插入最后几条数据
    @Insert("Insert into array set course_id=#{courseId},teacher=#{teacher},school_time=#{schoolTime},place=#{place}")
    void addArray(@Param("courseId") String courseId, @Param("teacher") String teacher, @Param("schoolTime") String schoolTime, @Param("place") String place);

    @Select("select * from array where course_id=#{courseId}")
    List<Map<String,Object>> queryArray(@Param("courseId") String courseId);
}
