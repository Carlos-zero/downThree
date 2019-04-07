package com.example.demo.Handler;

import com.example.demo.Utils.RegularUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 94801
 */
@Component
public class DataSimplify {
    @Autowired
    private RegularUtils regularUtils;

    public List<String[]> getCourseList(String htm_str){

        //通过正则   将htm_str作为参数，取得div_str
        String div_str=regularUtils.getDiv(htm_str).replaceAll("\\s*","");


        //同理，取得tr_strs集合
        List<String> tr_strs=regularUtils.getTr(div_str);

        //创建一个数组，用于将tr_strs集合的内容转存（方便使用下标进行数据操作）
        String[] tr_strArray=new String[tr_strs.size()];

        //此处的i为tr_strArray数组的下标     i+1即tr的数量
        int i=0;

        //此处的for循环将tr_strs集合的内容放入tr_strArray
        for (String tr_str:tr_strs) {
            tr_strArray[i]=tr_str;
            i++;
        }

        //用于存放course（课表）数组的集合
        List<String[]> courseArrayList=new ArrayList<>();

        //以下为逻辑层
        //此处for循环  最外层从i=1开始（i=0的信息无用）  提取出tr_strArray的内容
        for (i=1;i<tr_strArray.length;i++){

            //一进入循环就取得该tr（课程）的rowspan值,方便之后的判断操作
            int row= regularUtils.getInt(tr_strArray[i]);

            //arraySize用于确定数组大小
            int arraySize=10+(row-1)*3;

            //每次进入循环就创建一个数组，用于存放此次课程内容
            String[] course=new String[arraySize];

            //course[](课程内容td)的下标
            int courseSubscript=0;

            //此处将tr_strArray[i]作为参数传入regularUtils.getTd，得到td_strs的list集合
            List<String> td_strs= regularUtils.getTd(tr_strArray[i]);

            //此处foreach循环遍历出td_strs的list集合内容并放入course[]数组   便于对单个课程的操作
            for (String td_str:td_strs){
                course[courseSubscript]= regularUtils.getTdContent(td_str);
                courseSubscript++;
            }

            //此处检查该td是否rowspan>1，如果为ture  则进行子节点的提取
            if (row>1){

                //此处将i的值赋予iBegin进行记录,方便在之后的条件判断中进行判断（无法用i进行判断，i值在内部变化）
                int iBegin=i;

                //通过对i的大小的判断确定子节点是否全部取得
                while (i<(iBegin+row-1)){

                    //使i值增加1，对子节点(tr中的td)进行逐层提取
                    i++;
                    List<String> td_strs0 = regularUtils.getTd(tr_strArray[i]);

                    //对提取后的子节点进行遍历，并且再次提取其中所需关键字
                    for (String td_str : td_strs0) {

                        //再次提取
                        course[courseSubscript]= regularUtils.getTdContent(td_str);
                        courseSubscript++;
                    }
                }
            }

            //将本次循环所提取的单个课程内容放入list集合
            courseArrayList.add(course);
        }
        return courseArrayList;
    }
}
