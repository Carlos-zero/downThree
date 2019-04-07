package com.example.demo.Service;

import com.example.demo.Dao.ArrayDao;
import com.example.demo.Dao.CourseDao;
import com.example.demo.Dao.StudentDao;
import com.example.demo.Dao.ThreeDao;
import com.example.demo.Entity.CourseData;
import com.example.demo.Entity.StudentData;
import com.example.demo.Handler.GetStudentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@Service
public class AddStudent {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private ArrayDao arrayDao;
    @Autowired
    private ThreeDao threeDao;
    @Autowired
    private StudentData studentData;
    @Autowired
    private GetStudentData getStudentData;

    @Transient
    public void addStudent(StudentData studentData) {
//        studentData.getCourseDataList();
        try {
            String stuName = studentData.getStuName();
            String stuTerm = studentData.getStuTerm();
            String stuNum = String.valueOf(studentData.getStuNum());
            studentDao.addStudent(stuName, stuTerm, stuNum);
            String studentId=threeDao.allId();
            List<CourseData> courseData;
            courseData = studentData.getCourseDataList();
            for (CourseData courseData1 : courseData) {
                String teachClass = courseData1.getTeachClass();
                String courseName = courseData1.getCourseName();
                String teachingClass = courseData1.getTeachingClass();
                String classes = courseData1.getClasses();
                String courseStatus = courseData1.getCourseStatus();
                courseDao.addCourse(studentId, teachClass, courseName, teachingClass, classes, courseStatus);
                String courseId=threeDao.allId();
                for (int i = 0; i < courseData1.getTeachers().length; i++) {
                    String teacher = courseData1.getTeachers()[i];
                    String schoolTime = courseData1.getSchoolTimes()[i];
                    String place = courseData1.getPlaces()[i];
                    arrayDao.addArray(courseId, teacher, schoolTime, place);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
