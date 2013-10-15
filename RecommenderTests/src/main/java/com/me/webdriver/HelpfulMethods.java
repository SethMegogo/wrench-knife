package com.me.webdriver;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Igor
 * Date: 8/28/13
 * Time: 10:37 AM
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
    public static String browserInfo(Boolean browserChrome, Boolean browserFireFox){
        String browser;
        if (browserChrome){
            browser="Chrome";
        }
        else if (browserFireFox){
            browser="FireFox";
        }
        else {
            browser="IE";
        }
        return browser;
    }
    public static WebDriver startDriver(String sub_url, Boolean browserChrome, Boolean browserFireFox, Boolean browserIE) {
        try {
            if (browserChrome && !browserFireFox && !browserIE){
                System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
                WebDriver driver = new ChromeDriver();                             // Starting the driver
                driver.get("http://recommender" + sub_url + "/");                      // Getting to Accounts
                driver.manage().window().maximize();                                // Maximizing browser window
                return driver;
            }
            if (!browserChrome && browserFireFox && !browserIE){
                WebDriver driver = new FirefoxDriver();                             // Starting the driver
                driver.get("http://recommender" + sub_url + "/");                      // Getting to Accounts
                driver.manage().window().maximize();                                // Maximizing browser window
                return driver;
            }
            if (!browserChrome && !browserFireFox && browserIE){
                DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
                ieCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
                ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                System.setProperty("webdriver.ie.driver", "C:\\IEDriver\\IEDriverServer.exe");
                WebDriver driver = new InternetExplorerDriver(ieCapabilities);                     // Starting the driver
                driver.get("http://recommender" + sub_url + "/");                      // Getting to Accounts
                driver.manage().window().maximize();                                // Maximizing browser window
                return driver;
            }
        } catch (Exception e) {
            System.out.println("Caught an exception opening browser. Check if the page being tested is available and re-run the test");
            return  null;
        }
        return null;
    }
    public static PrintWriter startLogger(String testName, String environment, String browser) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter logger = new PrintWriter("log_"+testName+"_"+now()+"_"+environment+"_"+browser+".txt", "UTF-8");
        logger.println("Universe autotest v.0.2 Proudly Presents");
        logger.println("");
        logger.println("Autotest Results on " + now() + " in " + environment + " using " + browser);
        logger.println("");
        System.out.println("----------------Starting Tests for " + testName + " in " + environment + " using " + browser +"----------------");
        return logger;
    }
    public static PrintWriter startLoggerHTML(String environment, String browser, String logName) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter loggerHTML = new PrintWriter("C:/PSCP/"+logName, "UTF-8");
        loggerHTML.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 3.2 Final//EN\">");
        loggerHTML.println("<HTML>");
        loggerHTML.println("<HEAD>");
        loggerHTML.println("<TITLE>Test Results for Accounts " + now() + "</TITLE>");
        loggerHTML.println("</HEAD>");
        loggerHTML.println("<BODY>");
        loggerHTML.println("Recommender autotest v.0.0.1 Proudly Presents<br>");
        loggerHTML.println("<br>");
        loggerHTML.println("Autotest Results on " + now() + " in " + environment + " using " + browser);
        loggerHTML.println("<br>");
        loggerHTML.println("<br>");
        loggerHTML.flush();
        return loggerHTML;
    }

    public static void finish(WebDriver driver, PrintWriter logger, PrintWriter loggerHTML, int errorCounter, String testName, String logName) throws IOException, InterruptedException {
        logger.println("");
        logger.println("Test completed with " + errorCounter + " error(s)");
        logger.close();
        loggerHTML.println("</table>");
        loggerHTML.println("<br>");
        loggerHTML.println("Test Completed with " + errorCounter + " error(s)");
        loggerHTML.println("<font>N tests performed</font><br>");
        loggerHTML.println("<font color=\"red\">"+errorCounter+" tests failed</font><br>");
        int testsPassed = 26 - errorCounter;
        loggerHTML.println("<font color=\"green\">"+testsPassed+" tests passed</font><br>");
        loggerHTML.println("Tests Completed with N error(s)<br>");
        loggerHTML.println("</BODY>");
        loggerHTML.println("</HTML>");
        loggerHTML.close();

        Thread.sleep(5000);

        System.out.println("Uploading Log to Server. Takes up to 5 seconds");

        String uploadLog = "C:\\PSCP\\pscp.exe -q -pw \"V^%*I$B&*($B\" C:\\PSCP\\" +logName+" automation@qa.vocvox.com:/var/www/automation.qa.vocvox.com";

        Runtime.getRuntime().exec(uploadLog);

        Document index = Jsoup.connect("http://automation.qa.vocvox.com/").get();
        index.body().append("<br><a href=\""+logName+"\">"+logName+"</a>");
        PrintWriter indexDone = new PrintWriter("C:\\PSCP\\index.html", "UTF-8");
        indexDone.println(index.toString());
        indexDone.close();

        String uploadIndex = "C:\\PSCP\\pscp.exe -q -pw \"V^%*I$B&*($B\" C:\\PSCP\\index.html automation@qa.vocvox.com:/var/www/automation.qa.vocvox.com/";
        System.out.println("Updating Index.html");
        Runtime.getRuntime().exec(uploadIndex);

        System.out.println("----------------Tests for " + testName + " are completed.----------------");

        driver.quit();
    }
    public static void correctAuthorization(WebDriver driver) {
        driver.findElement(By.id("user_email")).sendKeys("autobot@bot.com");
        driver.findElement(By.id("user_password")).sendKeys("123456");
        driver.findElement(By.className("accounts_send_btn")).click();
    }
    public static void checkSignInSignUp(String sub_url, WebDriver driver, WebDriverWait wait) {
        System.out.println("Checking Sign In | Sign Up");
        driver.get("https://accounts" + sub_url + "/");
        clickSignUp(driver, wait);
        clickSignIn(driver, wait);
        clickSignUp(driver, wait);
        System.out.println("Checking Sign In | Sign Up - Complete");
    }
    public static void clickSignIn(WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[href*='#sign_in']")));
        driver.findElement(By.cssSelector("[href*='#sign_in']")).click();
    }
    public static void clickSignUp(WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[href*='#sign_up']")));                   // Switching to Sign Up Form
        driver.findElement(By.cssSelector("[href*='#sign_up']")).click();
    }

}
