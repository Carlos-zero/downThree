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
public interface CourseDao {

    //向数据库插入课程的前几条单数据，并返回新增的主键,用于后续的插入
    @Insert("Insert into course set student_id=#{studentId},teach_class=#{teachClass},course_name=#{courseName}," +
            "teaching_class=#{teachingClass},classes=#{classes},course_status=#{courseStatus}")
//    @Options(useGeneratedKeys = true,keyProperty = "course_id")
    void addCourse(@Param("studentId") String studentId, @Param("teachClass") String teachClass, @Param("courseName") String courseName,
                  @Param("teachingClass") String teachingClass, @Param("classes") String classes, @Param("courseStatus") String courseStatus);

    @Select("select * from course where student_id=#{studentId}")
    List<Map<String,Object>> query(@Param("studentId") String studentId);
}
