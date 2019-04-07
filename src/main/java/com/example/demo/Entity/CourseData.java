package com.example.demo.Entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author 94801
 */
@Data
@Component
public class CourseData {

    //教学班分类
    private String teachClass;
    //课程名
    private String courseName;
    //教学班
    private String teachingClass;
    //类别
    private String classes;
    //选课状态
    private String courseStatus;
    //教师
    private String[] teachers;
    //上课时间
    private String[] schoolTimes;
    //地点
    private String[] places;

    @Override
    public String toString() {
        return "CourseData{" +
                "teachClass='" + teachClass + '\'' +
                ", courseName='" + courseName + '\'' +
                ", teachingClass='" + teachingClass + '\'' +
                ", classes='" + classes + '\'' +
                ", courseStatus='" + courseStatus + '\'' +
                ", teachers=" + Arrays.toString(teachers) +
                ", schoolTimes=" + Arrays.toString(schoolTimes) +
                ", places=" + Arrays.toString(places) +
                '}';
    }
}
