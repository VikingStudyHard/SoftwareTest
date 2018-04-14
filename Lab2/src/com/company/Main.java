package com.company;
import java.io.IOException;
import java.nio.charset.Charset;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.csvreader.CsvReader;
import java.util.concurrent.TimeUnit;

public class Main{
    public static void main(String[] args){
        System.out.println("未通过列表：");
        int pass = 0;
        int unpass = 0;
        String BaseURL ="https://psych.liebes.top";
        long startTime = System.currentTimeMillis(); //程序开始记录时间
        try {
            CsvReader r = new CsvReader("/Users/viking/Documents/IDEAproject/Lab2/resource/input.csv", ',', Charset.forName("UTF-8"));
            while (r.readRecord()) {
                //读取CSV文件中数据
                String str = r.getRawRecord();
                String[] strList = str.split(",");
                String number = strList[0].trim();
                if(strList.length == 2) {
                    String address = strList[1].trim();
                    int x = address.length();
//                    if (address.charAt(x - 1) == '/') {
//                        address = strList[1].trim().substring(0, x - 1);
//                    } else {
                        address = strList[1].trim();
//                    }

                    String pwd = number.substring(number.length() - 6, number.length());

                    //对应@Before
                    WebDriver driver = new FirefoxDriver();
                    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

                    //对应@Test
                    driver.get(BaseURL + "/st");
                    driver.findElement(By.id("username")).clear();
                    driver.findElement(By.id("username")).sendKeys(number);
                    driver.findElement(By.id("password")).clear();
                    driver.findElement(By.id("password")).sendKeys(pwd);
                    driver.findElement(By.id("submitButton")).click();

                    String targetGit = driver.findElement(By.cssSelector("p.login-box-msg")).getAttribute("innerHTML").trim();
                    if (address.equals(targetGit))
                        pass++;
                    else{
                        unpass++;
                        System.out.println(number + " 未通过：git不匹配。");
                        System.out.println("网站反馈的是：" + targetGit);
                        System.out.println("表格给出的是：" + address);
                    }
                    driver.close();
                }else{
                    unpass++;
                    System.out.println(number + " 未通过：表格中未读取到git");
                }
            }
            r.close();
            long endTime = System.currentTimeMillis(); //程序结束记录时间
            long TotalTime = endTime - startTime; //总消耗时间
            System.out.println("--------测试结束--------");
            System.out.println("通过"+pass+"人，未通过"+unpass+"人。");
            System.out.println("测试总时长："+TotalTime);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
