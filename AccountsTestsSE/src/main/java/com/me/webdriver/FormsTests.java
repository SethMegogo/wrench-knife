package com.me.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.PrintWriter;
import java.util.List;

public class FormsTests {
    public static int checkEmailOnly(WebDriver driver, PrintWriter logger, PrintWriter loggerHTML, int errorCounter) throws InterruptedException {
        System.out.println("Checking Email Only for Sign In");
        loggerHTML.println("Testing Email Only for Sing In<br>");
        loggerHTML.flush();
        Boolean result;
        driver.findElement(By.id("user_email")).sendKeys("Invalid Input");                                                  // Case 1.1 Email only   Sign In
        driver.findElement(By.className("accounts_send_btn")).click();
        Thread.sleep(2000);
        result = ExpectedConditions.invisibilityOfElementLocated(By.className("accounts_error_text")).apply(driver);
        if (result != null && result){
            logger.println("No \"Invalid email or password message\" 1");
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>No \"Invalid email or password\" message</th><th>1.Open Accounts<br>2.Enter Email only<br>3.Click Sign In</th><th>" + HelpfulMethods.now() + " </th><th></th><th></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        loggerHTML.println("<font color=\"green\">Testing Email Only for Sing In - Complete</font><br><br>");
        loggerHTML.flush();
        driver.findElement(By.id("user_email")).clear();
        System.out.println("Checking Email Only for Sign In - Complete");
        return errorCounter;
    }
    public static int checkPasswordOnly(WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter) throws InterruptedException {
        System.out.println("Checking Password Only for Sign In");
        loggerHTML.println("Testing Password Only for Sing In<br>");
        loggerHTML.flush();
        Boolean result;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password")));
        driver.findElement(By.id("user_password")).sendKeys("password");                                                    // Case 1.2 Password only  Sign In
        driver.findElement(By.className("accounts_send_btn")).click();
        Thread.sleep(2000);
        result = ExpectedConditions.invisibilityOfElementLocated(By.className("accounts_error_text")).apply(driver);
        if (result != null && result){
            logger.println("No \"Invalid email or password message\" 2");
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>No \"Invalid email or password\" message</th><th>1.Open Accounts<br>2.Enter Password only<br>3.Click Sign In</th><th>" + HelpfulMethods.now() + " </th><th></th><th></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        loggerHTML.println("<font color=\"green\">Testing Password Only for Sing In - Complete</font><br><br>");
        loggerHTML.flush();
        driver.findElement(By.id("user_password")).clear();
        System.out.println("Checking Password Only for Sign In - Complete");
        return errorCounter;
    }
    public static int checkInvalidEmail(WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter) throws InterruptedException {
        System.out.println("Checking Invalid Email for Sign In");
        loggerHTML.println("Testing Invalid Email for Sing In<br>");
        loggerHTML.flush();
        Boolean result;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email")));
        driver.findElement(By.id("user_email")).sendKeys("Invalid Input");                                                  // Case 1.3 Invalid email  Sign In
        driver.findElement(By.id("user_password")).sendKeys("password");
        driver.findElement(By.className("accounts_send_btn")).click();
        Thread.sleep(2000);
        result = ExpectedConditions.invisibilityOfElementLocated(By.className("accounts_error_text")).apply(driver);
        if (result != null && result){
            logger.println("No \"Invalid email or password message\" 3");
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>No \"Invalid email or password\" message</th><th>1.Open Accounts<br>2.Enter invalid email and password<br>3.Click Sign In</th><th>" + HelpfulMethods.now() + " </th><th></th><th></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        driver.findElement(By.id("user_email")).clear();
        driver.findElement(By.id("user_password")).clear();
        loggerHTML.println("<font color=\"green\">Testing Invalid Email for Sing In - Complete</font><br><br>");
        loggerHTML.flush();
        System.out.println("Checking Invalid Email for Sign In - Complete");
        return errorCounter;
    }
    public static int checkInvEmailBPasswordBConfirm(WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter) {
        System.out.println("Checking Invalid Email, Blank Password, Blank Confirm for Sign Up");
        loggerHTML.println("Testing Invalid Email, Blank Password, Blank Confirm for Sign Up<br>");
        loggerHTML.flush();
        List<WebElement> listOfEmails;
        Boolean emailVisibility;
        Boolean passwordVisibility;
        String emailErrorText;
        String passwordErrorText;
        listOfEmails = driver.findElements(By.id("user_email"));
        wait.until(ExpectedConditions.visibilityOf(listOfEmails.get(1)));                                                  // Case 2.1 Invalid Email, Blank Pass, Blank Confirm
        listOfEmails.get(1).sendKeys("Invalid Input");
        driver.findElement(By.className("accounts_type1")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_email']/following-sibling::p")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_password']/following-sibling::p")));
        emailVisibility = driver.findElement(By.xpath("//input[@id='user_email']/following-sibling::p")).isDisplayed();
        passwordVisibility = driver.findElement(By.xpath("//input[@id='user_password']/following-sibling::p")).isDisplayed();
        emailErrorText = driver.findElement(By.xpath("//input[@id='user_email']/following-sibling::p")).getText();
        passwordErrorText = driver.findElement(By.xpath("//input[@id='user_password']/following-sibling::p")).getText();
        if (!emailVisibility||!passwordVisibility){
            logger.println("No \"email is invalid\" or \"password can't be blank\" message 1");
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>No \"email is invalid\" or \"password can't be blank\" message</th><th>" + HelpfulMethods.now() + " </th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        if (!emailErrorText.equals("email is invalid") || !passwordErrorText.equals("password can't be blank")){
            logger.println("Wrong error text");
            logger.println("Got Email Error : |" + emailErrorText + "|");
            logger.println("Got Password Error : |" + passwordErrorText + "|");
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Wrong Error Text</th><th>1.Open Accounts<br>2.Click Sign Up<br>3.Enter Invalid Email, Blank Password, Blank Confirm<br>4.Click Sign Up</th><th>" + HelpfulMethods.now() + " </th><th>"+emailErrorText+"<br>"+passwordErrorText+"</th<th>>email is invalid<br>password can't be blank</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        loggerHTML.println("<font color=\"green\">Testing Invalid Email, Blank Password, Blank Confirm for Sign Up - Complete</font><br><br>");
        loggerHTML.flush();
        driver.navigate().refresh();
        System.out.println("Checking Invalid Email, Blank Password, Blank Confirm for Sign Up - Complete");
        return errorCounter;
    }
    public static int checkBEmailShPasswordShConfirm(WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter) {
        System.out.println("Checking Blank Email, Short Password, Short Confirm for Sign Up");
        loggerHTML.println("Testing Blank Email, Short Password, Short Confirm for Sign Up<br>");
        loggerHTML.flush();
        List<WebElement> listOfPasswords;
        Boolean emailVisibility;
        Boolean passwordVisibility;
        String emailErrorText;
        String passwordErrorText;
        listOfPasswords = driver.findElements(By.id("user_password"));
        listOfPasswords.get(1).sendKeys("1234");                                                                         // Case 2.6 Blank Email, Short Pass, Short Confirm
        driver.findElement(By.id("user_password_confirmation")).sendKeys("1234");
        driver.findElement(By.className("accounts_type1")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_email']/following-sibling::p")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_password']/following-sibling::p")));
        emailVisibility = driver.findElement(By.xpath("//input[@id='user_email']/following-sibling::p")).isDisplayed();
        passwordVisibility = driver.findElement(By.xpath("//input[@id='user_password']/following-sibling::p")).isDisplayed();
        emailErrorText = driver.findElement(By.xpath("//input[@id='user_email']/following-sibling::p")).getText();
        passwordErrorText = driver.findElement(By.xpath("//input[@id='user_password']/following-sibling::p")).getText();
        if (!emailVisibility||!passwordVisibility){
            logger.println("No \"email can't be blank\" or \"password is too short\" message");
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>No \"email can't be blank\" or \" password is too short\" message</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        if (!emailErrorText.equals("email can't be blank") || !passwordErrorText.equals("password is too short (minimum is 6 characters)")){
            logger.println("Wrong error text 6");
            logger.println("Got Email Error: |" + emailErrorText + "|");
            logger.println("Got Password : |" + passwordErrorText + "|");
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Wrong Error Text</th><th>1.Open Accounts<br>2.Click Sign Up<br>3.Enter Blank Email, Short Password, Short Confirm<br>4.Click Sign Up</th><th>" + HelpfulMethods.now() + " </th><th>"+emailErrorText+"<br>"+passwordErrorText+"</th><th>email can't be blank<br>password is too short (minimum is 6 characters)</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        loggerHTML.println("<font color=\"green\">Testing Blank Email, Short Password, Short Confirm for Sign Up - Complete</font><br><br>");
        loggerHTML.flush();
        driver.navigate().refresh();
        System.out.println("Checking Blank Email, Short Password, Short Confirm for Sign Up - Complete");
        return errorCounter;
    }
    public static int checkBEmailPasswordBConfirm(WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter) {
        System.out.println("Checking Blank Email, Password, Blank Confirm for Sign Up");
        loggerHTML.println("Testing Blank Email, Password, Blank Confirm for Sign Up<br>");
        loggerHTML.flush();
        List<WebElement> listOfPasswords;
        Boolean emailVisibility;
        Boolean passwordVisibility;
        String emailErrorText;
        String passwordErrorText;
        listOfPasswords = driver.findElements(By.id("user_password"));
        listOfPasswords.get(1).sendKeys("password");                                                                       // Case 2.2 Blank Email, Pass, Blank Confirm     ER: Email Can't be blank, Password Doesn't match
        driver.findElement(By.className("accounts_type1")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_email']/following-sibling::p")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_password']/following-sibling::p")));
        emailVisibility = driver.findElement(By.xpath("//input[@id='user_email']/following-sibling::p")).isDisplayed();
        passwordVisibility = driver.findElement(By.xpath("//input[@id='user_password']/following-sibling::p")).isDisplayed();
        emailErrorText = driver.findElement(By.xpath("//input[@id='user_email']/following-sibling::p")).getText();
        passwordErrorText = driver.findElement(By.xpath("//input[@id='user_password']/following-sibling::p")).getText();
        if (!emailVisibility||!passwordVisibility){
            logger.println("No \"email can't be blank\" or \"password doesn't match confirmation\" message");
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>No \"email can't be blank\" or \"password can't be blank\" message</th><th>" + HelpfulMethods.now() + " </th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        if (!emailErrorText.equals("email can't be blank") || !passwordErrorText.equals("password doesn't match confirmation")){
            logger.println("Wrong error text 2 ");
            logger.println("Got Email Error: |" + emailErrorText + "| instead of \"email can't be blank\"");
            logger.println("Got Password Error: |" + passwordErrorText + "| instead of \"password doesn't match confirmation \"");
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Wrong Error Text</th><th>1.Open Accounts<br>2.Click Sign Up<br>3.Enter Blank Email, Password, Blank Confirm<br>4.Click Sign Up</th><th>" + HelpfulMethods.now() + " </th><th>"+emailErrorText+"<br>"+passwordErrorText+"</th><th>email can't be blank<br>password doesn't match confirmation</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        loggerHTML.println("<font color=\"green\">Testing Blank Email, Password, Blank Confirm for Sign Up - Complete</font><br><br>");
        loggerHTML.flush();
        driver.navigate().refresh();
        System.out.println("Checking Blank Email, Password, Blank Confirm for Sign Up - Complete");
        return errorCounter;
    }
    public static int checkBEmailBPassConfirm(WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter) {
        System.out.println("Checking Blank Email, Blank Password, Confirm for Sign Up");
        loggerHTML.println("Testing Blank Email, Blank Password, Confirm for Sign Up<br>");
        loggerHTML.flush();
        Boolean emailVisibility;
        Boolean passwordVisibility;
        String emailErrorText;
        String passwordErrorText;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password_confirmation")));
        driver.findElement(By.id("user_password_confirmation")).sendKeys("password");                                     // Case 2.3 Blank Email, Blank Pass, Confirm
        driver.findElement(By.className("accounts_type1")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_email']/following-sibling::p")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_password']/following-sibling::p")));
        emailVisibility = driver.findElement(By.xpath("//input[@id='user_email']/following-sibling::p")).isDisplayed();
        passwordVisibility = driver.findElement(By.xpath("//input[@id='user_password']/following-sibling::p")).isDisplayed();
        emailErrorText = driver.findElement(By.xpath("//input[@id='user_email']/following-sibling::p")).getText();
        passwordErrorText = driver.findElement(By.xpath("//input[@id='user_password']/following-sibling::p")).getText();
        if (!emailVisibility||!passwordVisibility){
            logger.println("No \"email can't be blank\" or \"password can't be blank\" message");
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>No \"email can't be blank\" or \"password can't be blank\" message</th><th>" + HelpfulMethods.now() + "</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        if (!emailErrorText.equals("email can't be blank") || !passwordErrorText.equals("password can't be blank")){
            logger.println("Wrong error text");
            logger.println("Got Email Error : |" + emailErrorText + "|");
            logger.println("Got Password : |" + passwordErrorText + "|");
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Wrong Error Text</th><th>1.Open Accounts<br>2.Click Sign Up<br>3.Enter Blank Email, Blank Password, Confirm<br>4.Click Sign Up</th><th>" + HelpfulMethods.now() + " </th><th>"+emailErrorText+"<br>"+passwordErrorText+"</th><th>email can't be blank<br>password can't be blank</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        loggerHTML.println("<font color=\"green\">Testing Blank Email, Blank Password, Confirm for Sign Up - Complete</font><br><br>");
        loggerHTML.flush();
        driver.navigate().refresh();
        System.out.println("Checking Blank Email, Blank Password, Confirm for Sign Up - Complete");
        return errorCounter;
    }
    public static int checkBEmailBPasswordBConfirm(WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter) {
        System.out.println("Checking Blank Email, Blank Password, Blank Confirm for Sign Up");
        loggerHTML.println("Testing Blank Email, Blank Password, Blank Confirm for Sign Up<br>");
        loggerHTML.flush();
        Boolean emailVisibility;
        Boolean passwordVisibility;
        String emailErrorText;
        String passwordErrorText;
        driver.findElement(By.className("accounts_type1")).click();                                                       // Case 2.4 Blank Email, Blank Pass, Blank Confirm
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_email']/following-sibling::p")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_password']/following-sibling::p")));
        emailVisibility = driver.findElement(By.xpath("//input[@id='user_email']/following-sibling::p")).isDisplayed();
        passwordVisibility = driver.findElement(By.xpath("//input[@id='user_password']/following-sibling::p")).isDisplayed();
        emailErrorText = driver.findElement(By.xpath("//input[@id='user_email']/following-sibling::p")).getText();
        passwordErrorText = driver.findElement(By.xpath("//input[@id='user_password']/following-sibling::p")).getText();
        if (!emailVisibility||!passwordVisibility){
            logger.println("No \"email can't be blank\" or \"password can't be blank\" message");
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>No \"email can't be blank\" or \"password can't be blank\" message</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        if (!emailErrorText.equals("email can't be blank") || !passwordErrorText.equals("password can't be blank")){
            logger.println("Wrong error text 4");
            logger.println("Got Email Error: |" + emailErrorText + "|");
            logger.println("Got Password : Error |" + passwordErrorText + "|");
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Wrong Error Text</th><th>1.Open Accounts<br>2.Click Sign Up<br>3.Leave form Blank<br>4.Click Sign Up</th><th>" + HelpfulMethods.now() + " </th><th>"+emailErrorText+"<br>"+passwordErrorText+"</th><th>email can't be blank<br>password can't be blank</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        loggerHTML.println("<font color=\"green\">Testing Blank Email, Blank Password, Blank Confirm for Sign Up - Complete</font><br><br>");
        loggerHTML.flush();
        driver.navigate().refresh();
        System.out.println("Checking Blank Email, Blank Password, Blank Confirm for Sign Up - Complete");
        return errorCounter;
    }
    public static int checkTEmailPassConfirm(WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter) {
        System.out.println("Checking Taken Email, Password, Confirm for Sign Up");
        loggerHTML.println("Testing Taken Email, Password, Confirm for Sign Up<br>");
        loggerHTML.flush();
        List<WebElement> listOfEmails;
        List<WebElement> listOfPasswords;
        Boolean emailVisibility;
        String emailErrorText;
        listOfEmails = driver.findElements(By.id("user_email"));
        listOfPasswords = driver.findElements(By.id("user_password"));
        listOfEmails.get(1).sendKeys("asdf@asdf.com");                                                                    // Case 2.5 Taken Email, Pass, Confirm
        listOfPasswords.get(1).sendKeys("password");
        driver.findElement(By.id("user_password_confirmation")).sendKeys("password");
        driver.findElement(By.className("accounts_type1")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_email']/following-sibling::p")));
        emailVisibility = driver.findElement(By.xpath("//input[@id='user_email']/following-sibling::p")).isDisplayed();
        emailErrorText = driver.findElement(By.xpath("//input[@id='user_email']/following-sibling::p")).getText();
        if (!emailVisibility){
            logger.println("No \"email is already taken\" message");
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>No \"email is already taken\" message</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        if (!emailErrorText.equals("email is already taken")){
            logger.println("Wrong error text");
            logger.println("Got Email Error : |" + emailErrorText + "|");
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Wrong Error Text</th><th>1.Open Accounts<br>2.Click Sign Up<br>3.Enter Taken Email, Password, Confirm<br>4.Click Sign Up</th><th>" + HelpfulMethods.now() + " </th><th>"+emailErrorText+"</th><th>email is already taken</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        driver.navigate().refresh();
        loggerHTML.println("<font color=\"green\">Testing Taken Email, Password, Confirm for Sign Up - Complete</font><br><br>");
        loggerHTML.flush();
        System.out.println("Checking Taken Email, Password, Confirm for Sign Up - Complete");
        return errorCounter;
    }
    public static int checkTermsAndConditions(WebDriver driver, PrintWriter logger, PrintWriter loggerHTML, int errorCounter) {
        System.out.println("Checking Terms and Conditions");
        loggerHTML.println("Testing Terms and Conditions<br>");
        loggerHTML.flush();
        driver.findElement(By.linkText("tunehog T&C’s")).click();
        String termsPageTitle = driver.getTitle();
        String termsPageUrl = driver.getCurrentUrl();
        if (!termsPageTitle.equals("Terms and Conditions — Tunehog Accounts") || !termsPageUrl.equals("http://accounts.tunehog.com/terms-and-conditions")){
            logger.println("Problems with Terms and Conditions. Can be wrong url, wrong page title or not https - ");
            logger.println("Got " + termsPageTitle + " instead of Tunehog Terms and Conditions");
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Problems with Terms and Conditions</th><th>1.Open Accounts<br>2.Click Sign Up<br>3.Click Terms and Conditions link</th><th>" + HelpfulMethods.now() + " </th><th>"+termsPageTitle+" - title</th><th>Tunehog Terms and Conditions - title</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        loggerHTML.println("<font color=\"green\">Testing Terms and Conditions - Complete</font><br><br>");
        loggerHTML.flush();
        System.out.println("Checking Terms and Conditions - Complete");
        return errorCounter;
    }
    public static int checkCreateNewUser(WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter) {
        System.out.println("Checking Create New User");
        loggerHTML.println("Testing Create New User<br>");
        loggerHTML.flush();
        Boolean messageVisibility;
        List<WebElement> listOfEmails;
        List<WebElement> listOfPasswords;
        listOfEmails = driver.findElements(By.id("user_email"));
        listOfPasswords = driver.findElements(By.id("user_password"));
        wait.until(ExpectedConditions.visibilityOf(listOfEmails.get(1)));
        listOfEmails.get(1).sendKeys("autobot" + HelpfulMethods.now() + "@gmail.com");
        listOfPasswords.get(1).sendKeys("password");
        driver.findElement(By.id("user_password_confirmation")).sendKeys("password");
        driver.findElement(By.className("accounts_type1")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("accounts_form_title")));
        messageVisibility = driver.findElement(By.className("accounts_form_title")).isDisplayed();
        if (!messageVisibility){
            logger.println("User Was Not Created Or No Welcome Message Appeared");
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>User Was Not Created Or No Welcome Message Appeared</th><th>" + HelpfulMethods.now() + " </th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        loggerHTML.println("<font color=\"green\">Testing Create New User - Complete</font><br><br>");
        loggerHTML.flush();System.out.println("Checking Create New User - Complete");
        return errorCounter;
    }

}
