package com.example.demo.Handler;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author 94801
 */
@Component
public class Spider {
    public String getHtml(String courseUrl){
        //待研究1
        URL url=null;
        try {
            url=new URL(courseUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //待研究1

        String charset="utf-8";

        //待研究2
        int sec_cont=1000;
        try {
            URLConnection url_con=url.openConnection();
            url_con.setDoOutput(true);
            url_con.setReadTimeout(60 * sec_cont);
            url_con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
            InputStream htm_in = url_con.getInputStream();

            //获得html页面
            String htm_str= InputStream2String(htm_in,charset);
            return htm_str;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "获取出错";
    }



    //该部分还需要仔细研究
    /**
     * Method: InputStream2String
     * Description: make InputStream to String
     * @param in_st
     * inputstream which need to be converted
     * @param charset
     * encoder of value
     * @throws IOException
     * if an error occurred
     */
    public static String InputStream2String(InputStream in_st,String charset)throws IOException{
        BufferedReader buff = new BufferedReader(new InputStreamReader(in_st,charset));
        StringBuffer res=new StringBuffer();
        String line="";
        while ((line = buff.readLine())!=null){
            res.append(line);
        }
        return res.toString();
    }
}
