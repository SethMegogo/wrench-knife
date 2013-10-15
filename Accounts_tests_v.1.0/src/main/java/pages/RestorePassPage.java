package pages;

import app.App;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import app.App;

public class RestorePassPage {
    App app = new App();
    String singleTestResult;

    //Check link
    public void checkLink(String linkName, String locator, String correctLink, WebDriver driver, PrintWriter logger) throws InterruptedException {

        driver.get("http://accounts.tunehog.com/");                                                                     //Open "Restore password page" (from main page)
        WebDriverWait wait = new WebDriverWait(driver, 10);                                                             //
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='accounts_wraper']/div[2]/div/form/a")));
        driver.findElement(By.xpath("//div[@class='accounts_wraper']/div[2]/div/form/a")).click();                      //

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));                                   //link testing
        driver.findElement(By.xpath(locator)).click();                                                                  //
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);                                                //
        Thread.sleep(1000);
        String url = driver.getCurrentUrl();                                                                            //
        if (url.equals(correctLink)) {                                                                                  //
            singleTestResult = "<td bgcolor=\"#FFFFFF\">ok";
        } else {
            app.increaseErrorCounter();                                                                                 //
            singleTestResult = "<td bgcolor=\"#F8B3B3\">kk  (url can be wrong, current URL: "+url+")";                  //
        }
        app.increaseTestNumber();
        String currentResult = ("<td bgcolor=\"#E0E0E0\"><b>"+App.testNumber+"</b></td><td bgcolor=\"#FFFFFF\">Time("+ App.now()+") RestorePassPage link \""+linkName+"\" </td>" +singleTestResult+"</td>");
        System.out.println(currentResult);                                                                              //Print result
        logger.println("<tr>"+currentResult+"</tr>");                                                                   //
        logger.flush();                                                                                                 //
    }
}
