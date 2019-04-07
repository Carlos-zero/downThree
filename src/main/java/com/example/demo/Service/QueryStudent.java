package com.example.demo.Service;

import com.example.demo.Dao.ArrayDao;
import com.example.demo.Dao.CourseDao;
import com.example.demo.Dao.StudentDao;
import com.example.demo.Entity.CourseData;
import com.example.demo.Entity.StudentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QueryStudent {
    @Autowired
    private ArrayDao arrayDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private StudentDao studentDao;
    /*@Autowired
    private CourseData courseData;*/
    @Autowired
    private StudentData studentData;

    public StudentData queryStuByName(String stuName) throws SQLException {
        Map<String,Object> resultSet=studentDao.queryStuByStuName(stuName);
        Object student_id=resultSet.get("id");
        System.out.println(student_id);
        Object stuName1=resultSet.get("stu_name");
        Object stuTerm=resultSet.get("stu_term");
        Object stuNum=resultSet.get("stu_num");
        studentData.setStuName((String) stuName1);
        studentData.setStuTerm((String) stuTerm);
        studentData.setStuNum((Integer) stuNum);
        List<CourseData> courseDataList=new ArrayList<>();
        List<Map<String,Object>> coursesList=courseDao.query(String.valueOf(student_id));
        for (Map<String,Object> courseMap:coursesList ){
            CourseData courseData=new CourseData();
            Object course_id=courseMap.get("course_id");
            Object teach_class=courseMap.get("teach_class");
            Object course_name=courseMap.get("course_name");
            Object teaching_class=courseMap.get("teaching_class");
            Object classes=courseMap.get("classes");
            Object course_status=courseMap.get("course_status");
            courseData.setTeachClass(String.valueOf(teach_class));
            courseData.setCourseName(String.valueOf(course_name));
            courseData.setTeachingClass(String.valueOf(teaching_class));
            courseData.setClasses(String.valueOf(classes));
            courseData.setCourseStatus(String.valueOf(course_status));
            List<Map<String,Object>> arrayList=arrayDao.queryArray(String.valueOf(course_id));
            int size=arrayList.size();
            String[] teacher=new String[size];
            String[] school_time=new String[size];
            String[] place=new String[size];
            int a=0;
            for (Map<String,Object> arrayMap:arrayList){
                Object teacher1=arrayMap.get("teacher");
                Object school_time1=arrayMap.get("school_time");
                Object place1=arrayMap.get("place");
                teacher[a]= String.valueOf(teacher1);
                school_time[a]= String.valueOf(school_time1);
                place[a]= String.valueOf(place1);
                a++;
            }
            courseData.setTeachers(teacher);
            courseData.setSchoolTimes(school_time);
            courseData.setPlaces(place);
            courseDataList.add(courseData);
        }
        /*while (resultSet.next()) {
            courseData.setTeachClass(resultSet.getString("teach_class"));
            courseData.setCourseName(resultSet.getString("course_name"));
            courseData.setTeachingClass(resultSet.getString("teaching_class"));
            courseData.setClasses(resultSet.getString("classes"));
            courseData.setCourseStatus(resultSet.getString("course_status"));
            String courseId = resultSet.getString("course_id");
            resultSet=arrayDao.queryArray(courseId);
            int i=0;
            while (resultSet.next()){
                i++;
            }
            String[] teachers=new String[i];
            String[] schoolTimes=new String[i];
            String[] places=new String[i];
            int b=0;
            while (resultSet.next()){
                teachers[b]=resultSet.getString("teacher");
                schoolTimes[b]=resultSet.getString("school_time");
                places[b]=resultSet.getString("place");
            }
            courseData.setTeachers(teachers);
            courseData.setSchoolTimes(schoolTimes);
            courseData.setPlaces(places);
            courseDataList.add(courseData);
        }*/
        studentData.setCourseDataList(courseDataList);
//        System.out.println(studentData.toString());
        return studentData;
    }

    public StudentData queryStuByNum(String stuNum1){
        Map<String,Object> resultSet=studentDao.queryStuByStuNum(stuNum1);
        Object student_id=resultSet.get("id");
        System.out.println(student_id);
        Object stuName1=resultSet.get("stu_name");
        Object stuTerm=resultSet.get("stu_term");
        Object stuNum=resultSet.get("stu_num");
        studentData.setStuName((String) stuName1);
        studentData.setStuTerm((String) stuTerm);
        studentData.setStuNum((Integer) stuNum);
        List<CourseData> courseDataList=new ArrayList<>();
        List<Map<String,Object>> coursesList=courseDao.query(String.valueOf(student_id));
        for (Map<String,Object> courseMap:coursesList ){
            CourseData courseData=new CourseData();
            Object course_id=courseMap.get("course_id");
            Object teach_class=courseMap.get("teach_class");
            Object course_name=courseMap.get("course_name");
            Object teaching_class=courseMap.get("teaching_class");
            Object classes=courseMap.get("classes");
            Object course_status=courseMap.get("course_status");
            courseData.setTeachClass(String.valueOf(teach_class));
            courseData.setCourseName(String.valueOf(course_name));
            courseData.setTeachingClass(String.valueOf(teaching_class));
            courseData.setClasses(String.valueOf(classes));
            courseData.setCourseStatus(String.valueOf(course_status));
            List<Map<String,Object>> arrayList=arrayDao.queryArray(String.valueOf(course_id));
            int size=arrayList.size();
            String[] teacher=new String[size];
            String[] school_time=new String[size];
            String[] place=new String[size];
            int a=0;
            for (Map<String,Object> arrayMap:arrayList){
                Object teacher1=arrayMap.get("teacher");
                Object school_time1=arrayMap.get("school_time");
                Object place1=arrayMap.get("place");
                teacher[a]= String.valueOf(teacher1);
                school_time[a]= String.valueOf(school_time1);
                place[a]= String.valueOf(place1);
                a++;
            }
            courseData.setTeachers(teacher);
            courseData.setSchoolTimes(school_time);
            courseData.setPlaces(place);
            courseDataList.add(courseData);
        }
        studentData.setCourseDataList(courseDataList);
//        System.out.println(studentData.toString());
        return studentData;
    }


}
