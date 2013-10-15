package com.me.Megogo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Igor
 * Date: 9/24/13
 * Time: 7:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class HelpfulMethods {
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd_HH-mm";
    public static int getRandomNumber(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }
    public static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    public static WebDriver startDriver() {
        try {
            System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.get("http://megogo.net/");
            driver.manage().window().maximize();
            return driver;

        } catch (Exception e) {
            System.out.println("Caught an exception opening browser. Check if the page being tested is available and re-run the test");
            return  null;
        }
    }
    public static PrintWriter startLogger(String logName) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter logger = new PrintWriter("log_"+logName+".txt", "UTF-8");
        logger.println("A beautiful txt log could be here. But instead...");
        logger.println();
        logger.flush();
        return logger;
    }

    public static void finish(WebDriver driver, PrintWriter logger, int errorCounter) throws IOException, InterruptedException {
        logger.println("");
        logger.println("Test completed with " + errorCounter + " error(s)");
        logger.close();
        driver.quit();
    }

}
