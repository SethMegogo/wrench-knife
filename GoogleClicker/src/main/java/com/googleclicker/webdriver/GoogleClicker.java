package com.googleclicker.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;


public class GoogleClicker {

    public static void main(String[] args) throws IOException, InterruptedException {
        WebDriver driver = startDriver();
        WebDriverWait wait = new WebDriverWait(driver, 3);
        int counter=0;
        for (;;) {
            driver.get("http://google.com");
            driver.findElement(By.id("gbqfq")).sendKeys("3д печать киев");
            driver.findElement(By.id("gbqfb")).click();
            for (int i = 1; i <=3; i++){
                try{
                    Thread.sleep(2000);
                    driver.findElement(By.id("vs0p"+i)).click();
                    Thread.sleep(2000);
                    driver.navigate().back();
                }catch (Exception e){
                    break;
                }
            }
            for (int i = 1; i <=3; i++){
                try{
                    Thread.sleep(2000);
                    driver.findElement(By.id("vs3p"+i)).click();
                    Thread.sleep(2000);
                    driver.navigate().back();
                }catch (Exception e){
                    break;
                }
            }
            counter++;
            System.out.println("Clicked "+counter+" time(s)");
        }
    }




    private static WebDriver startDriver() {
            WebDriver driver = new FirefoxDriver();                             // Starting the driver
            driver.get("http://google.com/");                      // Getting to Accounts
            return driver;
    }



}
