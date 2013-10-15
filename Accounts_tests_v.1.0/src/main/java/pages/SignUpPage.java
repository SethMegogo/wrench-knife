package pages;

import app.App;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class SignUpPage {
    App app = new App();
    String singleTestResult;


    //SignUp
    public WebDriver SignUp(String regEmail, String password, String confPassword, WebDriver driver) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.get("http://accounts.tunehog.com");                                                                      //Open Accounts page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='accounts_wraper']/div[2]/div[1]/form/p/a")));
        driver.findElement(By.xpath("//div[@class='accounts_wraper']/div[2]/div[1]/form/p/a")).click();                 //Click "SignUp"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign_up")));                                    //
        driver.findElement(By.id("sign_up")).findElement(By.id("user_email")).sendKeys(regEmail);                       //Set reg.mail
        driver.findElement(By.id("sign_up")).findElement(By.id("user_password")).sendKeys(password);                    //Set password
        driver.findElement(By.id("sign_up")).findElement(By.id("user_password_confirmation")).sendKeys(confPassword);   //Set password
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"sign_up\"]/form/input[2]")));      //
        driver.findElement(By.xpath("//*[@id=\"sign_up\"]/form/input[2]")).click();                                     //Click "Create Account"

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


    //Check SignUp
    public void checkSignUp(String regEmail, String password, WebDriver driver, PrintWriter logger) throws InterruptedException {

        SignUp(regEmail, password, password, driver);                                                                   //Sign up
        if (app.isElementPresent(driver, By.id("signout"))) {                                                           //Check "Sign out" presence
            singleTestResult = "<td bgcolor=\"#FFFFFF\">ok";                                                            //
        } else {                                                                                                        //
            app.increaseErrorCounter();
            singleTestResult = "<td bgcolor=\"#F8B3B3\">ko (registration is not successful, \"Sign out\" link was not found)";
        }                                                                                                               //
        app.increaseTestNumber();
        String currentResult = ("<td bgcolor=\"#E0E0E0\"><b>"+App.testNumber+"</b></td><td bgcolor=\"#FFFFFF\">Time("+App.now()+") SignUpPage Registration</td>" +singleTestResult+"</td>");//Print result
        System.out.println(currentResult);                                                                              //
        logger.println("<tr>"+currentResult+"</tr>");                                                                   //
        logger.flush();                                                                                                 //
    }
    //Check SignOut
    public void checkSignOut(WebDriver driver, PrintWriter logger) {

        if (singleTestResult.equals("<td bgcolor=\"#FFFFFF\">ok")) {                                                    //If SignUp is successful
            SignOut(driver);                                                                                            //SignOut
            if (app.isElementPresent(driver, By.id("user_email"))) {                                                    //Check "Email" field presence
                singleTestResult = "<td bgcolor=\"#FFFFFF\">ok";                                                        //
            } else {                                                                                                    //
                app.increaseErrorCounter();                                                                             //
                singleTestResult = "<td bgcolor=\"#F8B3B3\">ko (user is not logged out, \"Email\" field was not found)";//
            }                                                                                                           //
            app.increaseTestNumber();
            String currentResult = ("<td bgcolor=\"#E0E0E0\"><b>"+App.testNumber+"</b></td><td bgcolor=\"#FFFFFF\">Time("+App.now()+") SignUpPage Logging out</td>" +singleTestResult+"</td>");//Print result
            System.out.println(currentResult);                                                                          //
            logger.println("<tr>"+currentResult+"</tr>");                                                               //
            logger.flush();                                                                                             //
        }

    }
    //Check Link
    public void checkLink(String linkName, String locator, String correctLink, WebDriver driver, PrintWriter logger) throws InterruptedException {

        driver.get("http://accounts.tunehog.com/");                                                                     //Open "Restore password page" (from main page)
        WebDriverWait wait = new WebDriverWait(driver, 10);                                                             //
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='accounts_wraper']/div[2]/div[1]/form/p/a")));
        driver.findElement(By.xpath("//div[@class='accounts_wraper']/div[2]/div[1]/form/p/a")).click();                 //

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));                                   //link testing
        driver.findElement(By.xpath(locator)).click();                                                                  //
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);                                                //
        Thread.sleep(1500);                                                                                             //
        String url = driver.getCurrentUrl();                                                                            //
        if (url.equals(correctLink)) {                                                                                  //
            singleTestResult = "<td bgcolor=\"#FFFFFF\">ok";
            } else {
            app.increaseErrorCounter();                                                                                 //
            singleTestResult = "<td bgcolor=\"#F8B3B3\">kk  (url can be wrong, current URL: "+url+")";                  //
        }
        app.increaseTestNumber();
        String currentResult = ("<td bgcolor=\"#E0E0E0\"><b>"+App.testNumber+"</b></td><td bgcolor=\"#FFFFFF\">Time("+ App.now()+") SignUpPage link \""+linkName+"\" </td>" +singleTestResult+"</td>");
        System.out.println(currentResult);                                                                              //Print result
        logger.println("<tr>"+currentResult+"</tr>");                                                                   //
        logger.flush();                                                                                                 //
    }

    //Errors

    //Check ErrorNoEmailNoPassNoPass
    public void checkSignUpNoEmailNoPassNoPass(WebDriver driver, PrintWriter logger) throws InterruptedException {
        SignUp("", "", "", driver);
        Thread.sleep(1000);
        if (driver.findElement(By.xpath("//*[@id=\"sign_up\"]/form/div[2]/p")).isDisplayed() && driver.findElement(By.xpath("//*[@id=\"sign_up\"]/form/div[3]/p")).isDisplayed()) {
            String errorText1 = driver.findElement(By.xpath("//*[@id=\"sign_up\"]/form/div[2]/p")).getText();
            String errorText2 = driver.findElement(By.xpath("//*[@id=\"sign_up\"]/form/div[3]/p")).getText();
            if (errorText1.equals("email can't be blank") && errorText2.equals("password can't be blank")) {
                singleTestResult = "<td bgcolor=\"#FFFFFF\">ok";
            } else {
                app.increaseErrorCounter();
                singleTestResult = "<td bgcolor=\"#F8B3B3\">ko (Wrong error texts: \""+errorText1+"\" and \""+errorText2+"\")";
            }
        } else {
            app.increaseErrorCounter();
            singleTestResult = "<td bgcolor=\"#F8B3B3\">ko (error messages aren't displayed)";
        }
        app.increaseTestNumber();
        String currentResult = ("<td bgcolor=\"#E0E0E0\"><b>"+App.testNumber+"</b></td><td bgcolor=\"#FFFFFF\">Time("+App.now()+")  SignUpPage error NoEmailNoPassNoPass </td>" +singleTestResult+"</td>");
        System.out.println(currentResult);
        logger.println("<tr>"+currentResult+"</tr>");
        logger.flush();
    }
    //Check ErrorNoEmail
    public void checkSignUpNoEmail(WebDriver driver, PrintWriter logger) throws InterruptedException {
        SignUp("", "123456", "123456", driver);
        Thread.sleep(1000);
        if (driver.findElement(By.xpath("//*[@id=\"sign_up\"]/form/div[2]/p")).isDisplayed()) {
            String errorText = driver.findElement(By.xpath("//*[@id=\"sign_up\"]/form/div[2]/p")).getText();
            if (errorText.equals("email can't be blank")) {
                singleTestResult = "<td bgcolor=\"#FFFFFF\">ok";
            } else {
                app.increaseErrorCounter();
                singleTestResult = "<td bgcolor=\"#F8B3B3\">ko (Wrong error text: \""+errorText+"\")";
            }
        } else {
            app.increaseErrorCounter();
            singleTestResult = "<td bgcolor=\"#F8B3B3\">ko (error messages aren't displayed)";
        }
        app.increaseTestNumber();
        String currentResult = ("<td bgcolor=\"#E0E0E0\"><b>"+App.testNumber+"</b></td><td bgcolor=\"#FFFFFF\">Time("+App.now()+")  SignUpPage error NoEmail </td>" +singleTestResult+"</td>");
        System.out.println(currentResult);
        logger.println("<tr>"+currentResult+"</tr>");
        logger.flush();
    }
}
