package com.example.demo.Thread;

import com.example.demo.Handler.DataSimplify;
import com.example.demo.Handler.GetStudentData;
import com.example.demo.Handler.Spider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Multithreading {
    @Autowired
    private Spider spider;
    @Autowired
    private GetStudentData getStudentData;
    @Autowired
    private DataSimplify dataSimplify;
    //未爬取的url
    ArrayList<String> notCrawlUrl =new ArrayList<String>();

    int number=0;
    int threadCount=5;      //线程数量
    int waitCount=0;        //等待线程数量
    public static final Object signal = new Object();       //线程间通信变量

    public void begin(){
        addUrl();
        for (int i=0;i<threadCount;i++){
            int finalI = i;
            new Thread(new Runnable(){
                public void run(){
                    while (true){
                        String url = getUrl();
                        if (url!=null){
//                            System.out.println("该次提取由线程："+ finalI+"进行------------");
//                            System.out.println(url+"-----------------------------------------");
                            System.out.println("-------------------"+spider.getHtml(url));
                            String html=spider.getHtml(url);
                            System.out.println(html+"------------");
                            getStudentData.getCourseData(dataSimplify.getCourseList(html));
                            getStudentData.getStuData(html);
                            System.out.println(getStudentData.getStudentData().toString());
                        }else {
                            synchronized (signal){
                                try {
                                    waitCount++;
                                    System.out.println("当前有"+waitCount+"个线程在等待");
                                    signal.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            },"thread--"+i).start();
        }
    }

    public synchronized void addUrl(){
        for (int i=2018210001;i<=2018210050;i++){
            notCrawlUrl.add("http://jwzx.cqu.pt/kebiao/kb_stu.php?xh="+i);
        }
    }
    public synchronized String getUrl(){
        if (notCrawlUrl.isEmpty()){
            return null;
        }else {

            String url;
            url=notCrawlUrl.get(number);
            notCrawlUrl.remove(number);
            number++;
            return url;
        }
    }
}
