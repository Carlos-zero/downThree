package com.example.demo.Entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 94801
 */
@Component
@Data
public class StudentData {

    private String stuName;

    private String stuTerm;

    private int stuNum;

    private List<CourseData> courseDataList;

    public List<CourseData> getCourseDataList() {
        return courseDataList;
    }

    public void setCourseDataList(List<CourseData> courseDataList) {
        this.courseDataList = courseDataList;
    }

    @Override
    public String toString() {
        return "StudentData{" +
                "stuName='" + stuName + '\'' +
                ", stuTerm='" + stuTerm + '\'' +
                ", stuNum=" + stuNum +
                ", courseDataList=" + courseDataList +
                '}';
    }
}
