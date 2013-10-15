package main;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.MainPage;
import pages.SignUpPage;
import pages.RestorePassPage;
import app.App;

public class AccountsTests {
    protected WebDriver driver;
    public PrintWriter logger;
    public String email = "g@mail.com";
    public String password = "123456";
    public String regEmail;


    @Before
    public void setUp() {

    }

    @Test
    public void main() throws FileNotFoundException, UnsupportedEncodingException, InterruptedException {

        ArrayList<String> browsers = new ArrayList<String>();
        browsers.add("Firefox");
        browsers.add("Chrome");
        browsers.add("Explorer");

        App app = new App();
        MainPage mainPage = new MainPage();
        SignUpPage signUpPage = new SignUpPage();
        RestorePassPage restorePassPage = new RestorePassPage();
        PrintWriter newLoggerPage = app.startCommonLogger();


        for (String browser: browsers) {

            driver = app.createBrowser(browser);
            logger = app.startLogger(browser, newLoggerPage);
            regEmail = App.generateRegEmail();

            mainPage.checkTitle("Tunehog Accounts",                                                                                                                     driver, logger);
            mainPage.checkLink("tunehog logo", "//div[@class='accounts_wraper']/div[1]/div[1]/a", "http://accounts.tunehog.com/", driver, logger);
            mainPage.checkLink("Support",        "//div[@class='accounts_wraper']/div[1]/div[1]/p[3]/a",  "mailto:support@tunehog.com",                                 driver, logger);
            mainPage.checkLink("Forgot password","//div[@class='accounts_wraper']/div[2]/div[1]/form/a",  "http://accounts.tunehog.com/#restore_password",              driver, logger);
            mainPage.checkLink("Sign up",        "//div[@class='accounts_wraper']/div[2]/div[1]/form/p/a","http://accounts.tunehog.com/#sign_up",                       driver, logger);
            mainPage.checkLink("On Mobile",      "//div[@class='accounts_wraper']/div[1]/div[3]/a",       "https://itunes.apple.com/us/app/th-connect/id648019158?mt=8",driver, logger);
            mainPage.checkLink("Discover",       "//div[@class='accounts_wraper']/div[4]/a",              "http://universe.tunehog.com/#namespace=Tiles",               driver, logger);
            mainPage.checkLink("Top Stations",   "//div[@class='accounts_wraper']/div[3]/a",              "http://radio.tunehog.com/",                                  driver, logger);
            mainPage.checkLink("Recommend",      "//div[@class='accounts_wraper']/div[5]/a",              "http://recommend.tunehog.com/",                              driver, logger);
            mainPage.checkLink("Locate",         "//div[@class='accounts_wraper']/div[6]/a",              "http://locate.tunehog.com/",                                 driver, logger);
            mainPage.checkLink("MM",             "//div[@class='accounts_wraper']/div[7]/a",              "http://musicmanager.tunehog.com/",                           driver, logger);
            mainPage.checkLink("Hitmaker",       "//div[@class='accounts_wraper']/div[8]/a",              "http://hitmaker.tunehog.com/",                               driver, logger);
            mainPage.checkLink("Sync",           "//div[@class='accounts_wraper']/div[9]/a",              "http://sync.tunehog.com/",                                   driver, logger);
            mainPage.checkSignIn(email, password,     driver, logger);
            mainPage.checkSignOut(                    driver, logger);
            mainPage.checkSignInNoEmailNoPass(        driver, logger);
            mainPage.checkSignInNoEmail(              driver, logger);
            mainPage.checkSignInNoPass(               driver, logger);
            mainPage.checkSignInIncorrectEmail(       driver, logger);
            mainPage.checkSignInIncorrectPass(        driver, logger);
            signUpPage.checkSignUp(regEmail, password,driver, logger);
            signUpPage.checkSignOut(                  driver, logger);
            signUpPage.checkSignUpNoEmailNoPassNoPass(driver, logger);
            signUpPage.checkSignUpNoEmail            (driver, logger);
            signUpPage.checkLink("tunehog T&Cs", "//*[@id=\"sign_up\"]/form/span/a",                      "http://accounts.tunehog.com/terms-and-conditions",           driver, logger);
            signUpPage.checkLink("Sign in",      "//*[@id=\"sign_up\"]/form/p/a",                         "http://accounts.tunehog.com/#sign_in",                       driver, logger);
            restorePassPage.checkLink("Sign in", "//div[@class='accounts_wraper']/div[2]/div[3]/form/a",  "http://accounts.tunehog.com/#sign_in",                       driver, logger);
            restorePassPage.checkLink("Sign up", "//div[@class='accounts_wraper']/div[2]/div[3]/form/p/a","http://accounts.tunehog.com/#sign_up",                       driver, logger);

            app.endLogger(logger);
            driver.quit();
        }

        app.endCommonLogger(logger);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("file:///C:/Users/Nikolay/Desktop/accounts_testing_v.0.9/accounts_testing_v.0.9/"+App.fileName);
    }

    @After
    public void tearDown(){
    }
}
