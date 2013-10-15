package com.me.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.PrintWriter;


public class LandingPageTests {
    public static int urlTest(WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter, String sub_url){
    if (!driver.getCurrentUrl().startsWith("https://recommender.qa.vocvox.com/")&&!driver.getCurrentUrl().startsWith("http://recommender.qa.vocvox.com/")&&!driver.getCurrentUrl().startsWith("http://recommend.tunehog.com/")&&!driver.getCurrentUrl().startsWith("https://recommend.tunehog.com/")){
        loggerHTML.println("<table border=\"1\">");
        loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Actual Result</th><th>Expected Result</th></tr>");
        loggerHTML.println("<tr><th>Wrong page URL</th><th>1. Open http://recommend"+sub_url+"</th><th>" + HelpfulMethods.now() + " </th><th>Wrong Url</th><th>Correct page opened</th></tr>");
        loggerHTML.println("</table>");
        loggerHTML.flush();
        errorCounter++;
    }
    return errorCounter;
    }

    public static int arrowsTest(WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter, String sub_url){
    for (;;){
        driver.findElement(By.className("jcarousel-next")).click();
        if (driver.findElement(By.className("jcarousel-next")).getAttribute("disabled")!= null){
            break;
        }
    }
    for (;;){
        driver.findElement(By.className("jcarousel-prev")).click();
        if (driver.findElement(By.className("jcarousel-prev")).getAttribute("disabled")!= null){
            break;
        }
    }
    return errorCounter;
    }
}
