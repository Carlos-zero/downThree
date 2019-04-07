package com.example.demo.Controller;

import com.example.demo.Entity.StudentData;
import com.example.demo.Service.QueryStudent;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class queryCourseController {
    @Autowired
    private QueryStudent queryStudent;
    @Autowired
    private StudentData studentData;
    @Autowired
    private Gson gson;

    @PostMapping(value = "/queryCourseByName")
    public String queryCourseByName(String name) throws SQLException {
        studentData=queryStudent.queryStuByName(name);
        return gson.toJson(studentData.toString());
    }

    @PostMapping(value = "/queryCourseByNum")
    public String queryCourseByNum(String num){
        studentData=queryStudent.queryStuByNum(num);
        return gson.toJson(studentData.toString());
    }
}
