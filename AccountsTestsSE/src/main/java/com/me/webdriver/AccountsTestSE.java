package com.me.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.PrintWriter;

public class AccountsTestSE {

    public static void main(String[] args) throws IOException, InterruptedException {

        for (int i = 0; i<12; i++){

            accountsLinksTest(".tunehog.com", "Links", "PROD", true, false, false);      // PROD, Chrome, Links
            accountsLinksTest(".tunehog.com", "Links", "PROD", false, true, false);      // PROD, FF, Links
            accountsFormTest(".tunehog.com", "Forms", "PROD", true, false, false);       // PROD, Chrome, Forms
            accountsFormTest(".tunehog.com", "Forms", "PROD", false, true, false);       // PROD, FF, Forms

            Thread.sleep(7200000);

        }
    }

    public static void accountsLinksTest(String sub_url, String testName, String environment, Boolean browserChrome, Boolean browserFireFox, Boolean browserIE) throws IOException, InterruptedException {

        String browser;
        WebDriver driver = HelpfulMethods.startDriver(sub_url, browserChrome, browserFireFox, browserIE);
        browser = HelpfulMethods.browserInfo(browserChrome, browserFireFox);
        WebDriverWait wait = new WebDriverWait(driver,10);
        String logName = "log_"+testName+"_"+HelpfulMethods.now()+"_"+environment+"_"+browser+".html";
        PrintWriter logger = HelpfulMethods.startLogger(testName, environment, browser);
        PrintWriter loggerHTML = HelpfulMethods.startLoggerHTML(environment, browser, logName);

        int errorCounter = 0;

        HelpfulMethods.correctAuthorization(driver);
        
        try {
            errorCounter = LinksTests.checkAccountsPage(sub_url, driver, logger, loggerHTML, errorCounter);                                             // Accounts Page        **
        }catch (Exception e){
            System.out.println("-----------Caught an exception at Accounts Page. Please restart the test after finish-----------");
        }finally {
            driver.get("http://accounts" + sub_url + "/");
        }

        try{
            errorCounter = LinksTests.checkOnMobilePage(driver, wait, logger, loggerHTML, errorCounter, browserFireFox);                               // On Mobile Page       **
        }catch (Exception e){
            System.out.println("-----------Caught an exception at Itunes Store. Please restart the test after finish-----------");
        }finally {
            driver.get("http://accounts" + sub_url + "/");
        }

        try{
            errorCounter = LinksTests.checkRadioPage(sub_url, driver, wait, logger, loggerHTML, errorCounter, browserFireFox);                         // Radio Page           **
        }catch (Exception e){
            System.out.println("-----------Caught an exception at Radio Page. Please restart the test after finish-----------");
        }finally {
            driver.get("http://accounts" + sub_url + "/");
        }

        try {
            errorCounter = LinksTests.checkUniversePage(sub_url, driver, wait, logger, loggerHTML, errorCounter, browserFireFox);           // Universe Page        **
        }catch (Exception e){
            System.out.println("-----------Caught an exception at Universe Page. Please restart the test after finish-----------");
        }finally {
            driver.get("http://accounts" + sub_url + "/");
        }

        try{
            errorCounter = LinksTests.checkRecommenderPage(sub_url, driver, wait, logger, loggerHTML, errorCounter, browserFireFox, environment);                   // Recommender Page     **
        }catch (Exception e){
            System.out.println("-----------Caught an exception at Recommender Page. Please restart the test after finish-----------");
        }finally {
            driver.get("http://accounts" + sub_url + "/");
        }

        try {
            errorCounter = LinksTests.checkLocatePage(sub_url, driver, wait, logger, loggerHTML, errorCounter, browserFireFox);                        // Locate Page          **
        }catch (Exception e){
            System.out.println("-----------Caught an exception at Locate Page. Please restart the test after finish-----------");
        }finally {
            driver.get("http://accounts" + sub_url + "/");
        }


        try {
            errorCounter = LinksTests.checkMusicManagerPage(sub_url, driver, wait, logger, loggerHTML, errorCounter, browserFireFox);                  // Music Manager Page   **
        } catch (Exception e) {
            System.out.println("-----------Caught an exception at Music Manager Page. Please restart the test after finish-----------");
        } finally {
            driver.get("http://accounts" + sub_url + "/");
        }

        try {
            errorCounter = LinksTests.checkHitmakerPage(sub_url, driver, wait, logger, loggerHTML, errorCounter, browserFireFox);                      // Hitmaker Page        **
        } catch (Exception e) {
            System.out.println("-----------Caught an exception at Hitmaker Page. Please restart the test after finish-----------");
        } finally {
            driver.get("http://accounts" + sub_url + "/");
        }

        try {
            errorCounter = LinksTests.checkSyncPage(sub_url, driver, wait, logger, loggerHTML, errorCounter, browserFireFox, environment);                          // Sync Page            **
        } catch (Exception e) {
            System.out.println("-----------Caught an exception at Sync Page. Please restart the test after finish-----------");
        } finally {
            driver.get("http://accounts" + sub_url + "/");
        }

        HelpfulMethods.finish(driver, logger, loggerHTML, errorCounter, testName, logName);
    }
    public static void accountsFormTest(String sub_url, String testName, String environment, Boolean browserChrome, Boolean browserFireFox, Boolean browserIE) throws IOException, InterruptedException {

        String browser;
        WebDriver driver = HelpfulMethods.startDriver(sub_url, browserChrome, browserFireFox, browserIE);
        browser = HelpfulMethods.browserInfo(browserChrome, browserFireFox);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String logName = "log_"+testName+"_"+HelpfulMethods.now()+"_"+environment+"_"+browser+".html";
        PrintWriter logger = HelpfulMethods.startLogger(testName, environment, browser);
        PrintWriter loggerHTML = HelpfulMethods.startLoggerHTML(environment, browser, logName);

        int errorCounter = 0;

        try {
            errorCounter = FormsTests.checkEmailOnly(driver, logger, loggerHTML, errorCounter);                            // Email only (Sign In)
        } catch (Exception e) {
            System.out.println("----------Caught an exception while logging in. Please restart the test after finish----------");
        } finally {
            driver.get("http://accounts" + sub_url + "/");                      // Getting to Accounts
        }

        try {
            errorCounter = FormsTests.checkPasswordOnly(driver, wait, logger, loggerHTML, errorCounter);                   // Password only (Sign In)
        } catch (Exception e) {
            System.out.println("----------Caught an exception while logging in. Please restart the test after finish----------");
        } finally {
            driver.get("http://accounts" + sub_url + "/");                      // Getting to Accounts
        }

        try {
            errorCounter = FormsTests.checkInvalidEmail(driver, wait, logger, loggerHTML, errorCounter);                   // Invaild Email (Sign in)
        } catch (Exception e) {
            System.out.println("----------Caught an exception while logging in. Please restart the test after finish----------");
        } finally {
            driver.get("http://accounts" + sub_url + "/");                      // Getting to Accounts
        }

        HelpfulMethods.clickSignUp(driver, wait);

        try {
            errorCounter = FormsTests.checkInvEmailBPasswordBConfirm(driver, wait, logger, loggerHTML, errorCounter);      // Invalid Email, Blank Pass, Blank Confirm
        } catch (Exception e) {
            System.out.println("----------Caught an exception while logging in. Please restart the test after finish----------");
        } finally {
            driver.get("http://accounts" + sub_url + "/#sign_up");                      // Getting to Accounts
        }

        try {
            errorCounter = FormsTests.checkBEmailPasswordBConfirm(driver, wait, logger, loggerHTML, errorCounter);         // Blank Email, Pass, Blank Confirm
        } catch (Exception e) {
            System.out.println("----------Caught an exception while logging in. Please restart the test after finish----------");
        } finally {
            driver.get("http://accounts" + sub_url + "/#sign_up");                      // Getting to Accounts
        }

        try {
            errorCounter = FormsTests.checkBEmailBPassConfirm(driver, wait, logger, loggerHTML, errorCounter);             // Blank Email, Blank Pass, Confirm
        } catch (Exception e) {
            System.out.println("----------Caught an exception while logging in. Please restart the test after finish----------");
        } finally {
            driver.get("http://accounts" + sub_url + "/#sign_up");                      // Getting to Accounts
        }

        try {
            errorCounter = FormsTests.checkBEmailBPasswordBConfirm(driver, wait, logger, loggerHTML, errorCounter);        // Blank Email, Blank Pass, Blank Confirm
        } catch (Exception e) {
            System.out.println("----------Caught an exception while logging in. Please restart the test after finish----------");
        } finally {
            driver.get("http://accounts" + sub_url + "/#sign_up");                      // Getting to Accounts
        }

        try {
            errorCounter = FormsTests.checkTEmailPassConfirm(driver, wait, logger, loggerHTML, errorCounter);              // Taken Email, Pass, Confirm
        } catch (Exception e) {
            System.out.println("----------Caught an exception while logging in. Please restart the test after finish----------");
        } finally {
            driver.get("http://accounts" + sub_url + "/#sign_up");                      // Getting to Accounts
        }

        try {
            errorCounter = FormsTests.checkBEmailShPasswordShConfirm(driver, wait, logger, loggerHTML, errorCounter);      // Blank Email, Short Pass, Short Confirm
        } catch (Exception e) {
            System.out.println("----------Caught an exception while logging in. Please restart the test after finish----------");
        } finally {
            driver.get("http://accounts" + sub_url + "/#sign_up");                      // Getting to Accounts
        }

        try {
            errorCounter = FormsTests.checkTermsAndConditions(driver, logger, loggerHTML, errorCounter);                   // Terms And Conditions
        } catch (Exception e) {
            System.out.println("----------Caught an exception while logging in. Please restart the test after finish----------");
        } finally {
            driver.get("http://accounts" + sub_url + "/#sign_up");                      // Getting to Accounts
        }

        HelpfulMethods.checkSignInSignUp(sub_url, driver, wait);                                                           // Sign In | Sign Up

        try {
            errorCounter = FormsTests.checkCreateNewUser(driver, wait, logger, loggerHTML, errorCounter);                  // Create New User
        } catch (Exception e) {
            System.out.println("----------Caught an exception while Creating New User. Please restart the test after finish----------");
        } finally {
            driver.get("http://accounts" + sub_url + "/");                      // Getting to Accounts
        }

        HelpfulMethods.finish(driver, logger, loggerHTML, errorCounter, testName, logName);
    }

}
