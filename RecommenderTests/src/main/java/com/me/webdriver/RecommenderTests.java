package com.me.webdriver;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.PrintWriter;


public class RecommenderTests {

    public static void main(String[] args) throws IOException, InterruptedException {
        RecommenderForm form = new RecommenderForm();
        form.setVisible(true);
    }

    public static void recommenderTest1(String sub_url, String testName, String environment, Boolean browserChrome, Boolean browserFireFox, Boolean browserIE) throws IOException, InterruptedException {

        String browser;
        WebDriver driver = HelpfulMethods.startDriver(sub_url, browserChrome, browserFireFox, browserIE);
        browser = HelpfulMethods.browserInfo(browserChrome, browserFireFox);
        WebDriverWait wait = new WebDriverWait(driver,10);
        String logName = "log_"+testName+"_"+ HelpfulMethods.now()+"_"+environment+"_"+browser+".html";
        PrintWriter logger = HelpfulMethods.startLogger(testName, environment, browser);
        PrintWriter loggerHTML = HelpfulMethods.startLoggerHTML(environment, browser, logName);

        int errorCounter = 0;


        try {
            LandingPageTests.arrowsTest(driver,wait,logger,loggerHTML,errorCounter,sub_url);
        } catch (Exception e) {
            System.out.println("Caught an exception clicking arrows. Please re-run the tests");
        }

        try {
            LandingPageTests.urlTest(driver,wait,logger,loggerHTML,errorCounter,sub_url);
        } catch (Exception e) {
            System.out.println("Caught an exception checking Recommender Url. Please re-run the tests");
        }

        try {
           errorCounter = loginSequence.loginNewUser(driver, wait, logger, loggerHTML, errorCounter, sub_url);
        } catch (InterruptedException e) {
           System.out.println("Caught an exception Logging in as a new User. Please re-run the tests");
        }

/*
        try {
            errorCounter = loginSequence.loginOldUser(driver, wait, logger, loggerHTML, errorCounter, sub_url);
        } catch (InterruptedException e) {
            System.out.println("Caught an exception Logging in as a new User");
        }
*/
        try {
            CommonTests.musicTest(driver,wait,logger,loggerHTML,errorCounter,sub_url);
        } catch (InterruptedException e) {
            System.out.println("Caught an exception  Recommender Music. Please re-run the tests");
        }

        try {
            CommonTests.filmsTest(driver,wait,logger,loggerHTML,errorCounter,sub_url);
        } catch (InterruptedException e) {
            System.out.println("Caught an exception  Recommender Music. Please re-run the tests");
        }

        try {
            CommonTests.booksTest(driver,wait,logger,loggerHTML,errorCounter,sub_url);
        } catch (InterruptedException e) {
            System.out.println("Caught an exception  Recommender Music. Please re-run the tests");
        }

        try {
            CommonTests.TVTest(driver,wait,logger,loggerHTML,errorCounter,sub_url);
        } catch (InterruptedException e) {
            System.out.println("Caught an exception  Recommender Music. Please re-run the tests");
        }
        HelpfulMethods.finish(driver, logger, loggerHTML, errorCounter, testName, logName);
    }
}
