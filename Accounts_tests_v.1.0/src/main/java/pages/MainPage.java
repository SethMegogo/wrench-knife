package pages;

import app.App;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MainPage {
    App app = new App();
    String singleTestResult;


    //SignIn
    public WebDriver SignIn(String mail, String password, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.get("http://accounts.tunehog.com");                                                                      //Open Accounts page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email")));                                 //
        driver.findElement(By.id("user_email")).sendKeys(mail);                                                         //Set mail
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password")));                              //
        driver.findElement(By.id("user_password")).sendKeys(password);                                                  //Set password
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='accounts_wraper']/div[2]/div[1]/form/input[2]")));
        driver.findElement(By.xpath("//div[@class='accounts_wraper']/div[2]/div[1]/form/input[2]")).click();                          //click "Sign in"

        return driver;
    }
    //SignOut
    public WebDriver SignOut(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("http://accounts.tunehog.com");                                                                      //Open Accounts page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signout")));                                    //
        driver.findElement(By.id("signout")).click();                                                                   //Click "Sign out"
        return driver;
    }


    //Check SignIn
    public void checkSignIn(String mail, String password, WebDriver driver, PrintWriter logger) {

        SignIn(mail, password, driver);                                                                                 //Sign in
        if (app.isElementPresent(driver, By.id("signout"))) {                                                           //Check "Sign out" presence
            singleTestResult = "<td bgcolor=\"#FFFFFF\">ok";                                                                                //
        } else {                                                                                                        //
            app.increaseErrorCounter();                                                                                 //
            singleTestResult = "<td bgcolor=\"#F8B3B3\">ko (user is not logged in, \"Sign out\" link was not found)";   //
        }
        app.increaseTestNumber();
        String currentResult = ("<td bgcolor=\"#E0E0E0\"><b>"+App.testNumber+"</b></td><td bgcolor=\"#FFFFFF\">Time("+App.now()+") MainPage Logging in</td>" +singleTestResult+"</td>");//Print result
        System.out.println(currentResult);                                                                              //
        logger.println("<tr>"+currentResult+"</tr>");                                                                   //
        logger.flush();                                                                                                 //
    }
    //Check SignOut
    public void checkSignOut(WebDriver driver, PrintWriter logger) {

        SignOut(driver);                                                                                                //SignOut
        if (app.isElementPresent(driver, By.id("user_email"))) {                                                        //Check "Email" field presence
            singleTestResult = "<td bgcolor=\"#FFFFFF\">ok";                                                                                //
        } else {                                                                                                        //
            app.increaseErrorCounter();                                                                                 //
            singleTestResult = "<td bgcolor=\"#F8B3B3\">ko (user is not logged out, \"Email\" field was not found)";    //
        }                                                                                                               //
        app.increaseTestNumber();
        String currentResult = ("<td bgcolor=\"#E0E0E0\"><b>"+App.testNumber+"</b></td><td bgcolor=\"#FFFFFF\">Time("+App.now()+") MainPage Logging out</td>" +singleTestResult+"</td>");//Print result
        System.out.println(currentResult);                                                                              //
        logger.println("<tr>"+currentResult+"</tr>");                                                                   //
        logger.flush();                                                                                                 //
    }
    //Check title
    public void checkTitle(String title, WebDriver driver, PrintWriter logger) {

        driver.get("http://accounts.tunehog.com");                                                                      //Open main page
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);                                                //
        String currentTitle = driver.getTitle();
        if (title.equals(currentTitle)) {                                                                               //Check title
            singleTestResult = "<td bgcolor=\"#FFFFFF\">ok";                                                                                //
        } else {                                                                                                        //
            app.increaseErrorCounter();                                                                                 //
            singleTestResult = "<td bgcolor=\"#F8B3B3\">kk  (title can be wrong, current Title: "+currentTitle+")";     //
        }
        app.increaseTestNumber();
        String currentResult = ("<td bgcolor=\"#E0E0E0\"><b>"+App.testNumber+"</b></td><td bgcolor=\"#FFFFFF\">Time("+App.now()+")  MainPage page title </td>" +singleTestResult+"</td>");//Print result
        System.out.println(currentResult);                                                                              //
        logger.println("<tr>"+currentResult+"</tr>");                                                                   //
        logger.flush();                                                                                                 //

    }
    //Check link
    public void checkLink(String linkName, String locator, String correctLink, WebDriver driver, PrintWriter logger) throws InterruptedException {
        singleTestResult = "<td bgcolor=\"#FFFFFF\">ok";
        driver.get("http://accounts.tunehog.com");                                                                      //Open main page
        WebDriverWait wait = new WebDriverWait(driver, 10);                                                             //
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        if (linkName.equals("Support")) {                                                                               //Check "Support" link
            String link = driver.findElement(By.xpath(locator)).getAttribute("href");                                   //
            if (!link.equals(correctLink)) {                                                                            //
                app.increaseErrorCounter();                                                                             //
                singleTestResult = "<td bgcolor=\"#F8B3B3\">ko  (link can be wrong, current link: "+link+")";           //
            }
        } else {
            driver.findElement(By.xpath(locator)).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Thread.sleep(1000);
            String url = driver.getCurrentUrl();
            if (linkName.equals("Discover")){                                                                           //Check "Discover" link
                if(!url.startsWith(correctLink)){                                                                       //
                    app.increaseErrorCounter();                                                                         //
                    singleTestResult = "<td bgcolor=\"#F8B3B3\">ko  (url can be wrong, current URL: "+url+")";          //
                }
            } else {                                                                                                    //Check all other links
                if(!url.equals(correctLink)){                                                                           //
                    app.increaseErrorCounter();                                                                         //
                    singleTestResult = "<td bgcolor=\"#F8B3B3\">ko  (url can be wrong, current URL: "+url+")";          //
                }
            }
        }
        app.increaseTestNumber();
        String currentResult = ("<td bgcolor=\"#E0E0E0\"><b>"+App.testNumber+"</b></td><td bgcolor=\"#FFFFFF\">Time("+App.now()+") MainPage link \""+linkName+"\" </td>" +singleTestResult+"</td>");
        System.out.println(currentResult);                                                                              //print result
        logger.println("<tr>"+currentResult+"</tr>");                                                                   //
        logger.flush();                                                                                                 //
    }


    //Errors
    //Check ErrorNoEmailNoPass
    public void checkSignInNoEmailNoPass(WebDriver driver, PrintWriter logger) throws InterruptedException {
        SignIn("", "", driver);
        Thread.sleep(1000);
        if (driver.findElement(By.className("accounts_error_text")).isDisplayed()) {
            String errorText = driver.findElement(By.className("accounts_error_text")).getText();
            if (errorText.equals("You need to sign in or sign up before continuing.")) {
                singleTestResult = "<td bgcolor=\"#FFFFFF\">ok";
            } else {
                app.increaseErrorCounter();
                singleTestResult = "<td bgcolor=\"#F8B3B3\">ko (Error text is not a: \""+errorText+"\")";//
            }
        } else {
            app.increaseErrorCounter();
            singleTestResult = "<td bgcolor=\"#F8B3B3\">ko (error message isn't displayed)";//
        }
        app.increaseTestNumber();
        String currentResult = ("<td bgcolor=\"#E0E0E0\"><b>"+App.testNumber+"</b></td><td bgcolor=\"#FFFFFF\">Time("+App.now()+")  MainPage error SignIn NoEmailNoPass </td>" +singleTestResult+"</td>");
        System.out.println(currentResult);
        logger.println("<tr>"+currentResult+"</tr>");
        logger.flush();
    }
    //Check ErrorNoEmail
    public void checkSignInNoEmail(WebDriver driver, PrintWriter logger) throws InterruptedException {
        SignIn("", "123456", driver);
        Thread.sleep(1000);
        if (driver.findElement(By.className("accounts_error_text")).isDisplayed()) {
            String errorText = driver.findElement(By.className("accounts_error_text")).getText();
            if (errorText.equals("You need to sign in or sign up before continuing.")) {
                singleTestResult = "<td bgcolor=\"#FFFFFF\">ok";
            } else {
                app.increaseErrorCounter();
                singleTestResult = "<td bgcolor=\"#F8B3B3\">ko (Error text is not a: \""+errorText+"\")";//
            }
        } else {
            app.increaseErrorCounter();
            singleTestResult = "<td bgcolor=\"#F8B3B3\">ko (error message isn't displayed)";//
        }
        app.increaseTestNumber();
        String currentResult = ("<td bgcolor=\"#E0E0E0\"><b>"+App.testNumber+"</b></td><td bgcolor=\"#FFFFFF\">Time("+App.now()+")  MainPage error SignIn NoEmail </td>" +singleTestResult+"</td>");
        System.out.println(currentResult);
        logger.println("<tr>"+currentResult+"</tr>");
        logger.flush();
    }
    //Check ErrorNoPass
    public void checkSignInNoPass(WebDriver driver, PrintWriter logger) throws InterruptedException {
        SignIn("g@mail.com", "", driver);
        Thread.sleep(1000);
        if (driver.findElement(By.className("accounts_error_text")).isDisplayed()) {
            String errorText = driver.findElement(By.className("accounts_error_text")).getText();
            if (errorText.equals("Invalid email or password.")) {
                singleTestResult = "<td bgcolor=\"#FFFFFF\">ok";
            } else {
                app.increaseErrorCounter();
                singleTestResult = "<td bgcolor=\"#F8B3B3\">ko (Error text is not a: \""+errorText+"\")";//
            }
        } else {
            app.increaseErrorCounter();
            singleTestResult = "<td bgcolor=\"#F8B3B3\">ko (error message isn't displayed)";//
        }
        app.increaseTestNumber();
        String currentResult = ("<td bgcolor=\"#E0E0E0\"><b>"+App.testNumber+"</b></td><td bgcolor=\"#FFFFFF\">Time("+App.now()+")  MainPage error SignIn NoPass </td>" +singleTestResult+"</td>");
        System.out.println(currentResult);
        logger.println("<tr>"+currentResult+"</tr>");
        logger.flush();
    }
    //Check ErrorIncorrectEmail
    public void checkSignInIncorrectEmail(WebDriver driver, PrintWriter logger) throws InterruptedException {
        SignIn("gmailcom", "123456", driver);
        Thread.sleep(1000);
        if (driver.findElement(By.className("accounts_error_text")).isDisplayed()) {
            String errorText = driver.findElement(By.className("accounts_error_text")).getText();
            if (errorText.equals("Invalid email or password.")) {
                singleTestResult = "<td bgcolor=\"#FFFFFF\">ok";
            } else {
                app.increaseErrorCounter();
                singleTestResult = "<td bgcolor=\"#F8B3B3\">ko (Error text is not a: \""+errorText+"\")";//
            }
        } else {
            app.increaseErrorCounter();
            singleTestResult = "<td bgcolor=\"#F8B3B3\">ko (error message isn't displayed)";//
        }
        app.increaseTestNumber();
        String currentResult = ("<td bgcolor=\"#E0E0E0\"><b>"+App.testNumber+"</b></td><td bgcolor=\"#FFFFFF\">Time("+App.now()+")  MainPage error SignIn IncorrectEmail </td>" +singleTestResult+"</td>");
        System.out.println(currentResult);
        logger.println("<tr>"+currentResult+"</tr>");
        logger.flush();
    }
    //Check ErrorIncorrectPass
    public void checkSignInIncorrectPass(WebDriver driver, PrintWriter logger) throws InterruptedException {
        SignIn("g@mail.com", "123", driver);
        Thread.sleep(1000);
        if (driver.findElement(By.className("accounts_error_text")).isDisplayed()) {
            String errorText = driver.findElement(By.className("accounts_error_text")).getText();
            if (errorText.equals("Invalid email or password.")) {
                singleTestResult = "<td bgcolor=\"#FFFFFF\">ok";
            } else {
                app.increaseErrorCounter();
                singleTestResult = "<td bgcolor=\"#F8B3B3\">ko (Error text is not a: \""+errorText+"\")";//
            }
        } else {
            app.increaseErrorCounter();
            singleTestResult = "<td bgcolor=\"#F8B3B3\">ko (error message isn't displayed)";//
        }
        app.increaseTestNumber();
        String currentResult = ("<td bgcolor=\"#E0E0E0\"><b>"+App.testNumber+"</b></td><td bgcolor=\"#FFFFFF\">Time("+App.now()+")  MainPage error SignIn IncorrectPass </td>" +singleTestResult+"</td>");
        System.out.println(currentResult);
        logger.println("<tr>"+currentResult+"</tr>");
        logger.flush();
    }
}
