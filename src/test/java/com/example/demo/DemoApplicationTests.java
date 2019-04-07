package com.example.demo;

import com.example.demo.Dao.CourseDao;
import com.example.demo.Dao.StudentDao;
import com.example.demo.Entity.StudentData;
import com.example.demo.Handler.DataSimplify;
import com.example.demo.Handler.GetStudentData;
import com.example.demo.Handler.Spider;
import com.example.demo.Service.AddStudent;
import com.example.demo.Service.QueryStudent;
import com.example.demo.Thread.Multithreading;
import com.example.demo.Utils.RegularUtils;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

//import com.example.demo.Service.QueryStudent;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    //此处调用时也应该自动注入
    @Autowired
    private DataSimplify dataSimplify;

    @Autowired
    private RegularUtils regularUtils;

    @Autowired
    private StudentData studentData;

    @Autowired
    private GetStudentData getStudentData;

    @Autowired
    private AddStudent addStudent;

    @Autowired
    private QueryStudent queryStudent;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private Multithreading multithreading;


    @Autowired
    private Gson gson;
    @Test
    public void contextLoads() {
    }
    @Test
    public void testDao() throws SQLException {
        List<Map<String, Object>> resultSet= courseDao.query("1");
        for (Map<String, Object> map:resultSet){
            Object course_id=map.get("course_id");
            System.out.println(course_id);
            Object teach_class=map.get("teach_class");
            System.out.println(teach_class);
        }
        /*Object id= resultSet.get("course_id");
        System.out.println(id[1]);*/
        /*Object stu_name=resultSet.get("stu_name");
        System.out.println(stu_name);
        Object stu_term=resultSet.get("stu_term");
        Object stu_num=resultSet.get("stu_num");
        System.out.println(stu_term);
        System.out.println(stu_num);*/

    }
    @Test
    public void testQueryStudent() throws SQLException {
        queryStudent.queryStuByName("于凤雪 ");
        queryStudent.queryStuByNum("2018210007");
    }

    /*@Test

    public void testMultithreading(){
        multithreading.begin();

    }*/

    @Test
    public void testAddStudent(){
        Spider spider=new Spider();

        for (int stuNum=2018213537;stuNum<=2018215202;stuNum++){
            String html=spider.getHtml("http://jwzx.cqu.pt/kebiao/kb_stu.php?xh="+stuNum);
            getStudentData.getCourseData(dataSimplify.getCourseList(html));
            getStudentData.getStuData(html);
            addStudent.addStudent(getStudentData.getStudentData());
            System.out.println("学生---"+stuNum+"----添加成功");
            /*System.out.println(getStudentData.getStudentData().toString());
            if (!studentData.getCourseDataList().toString().trim().equals("")){
                System.out.println("-------"+studentData.getStuNum());
            }*/
        }
    }

    @Test
    public void test(){
        Spider spider=new Spider();

        for (int stuNum=2018213537;stuNum<=2018215202;stuNum++){
            String html=spider.getHtml("http://jwzx.cqu.pt/kebiao/kb_stu.php?xh="+stuNum);
            getStudentData.getCourseData(dataSimplify.getCourseList(html));
            getStudentData.getStuData(html);
            System.out.println(getStudentData.getStudentData().toString());
            if (!studentData.getCourseDataList().toString().trim().equals("")){
                System.out.println("-------"+studentData.getStuNum());
            }
        }

    }

    /*@Test
    public void testSpider(){
        //提问：   static方法不能在外部调用码？
        Spider spider=new Spider();
        String html=spider.getHtml("http://jwzx.cqu.pt/kebiao/kb_stu.php?xh=2018214924");
        List<String[]> list=dataSimplify.getCourseList(html);
        for (String[] test:list){
            for (String test1:test){
                System.out.println(test1);
            }
        }
    }*/

    @Test
    public void testGetStuData(){
        Spider spider=new Spider();
        String html=spider.getHtml("http://jwzx.cqu.pt/kebiao/kb_stu.php?xh=2018214924");
        String[] test=regularUtils.getStuData(html);
        for (String string:test){
            System.out.println(string);
        }
    }

}
