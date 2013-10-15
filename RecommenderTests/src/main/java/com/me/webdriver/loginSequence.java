package com.me.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.PrintWriter;
import java.util.List;


public class loginSequence {
    public static int loginNewUser(WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter, String sub_url) throws InterruptedException {
        driver.findElement(By.className("th_header_v2_signin")).click();
        HelpfulMethods.clickSignUp(driver,wait);
        List<WebElement> listOfEmails;
        List<WebElement> listOfPasswords;
        listOfEmails = driver.findElements(By.id("user_email"));
        listOfPasswords = driver.findElements(By.id("user_password"));
        wait.until(ExpectedConditions.visibilityOf(listOfEmails.get(1)));
        listOfEmails.get(1).sendKeys("autobot2" + HelpfulMethods.now() + "@bot.com");
        listOfPasswords.get(1).sendKeys("123456");
        driver.findElement(By.id("user_password_confirmation")).sendKeys("123456");
        driver.findElement(By.className("accounts_type1")).click();
        Thread.sleep(2000);
        if (!driver.getCurrentUrl().startsWith("http://recommend")&&!driver.getCurrentUrl().startsWith("https://recommend")&&!driver.getCurrentUrl().startsWith("http://recommender")&&!driver.getCurrentUrl().startsWith("https://recommender")){
            logger.println("No Redirection to Recommender after Signin Up");
            logger.flush();
            loggerHTML.println("<tr><th>No Redirection to Recommender after Signin Up</th><th>1.Open Recommender project<br>2.Click Sign In<br>3.Click Sign Up<br>4.Create New User<br>5.Sign Up</th><th>"+HelpfulMethods.now()+"</th><th>No redirection</th><th>Redirection to Recommender Project logged in as User</th></tr>");
            loggerHTML.flush();
            errorCounter++;
        }
        return errorCounter;
    }
    public static int loginOldUser(WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter, String sub_url) throws InterruptedException {
        driver.findElement(By.className("th_header_v2_signin")).click();
        List<WebElement> listOfEmails;
        List<WebElement> listOfPasswords;
        listOfEmails = driver.findElements(By.id("user_email"));
        listOfPasswords = driver.findElements(By.id("user_password"));
        wait.until(ExpectedConditions.visibilityOf(listOfEmails.get(0)));
        listOfEmails.get(0).sendKeys("autobot2@bot.com");
        listOfPasswords.get(0).sendKeys("123456");
        driver.findElement(By.className("accounts_send_btn")).click();
        Thread.sleep(2000);
        if (!driver.getCurrentUrl().startsWith("http://recommend")&&!driver.getCurrentUrl().startsWith("https://recommend")&&!driver.getCurrentUrl().startsWith("http://recommender")&&!driver.getCurrentUrl().startsWith("https://recommender")){
            System.out.println(driver.getCurrentUrl());
            logger.println("No Redirection to Recommender after Signin In");
            logger.flush();
            loggerHTML.println("<tr><th>No Redirection to Recommender after Signin In</th><th>1.Open Recommender project<br>2.Click Sign In<br>3.Log in as autobot@bot.com (pass : 123456)</th><th>"+HelpfulMethods.now()+"</th><th>No redirection</th><th>Redirection to Recommender Project logged in as User</th></tr>");
            loggerHTML.flush();
            errorCounter++;
        }
        return errorCounter;
    }
}
