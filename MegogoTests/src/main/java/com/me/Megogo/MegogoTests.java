package com.me.Megogo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Igor
 * Date: 9/24/13
 * Time: 7:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class MegogoTests {
    public static void main(String[] args) throws IOException, InterruptedException {
        int errorCounter=0;
        String logName = "log_"+HelpfulMethods.now()+".txt";
        PrintWriter logger = HelpfulMethods.startLogger(logName);

        WebDriver driver = HelpfulMethods.startDriver();

        WebDriverWait wait = new WebDriverWait(driver,10);

        try {
            errorCounter = checkTitleAndURL(errorCounter, logger, driver);
        } catch (Exception e) {
            System.out.println("Something went wrong. Please restart the test");
        }

        try {
            errorCounter = checkLogin(errorCounter, logger, driver, wait);
        } catch (Exception e) {
            System.out.println("Something went wrong. Please restart the test");
        }

        HelpfulMethods.finish(driver,logger,errorCounter);

    }

    private static int checkTitleAndURL(int errorCounter, PrintWriter logger, WebDriver driver) {
        System.out.println(driver.getTitle());
        if (!driver.getTitle().equals("MEGOGO.NET - хорошие фильмы онлайн легально и бесплатно")){             // Checking page title
            System.out.println("Wrong page title");
            errorCounter++;
            logger.println("Wrong page title");
            logger.flush();
        }
        if (!driver.getCurrentUrl().startsWith("http://megogo.net/")){                                          // Checking page URL
            System.out.println("Wrong page URL");
            errorCounter++;
            logger.println("Wrong page URL");
            logger.flush();
        }
        return errorCounter;
    }
    private static int checkLogin(int errorCounter, PrintWriter logger, WebDriver driver, WebDriverWait wait) {
        String userEmail = "seth.gecko4@gmail.com";
        String userPassword = "Zed123zeD";
        driver.findElement(By.id("serviceLoginLink")).click();                                                   // Clicking Enter
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("formLogin")));                           // Waiting for Form to appear

        if (!driver.findElement(By.id("formLogin")).isDisplayed()){
            System.out.println("Form popup did not appear");
            errorCounter++;
            logger.println("Form popup did not appear");
            logger.flush();
        }

        driver.findElement(By.id("formLogin")).sendKeys(userEmail);                                              // Sending E-Mail
        driver.findElement(By.id("formPassword")).sendKeys(userPassword);                                        // Sending Password

        driver.findElement(By.id("submitButton")).click();                                                       // Submitting

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"userPanelShowLink\"]/table/tbody/tr/td[2]/p")));

        if (!driver.findElement(By.xpath("//*[@id=\"userPanelShowLink\"]/table/tbody/tr/td[2]/p")).isDisplayed()){
            System.out.println("User was not logged in");
            errorCounter++;
            logger.println("User was not logged in");
            logger.flush();
        }
        return errorCounter;
    }
}
