package com.example.demo.Handler;

import com.example.demo.Entity.CourseData;
import com.example.demo.Entity.StudentData;
import com.example.demo.Utils.RegularUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetStudentData {
    /*@Autowired
    private CourseData courseData;*/
    @Autowired
    private StudentData studentData;
    @Autowired
    private RegularUtils regularUtils;

    public StudentData getStudentData() {
        return studentData;
    }

    public void getCourseData(List<String[]> courseList){
        //经检测正常
        /*for (String[] test:courseList){
            for (String test1:test){
                System.out.println("传入的值进行测试"+test1);
            }
        }*/
        List<CourseData> studentCourseData=new ArrayList<>();
        for (String[] course:courseList){
            CourseData courseData=new CourseData();
            String[] teachers = new String[(course.length-10)/3+1];
            String[] schoolTimes = new String[(course.length-10)/3+1];
            String[] places = new String[(course.length-10)/3+1];
//            System.out.println("course[2]的值为--------"+course[2]);
            if (course[3].equals("必修")){
                courseData.setTeachClass(course[0]);
                courseData.setCourseName(course[1]);
                courseData.setTeachingClass(course[2]);
                courseData.setClasses(course[3]);
                courseData.setCourseStatus(course[4]);
                teachers[0]=course[5];
                schoolTimes[0]=course[6];
                places[0]=course[7];

                int b=1;
                for (int i=10;i<course.length;i++){
//                    System.out.println("测试coureLenght"+course.length);

//                    System.out.println("此时i=="+i+"，，，测试course[i]="+course[i]);
                    teachers[b]=course[i];
                    schoolTimes[b]=course[i+1];
                    places[b]=course[i+2];
                    b++;
                    i=i+2;
//                    System.out.println("i======"+i);
                }
                courseData.setTeachers(teachers);
                courseData.setSchoolTimes(schoolTimes);
                courseData.setPlaces(places);
            }
            if (course[3].equals("必修")) {
                studentCourseData.add(courseData);
//                System.out.println("course测试+++++++++++++"+courseData.toString());
            }
            //经检查为null---------测试通过

        }
        //检测完成
//        System.out.println("测试1，内层studentCourseData数据---------"+studentCourseData.toString());
        studentData.setCourseDataList(studentCourseData);
        //检测完成
//        System.out.println("测试2,外层封装对象studengtData--------------"+studentData.getCourseDataList().toString());
    }

    public void getStuData(String html){
        String[] stuData=regularUtils.getStuData(html);
        studentData.setStuTerm(stuData[0]);
        studentData.setStuNum(Integer.parseInt(stuData[1]));
        studentData.setStuName(stuData[2]);
    }
}
