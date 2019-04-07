package com.example.demo.Utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 94801
 */
@Component
public class RegularUtils {
    //获取每个td并列的列的数量
    public int getInt(String row){
        String pattern="\\d";

        Pattern pattern1=Pattern.compile(pattern);
        Matcher matcher=pattern1.matcher(row);
        while (matcher.find()){
            int rowspan= Integer.parseInt(matcher.group());
            return rowspan;
        }
        return 0;
    }



    //获取需要的div
    public String getDiv(String html){
        String pattern="<div id=\"kbStuTabs-list\">([\\s\\S]*)<div  id=\"kbStuTabs-ttk\">";

        Pattern pattern1=Pattern.compile(pattern);
        Matcher matcher=pattern1.matcher(html);
        while (matcher.find()){
            String s1=matcher.group();
            return s1;
        }
        return null;
    }

    //获取每个tr
    public List<String> getTr(String div){
        String pattern="<tr>([\\s\\S]*?)</tr>";

        Pattern pattern1=Pattern.compile(pattern);
        Matcher matcher=pattern1.matcher(div);
        List<String> list=new ArrayList<>();
        while (matcher.find()){
            list.add(matcher.group());
        }
        return list;
    }

    //获取每个td
    public List<String> getTd(String tr){
        String pattern="<td.*?>(.*?)</td>";

        Pattern pattern1=Pattern.compile(pattern);
        Matcher matcher=pattern1.matcher(tr);
        List<String> list=new ArrayList<>();
        while (matcher.find()){
            list.add(matcher.group());
        }
        return list;
    }

    //获取每个td标签内的Content
    public String getTdContent(String td){
        String pattern="<td.*?>(.*?)</td>";

        Pattern pattern1=Pattern.compile(pattern);
        Matcher matcher=pattern1.matcher(td);
        while (matcher.find()) {
            if (matcher.groupCount() > 0) {
                return matcher.group(1);
            } else {
                return "false";
            }
        }
        return null;
    }

    //直接从html中获取学期，学号，姓名
    public String[] getStuData(String html){
        String pattern="<li>〉〉(.*) </li>";

        Pattern pattern1=Pattern.compile(pattern);
        Matcher matcher=pattern1.matcher(html);
        matcher.find();
        String li=matcher.group(1);

        //学年
        pattern="(.*)学生";
        pattern1=Pattern.compile(pattern);
        matcher=pattern1.matcher(li);
        matcher.find();
        String stuTerm=matcher.group(1);

        //学号
        pattern=">>(\\d*)";
        pattern1=Pattern.compile(pattern);
        matcher=pattern1.matcher(li);
        matcher.find();
        String stuNum=matcher.group(1);

        //姓名
        pattern=">>\\d+(.*)";
        pattern1=Pattern.compile(pattern);
        matcher=pattern1.matcher(li);
        matcher.find();
        String stuName=matcher.group(1);

        //放入数组   待取
        String[] student=new String[3];
        student[0]=stuTerm;
        student[1]=stuNum;
        student[2]=stuName;
        return student;
    }
}
