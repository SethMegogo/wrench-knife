package app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class App {
    private WebDriver driver;
    public static String fileName;
    public static int testNumber = 0;
    private static int errorCounter = 0;
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd_HH-mm-ss";
    private static final String DATE_FORMAT_NOW_FOR_MAIL = "yyyyMMddHHmmss";


    //IncreaseErrorCounter
    public void increaseErrorCounter() {
        errorCounter++;
    }
    //IncreaseTestNumber
    public void increaseTestNumber() {
        testNumber++;
    }


    //Date&Time
    public static String now(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }
    //GenerateRegistrationEmail
    public static String generateRegEmail(){
        Calendar cal2 = Calendar.getInstance();
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT_NOW_FOR_MAIL);
        return sdf2.format(cal2.getTime())+"@mail.com";
    }
    //Presence of element
    public boolean isElementPresent(WebDriver driver, By locator) {
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        return driver.findElements(locator).size() > 0;
    }
    //Create browser
    public WebDriver createBrowser(String browser) {
        if (browser.equals("Firefox")) {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }
        if (browser.equals("Chrome")) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        if (browser.equals("Explorer")) {
            driver = new InternetExplorerDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }


    //Logger

    //Start common logger
    public PrintWriter startCommonLogger() throws FileNotFoundException, UnsupportedEncodingException {
        fileName = "AccountsLog_Date_(" + now() + ").html";
        PrintWriter logger = new PrintWriter(fileName, "UTF-8");
        logger.println("<html><head><meta charset=\"UTF-8\"><title>Testing results</title><link href=\"style.css\" media=\"all\" rel=\"stylesheet\" type=\"text/css\"></head><body>");
        logger.flush();
        return logger;
    }
    //End common logger
    public PrintWriter endCommonLogger(PrintWriter logger) throws FileNotFoundException, UnsupportedEncodingException {
        logger.println("</body></html>");
        logger.flush();
        return logger;
    }
    //Start Logger
    public PrintWriter startLogger(String browser, PrintWriter logger) {
        testNumber = 0;
        String favicon = "None";

        if (browser.equals("Firefox")) {
            favicon = "<img src=\"http://www.mozilla.org/favicon.ico\"> Firefox ";
        } else if (browser.equals("Chrome")) {
            favicon ="<img src=\"http://www.google.com/images/icons/product/chrome-16.png\"> Chrome ";
        } else if (browser.equals("Explorer")) {
            favicon ="<img src=\"http://forum.mozilla-russia.org/img/browsers/ie9.png\"> Explorer ";
        }

        logger.println("<div style=\"display:inline-block;\"><table border=\"1\" cellspacing=\"0\"><tr><td bgcolor=\"#82CAFF\"><img src=\"https://blog.qa.vocvox.com/favicon.ico\"></td><td bgcolor=\"#82CAFF\">Start("+now()+") Autotests started... </td><td bgcolor=\"#82CAFF\">"+favicon+"</td></tr>");
        logger.println("");
        logger.flush();
        System.out.println("Testing started...");
        return logger;
    }
    //End Logger
    public PrintWriter endLogger(PrintWriter logger) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("ErrorCounter: "+errorCounter);
        logger.println("<tr><td></td><td bgcolor=\"#FFFFFF\">ErrorCounter: </td><td bgcolor=\"#00FF66\">"+errorCounter+"</td></tr></body></table></div>");
        logger.flush();
        return logger;
    }

}
