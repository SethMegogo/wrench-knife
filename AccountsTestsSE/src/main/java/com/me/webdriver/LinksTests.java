package com.me.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Igor
 * Date: 8/28/13
 * Time: 10:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class LinksTests {
    // ACCOUNTS LINKS TEST BLOCK

    public static int checkAccountsPage(String sub_url, WebDriver driver, PrintWriter logger, PrintWriter loggerHTML, int errorCounter) {
        System.out.println("Checking Accounts Page");
        loggerHTML.println("Testing Accounts Page<br>");
        loggerHTML.flush();
        String accountsPageTitle = driver.getTitle();
        String accountsPageUrl = driver.getCurrentUrl();
        driver.findElement(By.className("accounts_logo")).click();
        if (!accountsPageTitle.equals("Tunehog Accounts")){
            logger.println("Problems with Tunehog Accounts. Wrong page title - " + HelpfulMethods.now());
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Problems with Tunehog Accounts. Wrong page title or not https</th><th>1.Open Accounts<br>2.Click Accounts Logo</th><th>" + HelpfulMethods.now() + "</th><th>"+ accountsPageUrl +"</th><th>"+accountsPageTitle+"</th><th><a href=\"" + accountsPageUrl + "\">Link To The Problem Page</a></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        if (!accountsPageUrl.startsWith("https://")){
            logger.println("Problems with Tunehog Accounts. No HTTPS - " + HelpfulMethods.now());
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Problems with Tunehog Accounts. No HTTPS</th><th>1.Open Accounts<br>2.Click Accounts Logo</th><th>" + HelpfulMethods.now() + "</th><th>"+ accountsPageUrl +"</th><th>"+accountsPageTitle+"</th><th><a href=\"" + accountsPageUrl + "\">Link To The Problem Page</a></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        if (!accountsPageUrl.equals("https://accounts" + sub_url + "/")&&!accountsPageUrl.equals("http://accounts" + sub_url + "/")){
            String bugDescription;
            logger.println("Problems with Tunehog Accounts. Wrong page URL - " + HelpfulMethods.now());
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Problems with Tunehog Accounts. Wrong URL</th><th>1.Open Accounts<br>2.Click Accounts Logo</th><th>" + HelpfulMethods.now() + "</th><th>"+ accountsPageUrl +"</th><th>"+accountsPageTitle+"</th><th><a href=\"" + accountsPageUrl + "\">Link To The Problem Page</a></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
            bugDescription = "Wrong URL on http://accounts"+ sub_url + "/";
            SendMailTLS.main("automation@randrmusic.com", "dI6nzeedu8", "Alert QA Team!,"
                    + "\n\n Critical bug found !!!"
                    + "\n\n" + bugDescription);
        }
        System.out.println("Accounts Page - Checked");
        loggerHTML.println("<font color=\"green\">Testing Accounts Page - Complete</font><br><br>");
        loggerHTML.flush();
        return errorCounter;
    }
    public static int checkOnMobilePage(WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter, Boolean browserFireFox) throws InterruptedException {
        System.out.println("Checking On Mobile Page");
        loggerHTML.println("Testing On Mobile Page<br>");
        loggerHTML.flush();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("accounts_mobile")));
        if (browserFireFox){
            driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[3]/a/p[1]")).click();
        }
        else {
            driver.findElement(By.className("accounts_mobile")).click();
        }
        Thread.sleep(3000);
        String onMobilePageTitle = driver.getTitle();
        String onMobilePageUrl = driver.getCurrentUrl();
        if (!onMobilePageTitle.equals("th Connect for iPhone, iPod touch, and iPad on the iTunes App Store") ){
            logger.println("Problems with iTunes Store. Wrong page title - " + HelpfulMethods.now());
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Problems with iTunes Store. Wrong page title</th><th>1.Open Accounts<br>2.Click On Mobile link</th><th>" + HelpfulMethods.now() + "</th><th>"+ onMobilePageUrl +"</th><th>"+onMobilePageTitle+"</th><th><a href=\"" + onMobilePageUrl + "\">Link To The Problem Page</a></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        if (!onMobilePageUrl.equals("https://itunes.apple.com/us/app/th-connect/id648019158?mt=8") ){
            String bugDescription;
            logger.println("Problems with iTunes Store. Wrong page URL - " + HelpfulMethods.now());
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Problems with iTunes Store. Wrong page URL</th><th>1.Open Accounts<br>2.Click On Mobile link</th><th>" + HelpfulMethods.now() + "</th><th>"+ onMobilePageUrl +"</th><th>"+onMobilePageTitle+"</th><th><a href=\"" + onMobilePageUrl + "\">Link To The Problem Page</a></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
            bugDescription = "Wrong URL https://itunes.apple.com/us/app/th-connect/id648019158?mt=8/";
            SendMailTLS.main("automation@randrmusic.com", "dI6nzeedu8", "Alert QA Team!,"
                    + "\n\n Critical bug found !!!"
                    + "\n\n" + bugDescription);
        }
        System.out.println("On Mobile Page - Checked");
        loggerHTML.println("<font color=\"green\">Testing On Mobile Page - Complete</font><br><br>");
        loggerHTML.flush();
        return errorCounter;
    }
    public static int checkRadioPage(String sub_url, WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter, Boolean browserFireFox) throws InterruptedException {
        System.out.println("Checking Radio Page");
        loggerHTML.println("Testing Radio Page<br>");
        loggerHTML.flush();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("accounts_radio")));
        if (browserFireFox){
            driver.findElement(By.xpath("/html/body/div[3]/div[3]/a/p[1]")).click();
        }
        else {
            driver.findElement(By.className("accounts_radio")).click();
        }                                                                // Radio
        Thread.sleep(3000);
        String radioPageTitle = driver.getTitle();
        String radioPageUrl = driver.getCurrentUrl();
        if (!radioPageTitle.equals("tunehog Radio | Your personal music radio")){
            logger.println("Problems with Radio. Wrong page title - " + HelpfulMethods.now());
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Problems with Radio. Wrong page title</th><th>1.Open Accounts<br>2.Click Radio link</th><th>" + HelpfulMethods.now() + "</th><th>"+ radioPageUrl +"</th><th>"+radioPageTitle+"</th><th><a href=\"" + radioPageUrl + "\">Link To The Problem Page</a></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        if (!radioPageUrl.startsWith("https://")){
            logger.println("Problems with Tunehog Radio. No HTTPS - " + HelpfulMethods.now());
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Problems with Tunehog Accounts. No HTTPS</th><th>1.Open Accounts<br>2.Click Accounts Logo</th><th>" + HelpfulMethods.now() + "</th><th>"+ radioPageUrl +"</th><th>"+radioPageTitle+"</th><th><a href=\"" + radioPageUrl + "\">Link To The Problem Page</a></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        if (!radioPageUrl.equals("https://radio" + sub_url + "/")&&!radioPageUrl.equals("http://radio" + sub_url + "/")){
            String bugDescription;
            logger.println("Problems with Radio. Wrong page URL - " + HelpfulMethods.now());
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Problems with Radio. Wrong page URL</th><th>1.Open Accounts<br>2.Click Radio link</th><th>" + HelpfulMethods.now() + "</th><th>"+ radioPageUrl +"</th><th>"+radioPageTitle+"</th><th><a href=\"" + radioPageUrl + "\">Link To The Problem Page</a></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
            bugDescription = "Wrong URL at https://radio"+sub_url+"/";
            SendMailTLS.main("automation@randrmusic.com", "dI6nzeedu8", "Alert QA Team!,"
                    + "\n\n Critical bug found !!!"
                    + "\n\n" + bugDescription);
        }
        loggerHTML.println("<font color=\"green\">Testing Radio Page - Complete</font><br><br>");
        loggerHTML.flush();
        System.out.println("Radio Page - Checked");
        return errorCounter;
    }
    public static int checkUniversePage(String sub_url, WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter, Boolean browserFireFox) throws InterruptedException {
        System.out.println("Checking Universe Page");
        loggerHTML.println("Testing Universe Page<br>");
        loggerHTML.flush();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("accounts_discovery")));
        if (browserFireFox){
            driver.findElement(By.xpath("/html/body/div[3]/div[4]/a/p[1]")).click();
        }
        else {
            driver.findElement(By.className("accounts_discovery")).click();
        }
        Thread.sleep(3000);
        String discoveryPageTitle = driver.getTitle();
        String discoveryPageUrl = driver.getCurrentUrl();
        if (!discoveryPageTitle.equals("Discovery Universe")){
            logger.println("Problems with Universe. Wrong page title - " + HelpfulMethods.now());
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Problems with Universe. Wrong page title</th><th>1.Open Accounts<br>2.Click Discover link</th><th>" + HelpfulMethods.now() + "</th><th>"+ discoveryPageUrl +"</th><th>"+discoveryPageTitle+"</th><th><a href=\"" + discoveryPageUrl + "\">Link To The Problem Page</a></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        if (!discoveryPageUrl.startsWith("https://")){
            logger.println("Problems with Universe. No HTTPS - " + HelpfulMethods.now());
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Problems with Universe. No HTTPS</th><th>1.Open Accounts<br>2.Click Discover link</th><th>" + HelpfulMethods.now() + "</th><th>"+ discoveryPageUrl +"</th><th>"+discoveryPageTitle+"</th><th><a href=\"" + discoveryPageUrl + "\">Link To The Problem Page</a></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        if (!discoveryPageUrl.startsWith("https://universe" + sub_url)&&!discoveryPageUrl.startsWith("http://universe" + sub_url)){
            String bugDescription;
            logger.println("Problems with Universe. Wrong page URL - " + HelpfulMethods.now());
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Problems with Universe. Wrong page URL</th><th>1.Open Accounts<br>2.Click Discover link</th><th>" + HelpfulMethods.now() + "</th><th>"+ discoveryPageUrl +"</th><th>"+discoveryPageTitle+"</th><th><a href=\"" + discoveryPageUrl + "\">Link To The Problem Page</a></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
            bugDescription = "Wrong URL at http://universe"+sub_url;
            SendMailTLS.main("automation@randrmusic.com", "dI6nzeedu8", "Alert QA Team!,"
                    + "\n\n Critical bug found !!!"
                    + "\n\n" + bugDescription);
        }
        loggerHTML.println("<font color=\"green\">Testing Universe Page - Complete</font><br><br>");
        loggerHTML.flush();
        System.out.println("Universe Page - Checked");
        return errorCounter;
    }
    public static int checkRecommenderPage(String sub_url, WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter, Boolean browserFireFox, String environment) throws InterruptedException {
        System.out.println("Checking Recommender Page");
        loggerHTML.println("Testing Recommender Page<br>");
        loggerHTML.flush();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("accounts_recommender")));
        if (browserFireFox){
            driver.findElement(By.xpath("/html/body/div[3]/div[5]/a/p[1]")).click();
        }
        else {
            driver.findElement(By.className("accounts_recommender")).click();
        }
        Thread.sleep(3000);
        String recommenderPageTitle = driver.getTitle();
        String recommenderPageUrl = driver.getCurrentUrl();
        if (environment.equals("PROD")){
            if (!recommenderPageTitle.equals("Recommend")){
                logger.println("Problems with Recommender. Wrong page title - " + HelpfulMethods.now());
                logger.flush();
                loggerHTML.println("<table border=\"1\">");
                loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
                loggerHTML.println("<tr><th>Problems with Recommender. Wrong page title</th><th>1.Open Accounts<br>2.Click Recommender link</th><th>" + HelpfulMethods.now() + "</th><th>"+ recommenderPageUrl +"</th><th>"+recommenderPageTitle+"</th><th><a href=\"" + recommenderPageUrl + "\">Link To The Problem Page</a></th></tr>");
                loggerHTML.println("</table>");
                loggerHTML.flush();
                errorCounter++;
            }
            if (!recommenderPageUrl.startsWith("https://")){
                logger.println("Problems with Recommender. No HTTPS - " + HelpfulMethods.now());
                logger.flush();
                loggerHTML.println("<table border=\"1\">");
                loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
                loggerHTML.println("<tr><th>Problems with Recommender. No HTTPS</th><th>1.Open Accounts<br>2.Click Recommender link</th><th>" + HelpfulMethods.now() + "</th><th>"+ recommenderPageUrl +"</th><th>"+recommenderPageTitle+"</th><th><a href=\"" + recommenderPageUrl + "\">Link To The Problem Page</a></th></tr>");
                loggerHTML.println("</table>");
                loggerHTML.flush();
                errorCounter++;
            }
            if (!recommenderPageUrl.equals("https://recommend" + sub_url + "/")&&!recommenderPageUrl.equals("http://recommend" + sub_url + "/")){
                String bugDescription;
                logger.println("Problems with Recommender. Wrong URL - " + HelpfulMethods.now());
                logger.flush();
                loggerHTML.println("<table border=\"1\">");
                loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
                loggerHTML.println("<tr><th>Problems with Recommender. Wrong URL</th><th>1.Open Accounts<br>2.Click Recommender link</th><th>" + HelpfulMethods.now() + "</th><th>"+ recommenderPageUrl +"</th><th>"+recommenderPageTitle+"</th><th><a href=\"" + recommenderPageUrl + "\">Link To The Problem Page</a></th></tr>");
                loggerHTML.println("</table>");
                loggerHTML.flush();
                errorCounter++;
                bugDescription = "Wrong URL at http://recommend"+sub_url+"/";
                SendMailTLS.main("automation@randrmusic.com", "dI6nzeedu8", "Alert QA Team!,"
                        + "\n\n Critical bug found !!!"
                        + "\n\n" + bugDescription);
            }
        }
        else {
            if (!recommenderPageTitle.equals("Recommend")){
                logger.println("Problems with Recommender. Wrong page title - " + HelpfulMethods.now());
                logger.flush();
                loggerHTML.println("<table border=\"1\">");
                loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
                loggerHTML.println("<tr><th>Problems with Recommender. Wrong Page Title</th><th>" + HelpfulMethods.now() + "</th><th>"+ recommenderPageUrl +"</th><th>"+recommenderPageTitle+"</th><th><a href=\"" + recommenderPageUrl + "\">Link To The Problem Page</a></th></tr>");
                loggerHTML.println("</table>");
                loggerHTML.flush();
                errorCounter++;
            }
            if (!recommenderPageUrl.startsWith("https://")){
                logger.println("Problems with Recommender. No HTTPS - " + HelpfulMethods.now());
                logger.flush();
                loggerHTML.println("<table border=\"1\">");
                loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
                loggerHTML.println("<tr><th>Problems with Recommender. No HTTPS</th><th>" + HelpfulMethods.now() + "</th><th>"+ recommenderPageUrl +"</th><th>"+recommenderPageTitle+"</th><th><a href=\"" + recommenderPageUrl + "\">Link To The Problem Page</a></th></tr>");
                loggerHTML.println("</table>");
                loggerHTML.flush();
            }
            if (!recommenderPageUrl.equals("https://recommender" + sub_url + "/")&&!recommenderPageUrl.equals("http://recommender" + sub_url + "/")){
                String bugDescription;
                logger.println("Problems with Recommender. Wrong URL - " + HelpfulMethods.now());
                logger.flush();
                loggerHTML.println("<table border=\"1\">");
                loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
                loggerHTML.println("<tr><th>Problems with Recommender. Wrong URL </th><th>" + HelpfulMethods.now() + "</th><th>"+ recommenderPageUrl +"</th><th>"+recommenderPageTitle+"</th><th><a href=\"" + recommenderPageUrl + "\">Link To The Problem Page</a></th></tr>");
                loggerHTML.println("</table>");
                loggerHTML.flush();
                errorCounter++;
                bugDescription = "Wrong URL at https://recommender"+sub_url+"/";
                SendMailTLS.main("automation@randrmusic.com", "dI6nzeedu8", "Alert QA Team!,"
                        + "\n\n Critical bug found !!!"
                        + "\n\n" + bugDescription);
            }
        }
        loggerHTML.println("<font color=\"green\">Testing Recommender Page - Complete</font><br><br>");
        loggerHTML.flush();
        System.out.println("Recommender Page - Checked");
        return errorCounter;
    }
    public static int checkLocatePage(String sub_url, WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter, Boolean browserFireFox) throws InterruptedException {
        System.out.println("Checking Locate Page");
        loggerHTML.println("Testing Locate Page<br>");
        loggerHTML.flush();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("accounts_locate")));
        if (browserFireFox){
            driver.findElement(By.xpath("/html/body/div[3]/div[6]/a/p[1]")).click();
        }
        else {
            driver.findElement(By.className("accounts_locate")).click();
        }
        Thread.sleep(3000);
        String locatePageTitle = driver.getTitle();
        String locatePageUrl = driver.getCurrentUrl();
        if (!locatePageTitle.equals("Locate")){
            logger.println("Problems with Locate. Wrong page title - " + HelpfulMethods.now());
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Problems with Locate. Wrong page title</th><th>1.Open Accounts<br>2.Click Locate link</th><th>" + HelpfulMethods.now() + "</th><th>"+ locatePageUrl +"</th><th>"+locatePageTitle+"</th><th><a href=\"" + locatePageUrl + "\">Link To The Problem Page</a></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        if (!locatePageUrl.startsWith("https://")){
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Problems with Locate. No HTTPS</th><th>1.Open Accounts<br>2.Click Locate link</th><th>" + HelpfulMethods.now() + "</th><th>"+ locatePageUrl +"</th><th>"+locatePageTitle+"</th><th><a href=\"" + locatePageUrl + "\">Link To The Problem Page</a></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        if (!locatePageUrl.equals("https://locate" + sub_url + "/")&&!locatePageUrl.equals("http://locate" + sub_url + "/")){
            String bugDescription;
            logger.println("Problems with Locate. Wrong URL - " + HelpfulMethods.now());
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Problems with Locate. Wrong URL</th><th>1.Open Accounts<br>2.Click Locate link</th><th>" + HelpfulMethods.now() + "</th><th>"+ locatePageUrl +"</th><th>"+locatePageTitle+"</th><th><a href=\"" + locatePageUrl + "\">Link To The Problem Page</a></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
            bugDescription = "Wrong URL at https://locate"+sub_url+"/";
            SendMailTLS.main("automation@randrmusic.com", "dI6nzeedu8", "Alert QA Team!,"
                    + "\n\n Critical bug found !!!"
                    + "\n\n" + bugDescription);
        }
        loggerHTML.println("<font color=\"green\">Testing Radio Page - Complete</font><br><br>");
        loggerHTML.flush();
        System.out.println("Locate Page - Checked");
        return errorCounter;
    }
    public static int checkMusicManagerPage(String sub_url, WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter, Boolean browserFireFox) throws InterruptedException {
        System.out.println("Checking Music Manager Page");
        loggerHTML.println("Testing Music Manager Page<br>");
        loggerHTML.flush();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("accounts_manager")));
        if (browserFireFox){
            driver.findElement(By.xpath("/html/body/div[3]/div[7]/a/p[1]")).click();
        }
        else {
            driver.findElement(By.className("accounts_manager")).click();
        }
        Thread.sleep(3000);
        String managerPageTitle = driver.getTitle();
        String managerPageUrl = driver.getCurrentUrl();
        if (!managerPageTitle.equals("tunehog Music Manager | Upload and share your music. Discover new artists")){
            logger.println("Problems with Music Manager. Wrong page title - " + HelpfulMethods.now());
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Problems with Music Manager. Wrong page title</th><th>1.Open Accounts<br>2.Click Music Manager link</th><th>" + HelpfulMethods.now() + "</th><th>"+ managerPageUrl +"</th><th>"+managerPageTitle+"</th><th><a href=\"" + managerPageUrl + "\">Link To The Problem Page</a></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        if (!managerPageUrl.startsWith("https://")){
            logger.println("Problems with Music Manager. No HTTPS - " + HelpfulMethods.now());
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Problems with Music Manager. No HTTPS</th><th>1.Open Accounts<br>2.Click Music Manager link</th><th>" + HelpfulMethods.now() + "</th><th>"+ managerPageUrl +"</th><th>"+managerPageTitle+"</th><th><a href=\"" + managerPageUrl + "\">Link To The Problem Page</a></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        if (!managerPageUrl.equals("https://musicmanager" + sub_url + "/")&&!managerPageUrl.equals("http://musicmanager" + sub_url + "/")){
            logger.println("Problems with Music Manager. Wrong URL - " + HelpfulMethods.now());
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Problems with Music Manager. Wrong URL or not HTTPS</th><th>1.Open Accounts<br>2.Click Music Manager link</th><th>" + HelpfulMethods.now() + "</th><th>"+ managerPageUrl +"</th><th>"+managerPageTitle+"</th><th><a href=\"" + managerPageUrl + "\">Link To The Problem Page</a></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
            String bugDescription = "Wrong URL at https://musicmanager" + sub_url + "/";
            SendMailTLS.main("automation@randrmusic.com", "dI6nzeedu8", "Alert QA Team!,"
                    + "\n\n Critical bug found !!!"
                    + "\n\n" + bugDescription);
        }
        loggerHTML.println("<font color=\"green\">Testing Music Manager Page - Complete</font><br><br>");
        loggerHTML.flush();
        System.out.println("Music Manager Page - Checked");
        return errorCounter;
    }
    public static int checkHitmakerPage(String sub_url, WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter, Boolean browserFireFox) throws InterruptedException {
        System.out.println("Checking Hitmaker Page");
        loggerHTML.println("Testing Hitmaker Page<br>");
        loggerHTML.flush();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("accounts_hitmaker")));
        if (browserFireFox){
            driver.findElement(By.xpath("/html/body/div[3]/div[8]/a/p[1]")).click();
        }
        else {
            driver.findElement(By.className("accounts_hitmaker")).click();
        }
        Thread.sleep(3000);
        String hitmakerPageTitle = driver.getTitle();
        String hitmakerPageUrl = driver.getCurrentUrl();
        if (!hitmakerPageTitle.equals("tunehog Hitmaker | Create a hit report for your song")){
            logger.println("Problems with Hitmaker. Wrong page title - " + HelpfulMethods.now());
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Problems with Hitmaker. Wrong page</th><th>1.Open Accounts<br>2.Click Hitmaker link</th><th>" + HelpfulMethods.now() + "</th><th>"+ hitmakerPageUrl +"</th><th>"+hitmakerPageTitle+"</th><th><a href=\"" + hitmakerPageUrl + "\">Link To The Problem Page</a></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        if (!hitmakerPageUrl.startsWith("https://")){
            logger.println("Problems with Hitmaker. No HTTPS - " + HelpfulMethods.now());
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Problems with Hitmaker. No https</th><th>1.Open Accounts<br>2.Click Hitmaker link</th><th>" + HelpfulMethods.now() + "</th><th>"+ hitmakerPageUrl +"</th><th>"+hitmakerPageTitle+"</th><th><a href=\"" + hitmakerPageUrl + "\">Link To The Problem Page</a></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        if (!hitmakerPageUrl.equals("https://hitmaker" + sub_url + "/")&&!hitmakerPageUrl.equals("http://hitmaker" + sub_url + "/")){
            logger.println("Problems with Hitmaker. Wrong URL - " + HelpfulMethods.now());
            logger.flush();
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
            loggerHTML.println("<tr><th>Problems with Hitmaker. Wrong url</th><th>1.Open Accounts<br>2.Click Hitmaker link</th><th>" + HelpfulMethods.now() + "</th><th>"+ hitmakerPageUrl +"</th><th>"+hitmakerPageTitle+"</th><th><a href=\"" + hitmakerPageUrl + "\">Link To The Problem Page</a></th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
            String bugDescription = "Wrong URL at https://hitmaker" + sub_url + "/";
            SendMailTLS.main("automation@randrmusic.com", "dI6nzeedu8", "Alert QA Team!,"
                    + "\n\n Critical bug found !!!"
                    + "\n\n" + bugDescription);
        }
        loggerHTML.println("<font color=\"green\">Testing Hitmaker Page - Complete</font><br><br>");
        loggerHTML.flush();
        System.out.println("Hitmaker Page - Checked");
        return errorCounter;
    }
    public static int checkSyncPage(String sub_url, WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter, Boolean browserFireFox, String environment) throws InterruptedException {
        System.out.println("Checking Sync Page");
        loggerHTML.println("Testing Sync Page<br>");
        loggerHTML.flush();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("accounts_sync")));
        if (browserFireFox){
            driver.findElement(By.xpath("/html/body/div[3]/div[9]/a/p[1]")).click();
        }
        else {
            driver.findElement(By.className("accounts_sync")).click();
        }
        Thread.sleep(3000);
        String syncPageTitle = driver.getTitle();
        String syncPageUrl = driver.getCurrentUrl();
        if (environment.equals("PROD")){
            if (!syncPageTitle.equals("Tunehog Sync")){
                logger.println("Problems with Sync. Wrong page title - " + HelpfulMethods.now());
                logger.flush();
                loggerHTML.println("<table border=\"1\">");
                loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
                loggerHTML.println("<tr><th>Problems with Sync. Wrong page title</th><th>1.Open Accounts<br>2.Click Sync link</th><th>" + HelpfulMethods.now() + "</th><th>"+ syncPageUrl +"</th><th>"+syncPageTitle+"</th><th><a href=\"" + syncPageUrl + "\">Link To The Problem Page</a></th></tr>");
                loggerHTML.println("</table>");
                loggerHTML.flush();
                errorCounter++;
            }
            if (!syncPageUrl.startsWith("https://")){
                logger.println("Problems with Sync. No HTTPS - " + HelpfulMethods.now());
                logger.flush();
                loggerHTML.println("<table border=\"1\">");
                loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
                loggerHTML.println("<tr><th>Problems with Sync. No HTTPS</th><th>1.Open Accounts<br>2.Click Sync link</th><th>" + HelpfulMethods.now() + "</th><th>"+ syncPageUrl +"</th><th>"+syncPageTitle+"</th><th><a href=\"" + syncPageUrl + "\">Link To The Problem Page</a></th></tr>");
                loggerHTML.println("</table>");
                loggerHTML.flush();
                errorCounter++;
            }
            if (!syncPageUrl.equals("https://sync" + sub_url + "/")&&!syncPageUrl.equals("http://sync" + sub_url + "/")){
                logger.println("Problems with Sync. Wrong URL or not HTTPS - " + HelpfulMethods.now());
                logger.flush();
                loggerHTML.println("<table border=\"1\">");
                loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
                loggerHTML.println("<tr><th>Problems with Sync. Wrong url</th><th>1.Open Accounts<br>2.Click Sync link</th><th>" + HelpfulMethods.now() + "</th><th>"+ syncPageUrl +"</th><th>"+syncPageTitle+"</th><th><a href=\"" + syncPageUrl + "\">Link To The Problem Page</a></th></tr>");
                loggerHTML.println("</table>");
                loggerHTML.flush();
                errorCounter++;
                String bugDescription = "Wrong URL at https://sync" + sub_url + "/";
                SendMailTLS.main("automation@randrmusic.com", "dI6nzeedu8", "Alert QA Team!,"
                        + "\n\n Critical bug found !!!"
                        + "\n\n" + bugDescription);
            }
        }
        else{
            if (!syncPageTitle.equals("Tunehog Sync")){
                logger.println("Problems with Sync. Wrong page title - " + HelpfulMethods.now());
                logger.flush();
                loggerHTML.println("<table border=\"1\">");
                loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
                loggerHTML.println("<tr><th>Problems with Sync. Wrong page title</th><th>" + HelpfulMethods.now() + "</th><th>"+ syncPageUrl +"</th><th>"+syncPageTitle+"</th><th><a href=\"" + syncPageUrl + "\">Link To The Problem Page</a></th></tr>");
                loggerHTML.println("</table>");
                loggerHTML.flush();
                errorCounter++;
            }
            if (!syncPageUrl.startsWith("https://")){
                logger.println("Problems with Sync. No HTTPS - " + HelpfulMethods.now());
                logger.flush();
                loggerHTML.println("<table border=\"1\">");
                loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
                loggerHTML.println("<tr><th>Problems with Sync. No HTTPS</th><th>" + HelpfulMethods.now() + "</th><th>"+ syncPageUrl +"</th><th>"+syncPageTitle+"</th><th><a href=\"" + syncPageUrl + "\">Link To The Problem Page</a></th></tr>");
                loggerHTML.println("</table>");
                loggerHTML.flush();
                errorCounter++;
            }
            if (!syncPageUrl.equals("https://syncability" + sub_url + "/")&&!syncPageUrl.equals("http://syncability" + sub_url + "/")){
                logger.println("Problems with Sync. Wrong URL - " + HelpfulMethods.now());
                logger.flush();
                loggerHTML.println("<table border=\"1\">");
                loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Page Url</th><th>Page Title</th><th>Link To The Problem Page</th></tr>");
                loggerHTML.println("<tr><th>Problems with Sync. Wrong URL</th><th>" + HelpfulMethods.now() + "</th><th>"+ syncPageUrl +"</th><th>"+syncPageTitle+"</th><th><a href=\"" + syncPageUrl + "\">Link To The Problem Page</a></th></tr>");
                loggerHTML.println("</table>");
                loggerHTML.flush();
                errorCounter++;
                String bugDescription = "Wrong URL at https://syncability" + sub_url + "/";
                SendMailTLS.main("automation@randrmusic.com", "dI6nzeedu8", "Alert QA Team!,"
                        + "\n\n Critical bug found !!!"
                        + "\n\n" + bugDescription);
            }
        }
        loggerHTML.println("<font color=\"green\">Testing Sync Page - Complete</font><br><br>");
        loggerHTML.flush();
        System.out.println("Sync Page - Checked");
        return errorCounter;
    }

}
