package com.me.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.PrintWriter;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Igor
 * Date: 9/3/13
 * Time: 3:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommonTests {
    public static int musicTest(WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter, String sub_url) throws InterruptedException {
        System.out.println("Checking Recommender Music");
        loggerHTML.println("Testing Recommender Music<br>");
        loggerHTML.flush();
        driver.findElement(By.partialLinkText("Music")).click();
        Thread.sleep(30000);
        List<WebElement> listOfRecommendedItems = driver.findElements(By.className("recommender_like_block_img"));
        System.out.println(listOfRecommendedItems.size());
        Actions mouseMover = new Actions(driver);
        System.out.println("Adding items to Top 5 (Drag'n'Drop)");
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("top5"))).build().perform();
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("top5"))).build().perform();
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("top5"))).build().perform();
        Thread.sleep(1000);
        List<WebElement> top5 = driver.findElements(By.className("recommender_top5_title_item"));
        if (top5.size()==0){
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Actual Result</th><th>Expected Result</th></tr>");
            loggerHTML.println("<tr><th>No items in Top 5 list</th><th>1. Open Recommender<br>2.Drag and drop a few items to Top 5 list<br>3.Observe</th><th>" + HelpfulMethods.now() + " </th><th>No items in Top 5 list</th><th>Items in Top 5 list</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        System.out.println("Liking items (Drag'n'Drop)");
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("like"))).build().perform();
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("like"))).build().perform();
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("like"))).build().perform();
        List<WebElement> likedItems = driver.findElements(By.className("recommender_recomen_item"));
        if (likedItems.size()==0){
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Actual Result</th><th>Expected Result</th></tr>");
            loggerHTML.println("<tr><th>No liked items in Liked Items list</th><th>1. Open Recommender<br>2. Click any of recommended items<br>3. Click Like button<br>4. Observe</th><th>" + HelpfulMethods.now() + " </th><th>No liked items in Liked Items list</th><th>Items in Liked Items list</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }

        System.out.println("Removing items from Top 5 list");
        top5 = driver.findElements(By.className("recommender_block_top_img"));
        int top5SizeBeg = top5.size();
        try {
            driver.findElement(By.className("recommender_top5_setting")).click();
        } catch (Exception e) {
            mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("like"))).build().perform();
            driver.findElement(By.className("recommender_top5_setting")).click();
        }

        WebElement top5Item = top5.get(0);
        WebElement top5ItemDropdown = top5Item.findElement(By.className("recommender_prop_dounw_small"));
        WebElement top5ItemDropdownWrapper = top5ItemDropdown.findElement(By.className("recommender_drop_wraper"));
        top5ItemDropdownWrapper.findElement(By.className("recommender_drop_dislike")).click();
        Thread.sleep(2000);
        top5 = driver.findElements(By.className("recommender_block_top_img"));
        int top5SizeEnd = top5.size();
        if (top5SizeBeg==top5SizeEnd){
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Actual Result</th><th>Expected Result</th></tr>");
            loggerHTML.println("<tr><th>Cannot remove item from Top 5 list</th><th>1. Open Recommender<br>2. Make sure you have items in Top 5 list<br>3. Click settings button for any item in Top 5 list<br>4. Click Dislike<br>5. Observe</th><th>" + HelpfulMethods.now() + " </th><th>Item was not removed</th><th>Disliked Items should be removed from Top 5 list</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }

        System.out.println("Disliking items");
        likedItems = driver.findElements(By.className("recommender_recomen_item"));
        int size = likedItems.size();
        for (int i = 0; i<size; i++){
            likedItems = driver.findElements(By.className("recommender_recomen_item"));
            WebElement likedItemSettingsWrapper = likedItems.get(0).findElement(By.className("recommender_setting_wraper"));
            WebElement likedItemSettingsWrapperButton = likedItemSettingsWrapper.findElement(By.className("recommender_item_settings"));
            likedItemSettingsWrapperButton.click();
            Thread.sleep(2000);
            WebElement likedItemDropdown = likedItems.get(0).findElement(By.className("recommender_setting_dropdown"));
            WebElement likedItemDropdownIcons = likedItemDropdown.findElement(By.className("recommender_dropdounw_icons"));
            likedItemDropdownIcons.findElement(By.className("recommender_drop_dislike")).click();
            Thread.sleep(5000);
        }
        likedItems = driver.findElements(By.className("recommender_recomen_item"));
        int sizeAft = likedItems.size();
        if (size == sizeAft){
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Actual Result</th><th>Expected Result</th></tr>");
            loggerHTML.println("<tr><th>Cannot remove item from Liked list</th><th>1. Open Recommender<br>2. Make sure you have items in Liked list<br>3. Click settings button for any item in Liked list<br>4. Click Dislike<br>5. Observe</th><th>" + HelpfulMethods.now() + " </th><th>Item was not removed</th><th>Disliked Items should be removed from Liked list</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        loggerHTML.println("<font color=\"green\">Testing Recommender Music - Complete</font><br><br>");
        loggerHTML.flush();
        System.out.println("Checking Recommender Music - Complete");
        return errorCounter;
    }
    public static int filmsTest(WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter, String sub_url) throws InterruptedException {
        System.out.println("Checking Recommender Films");
        loggerHTML.println("Testing Recommender Films<br>");
        loggerHTML.flush();
        driver.findElement(By.partialLinkText("Films")).click();
        Thread.sleep(30000);
        List<WebElement> listOfRecommendedItems = driver.findElements(By.className("recommender_like_block_img"));
        System.out.println(listOfRecommendedItems.size());
        Actions mouseMover = new Actions(driver);
        System.out.println("Adding items to Top 5 (Drag'n'Drop)");
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("top5"))).build().perform();
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("top5"))).build().perform();
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("top5"))).build().perform();
        Thread.sleep(1000);
        List<WebElement> top5 = driver.findElements(By.className("recommender_top5_title_item"));
        if (top5.size()==0){
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Actual Result</th><th>Expected Result</th></tr>");
            loggerHTML.println("<tr><th>No items in Top 5 list</th><th>1. Open Recommender<br>2.Drag and drop a few items to Top 5 list<br>3.Observe</th><th>" + HelpfulMethods.now() + " </th><th>No items in Top 5 list</th><th>Items in Top 5 list</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        System.out.println("Liking items (Drag'n'Drop)");
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("like"))).build().perform();
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("like"))).build().perform();
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("like"))).build().perform();
        Thread.sleep(3000);
        List<WebElement> likedItems = driver.findElements(By.className("recommender_recomen_item"));
        if (likedItems.size()==0){
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Actual Result</th><th>Expected Result</th></tr>");
            loggerHTML.println("<tr><th>No liked items in Liked Items list</th><th>1. Open Recommender<br>2. Click any of recommended items<br>3. Click Like button<br>4. Observe</th><th>" + HelpfulMethods.now() + " </th><th>No liked items in Liked Items list</th><th>Items in Liked Items list</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }

        System.out.println("Removing items from Top 5 list");
        top5 = driver.findElements(By.className("recommender_block_top_img"));
        int top5SizeBeg = top5.size();
        try {
            driver.findElement(By.className("recommender_top5_setting")).click();
        } catch (Exception e) {
            mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("like"))).build().perform();
            driver.findElement(By.className("recommender_top5_setting")).click();
        }

        WebElement top5Item = top5.get(0);
        WebElement top5ItemDropdown = top5Item.findElement(By.className("recommender_prop_dounw_small"));
        WebElement top5ItemDropdownWrapper = top5ItemDropdown.findElement(By.className("recommender_drop_wraper"));
        top5ItemDropdownWrapper.findElement(By.className("recommender_drop_dislike")).click();
        Thread.sleep(2000);
        top5 = driver.findElements(By.className("recommender_block_top_img"));
        int top5SizeEnd = top5.size();
        if (top5SizeBeg==top5SizeEnd){
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Actual Result</th><th>Expected Result</th></tr>");
            loggerHTML.println("<tr><th>Cannot remove item from Top 5 list</th><th>1. Open Recommender<br>2. Make sure you have items in Top 5 list<br>3. Click settings button for any item in Top 5 list<br>4. Click Dislike<br>5. Observe</th><th>" + HelpfulMethods.now() + " </th><th>Item was not removed</th><th>Disliked Items should be removed from Top 5 list</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }

        System.out.println("Disliking items");
        likedItems = driver.findElements(By.className("recommender_recomen_item"));
        int size = likedItems.size();
        for (int i = 0; i<size; i++){
            likedItems = driver.findElements(By.className("recommender_recomen_item"));
            WebElement likedItemSettingsWrapper = likedItems.get(0).findElement(By.className("recommender_setting_wraper"));
            WebElement likedItemSettingsWrapperButton = likedItemSettingsWrapper.findElement(By.className("recommender_item_settings"));
            likedItemSettingsWrapperButton.click();
            Thread.sleep(2000);
            WebElement likedItemDropdown = likedItems.get(0).findElement(By.className("recommender_setting_dropdown"));
            WebElement likedItemDropdownIcons = likedItemDropdown.findElement(By.className("recommender_dropdounw_icons"));
            likedItemDropdownIcons.findElement(By.className("recommender_drop_dislike")).click();
            Thread.sleep(5000);
        }
        likedItems = driver.findElements(By.className("recommender_recomen_item"));
        int sizeAft = likedItems.size();
        if (size == sizeAft){
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Actual Result</th><th>Expected Result</th></tr>");
            loggerHTML.println("<tr><th>Cannot remove item from Liked list</th><th>1. Open Recommender<br>2. Make sure you have items in Liked list<br>3. Click settings button for any item in Liked list<br>4. Click Dislike<br>5. Observe</th><th>" + HelpfulMethods.now() + " </th><th>Item was not removed</th><th>Disliked Items should be removed from Liked list</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        loggerHTML.println("<font color=\"green\">Testing Recommender Films - Complete</font><br><br>");
        loggerHTML.flush();
        System.out.println("Checking Recommender Films - Complete");
        return errorCounter;
    }
    public static int booksTest(WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter, String sub_url) throws InterruptedException {
        System.out.println("Checking Recommender Books");
        loggerHTML.println("Testing Recommender Books<br>");
        loggerHTML.flush();
        driver.findElement(By.partialLinkText("Books")).click();
        Thread.sleep(30000);
        List<WebElement> listOfRecommendedItems = driver.findElements(By.className("recommender_like_block_img"));
        System.out.println(listOfRecommendedItems.size());
        Actions mouseMover = new Actions(driver);
        System.out.println("Adding items to Top 5 (Drag'n'Drop)");
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("top5"))).build().perform();
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("top5"))).build().perform();
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("top5"))).build().perform();
        Thread.sleep(1000);
        List<WebElement> top5 = driver.findElements(By.className("recommender_top5_title_item"));
        if (top5.size()==0){
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Actual Result</th><th>Expected Result</th></tr>");
            loggerHTML.println("<tr><th>No items in Top 5 list</th><th>1. Open Recommender<br>2.Drag and drop a few items to Top 5 list<br>3.Observe</th><th>" + HelpfulMethods.now() + " </th><th>No items in Top 5 list</th><th>Items in Top 5 list</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        System.out.println("Liking items (Drag'n'Drop)");
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("like"))).build().perform();
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("like"))).build().perform();
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("like"))).build().perform();
        List<WebElement> likedItems = driver.findElements(By.className("recommender_recomen_item"));
        if (likedItems.size()==0){
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Actual Result</th><th>Expected Result</th></tr>");
            loggerHTML.println("<tr><th>No liked items in Liked Items list</th><th>1. Open Recommender<br>2. Click any of recommended items<br>3. Click Like button<br>4. Observe</th><th>" + HelpfulMethods.now() + " </th><th>No liked items in Liked Items list</th><th>Items in Liked Items list</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }

        System.out.println("Removing items from Top 5 list");
        top5 = driver.findElements(By.className("recommender_block_top_img"));
        int top5SizeBeg = top5.size();
        try {
            driver.findElement(By.className("recommender_top5_setting")).click();
        } catch (Exception e) {
            mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("like"))).build().perform();
            driver.findElement(By.className("recommender_top5_setting")).click();
        }

        WebElement top5Item = top5.get(0);
        WebElement top5ItemDropdown = top5Item.findElement(By.className("recommender_prop_dounw_small"));
        WebElement top5ItemDropdownWrapper = top5ItemDropdown.findElement(By.className("recommender_drop_wraper"));
        top5ItemDropdownWrapper.findElement(By.className("recommender_drop_dislike")).click();
        Thread.sleep(2000);
        top5 = driver.findElements(By.className("recommender_block_top_img"));
        int top5SizeEnd = top5.size();
        if (top5SizeBeg==top5SizeEnd){
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Actual Result</th><th>Expected Result</th></tr>");
            loggerHTML.println("<tr><th>Cannot remove item from Top 5 list</th><th>1. Open Recommender<br>2. Make sure you have items in Top 5 list<br>3. Click settings button for any item in Top 5 list<br>4. Click Dislike<br>5. Observe</th><th>" + HelpfulMethods.now() + " </th><th>Item was not removed</th><th>Disliked Items should be removed from Top 5 list</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }

        System.out.println("Disliking items");
        likedItems = driver.findElements(By.className("recommender_recomen_item"));
        int size = likedItems.size();
        for (int i = 0; i<size; i++){
            likedItems = driver.findElements(By.className("recommender_recomen_item"));
            WebElement likedItemSettingsWrapper = likedItems.get(0).findElement(By.className("recommender_setting_wraper"));
            WebElement likedItemSettingsWrapperButton = likedItemSettingsWrapper.findElement(By.className("recommender_item_settings"));
            likedItemSettingsWrapperButton.click();
            Thread.sleep(2000);
            WebElement likedItemDropdown = likedItems.get(0).findElement(By.className("recommender_setting_dropdown"));
            WebElement likedItemDropdownIcons = likedItemDropdown.findElement(By.className("recommender_dropdounw_icons"));
            likedItemDropdownIcons.findElement(By.className("recommender_drop_dislike")).click();
            Thread.sleep(5000);
        }
        likedItems = driver.findElements(By.className("recommender_recomen_item"));
        int sizeAft = likedItems.size();
        if (size == sizeAft){
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Actual Result</th><th>Expected Result</th></tr>");
            loggerHTML.println("<tr><th>Cannot remove item from Liked list</th><th>1. Open Recommender<br>2. Make sure you have items in Liked list<br>3. Click settings button for any item in Liked list<br>4. Click Dislike<br>5. Observe</th><th>" + HelpfulMethods.now() + " </th><th>Item was not removed</th><th>Disliked Items should be removed from Liked list</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        loggerHTML.println("<font color=\"green\">Testing Recommender Books - Complete</font><br><br>");
        loggerHTML.flush();
        System.out.println("Checking Recommender Books - Complete");
        return errorCounter;
    }
    public static int TVTest(WebDriver driver, WebDriverWait wait, PrintWriter logger, PrintWriter loggerHTML, int errorCounter, String sub_url) throws InterruptedException {
        System.out.println("Checking Recommender TV");
        loggerHTML.println("Testing Recommender TV<br>");
        loggerHTML.flush();
        driver.findElement(By.partialLinkText("TV Shows")).click();
        Thread.sleep(30000);
        List<WebElement> listOfRecommendedItems = driver.findElements(By.className("recommender_like_block_img"));
        System.out.println(listOfRecommendedItems.size());
        Actions mouseMover = new Actions(driver);
        System.out.println("Adding items to Top 5 (Drag'n'Drop)");
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("top5"))).build().perform();
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("top5"))).build().perform();
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("top5"))).build().perform();
        Thread.sleep(1000);
        List<WebElement> top5 = driver.findElements(By.className("recommender_top5_title_item"));
        if (top5.size()==0){
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Actual Result</th><th>Expected Result</th></tr>");
            loggerHTML.println("<tr><th>No items in Top 5 list</th><th>1. Open Recommender<br>2.Drag and drop a few items to Top 5 list<br>3.Observe</th><th>" + HelpfulMethods.now() + " </th><th>No items in Top 5 list</th><th>Items in Top 5 list</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        System.out.println("Liking items (Drag'n'Drop)");
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("like"))).build().perform();
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("like"))).build().perform();
        mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("like"))).build().perform();
        List<WebElement> likedItems = driver.findElements(By.className("recommender_recomen_item"));
        if (likedItems.size()==0){
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Actual Result</th><th>Expected Result</th></tr>");
            loggerHTML.println("<tr><th>No liked items in Liked Items list</th><th>1. Open Recommender<br>2. Click any of recommended items<br>3. Click Like button<br>4. Observe</th><th>" + HelpfulMethods.now() + " </th><th>No liked items in Liked Items list</th><th>Items in Liked Items list</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }

        System.out.println("Removing items from Top 5 list");
        top5 = driver.findElements(By.className("recommender_block_top_img"));
        int top5SizeBeg = top5.size();
        try {
            driver.findElement(By.className("recommender_top5_setting")).click();
        } catch (Exception e) {
            mouseMover.dragAndDrop(listOfRecommendedItems.get(HelpfulMethods.getRandomNumber(0,14)),driver.findElement(By.className("like"))).build().perform();
            driver.findElement(By.className("recommender_top5_setting")).click();
        }

        WebElement top5Item = top5.get(0);
        WebElement top5ItemDropdown = top5Item.findElement(By.className("recommender_prop_dounw_small"));
        WebElement top5ItemDropdownWrapper = top5ItemDropdown.findElement(By.className("recommender_drop_wraper"));
        top5ItemDropdownWrapper.findElement(By.className("recommender_drop_dislike")).click();
        Thread.sleep(2000);
        top5 = driver.findElements(By.className("recommender_block_top_img"));
        int top5SizeEnd = top5.size();
        if (top5SizeBeg==top5SizeEnd){
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Actual Result</th><th>Expected Result</th></tr>");
            loggerHTML.println("<tr><th>Cannot remove item from Top 5 list</th><th>1. Open Recommender<br>2. Make sure you have items in Top 5 list<br>3. Click settings button for any item in Top 5 list<br>4. Click Dislike<br>5. Observe</th><th>" + HelpfulMethods.now() + " </th><th>Item was not removed</th><th>Disliked Items should be removed from Top 5 list</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }

        System.out.println("Disliking items");
        likedItems = driver.findElements(By.className("recommender_recomen_item"));
        int size = likedItems.size();
        for (int i = 0; i<size; i++){
            likedItems = driver.findElements(By.className("recommender_recomen_item"));
            WebElement likedItemSettingsWrapper = likedItems.get(0).findElement(By.className("recommender_setting_wraper"));
            WebElement likedItemSettingsWrapperButton = likedItemSettingsWrapper.findElement(By.className("recommender_item_settings"));
            likedItemSettingsWrapperButton.click();
            Thread.sleep(2000);
            WebElement likedItemDropdown = likedItems.get(0).findElement(By.className("recommender_setting_dropdown"));
            WebElement likedItemDropdownIcons = likedItemDropdown.findElement(By.className("recommender_dropdounw_icons"));
            likedItemDropdownIcons.findElement(By.className("recommender_drop_dislike")).click();
            Thread.sleep(5000);
        }
        likedItems = driver.findElements(By.className("recommender_recomen_item"));
        int sizeAft = likedItems.size();
        if (size == sizeAft){
            loggerHTML.println("<table border=\"1\">");
            loggerHTML.println("<tr><th>Bug</th><th>Steps To Reproduce</th><th>Time</th><th>Actual Result</th><th>Expected Result</th></tr>");
            loggerHTML.println("<tr><th>Cannot remove item from Liked list</th><th>1. Open Recommender<br>2. Make sure you have items in Liked list<br>3. Click settings button for any item in Liked list<br>4. Click Dislike<br>5. Observe</th><th>" + HelpfulMethods.now() + " </th><th>Item was not removed</th><th>Disliked Items should be removed from Liked list</th></tr>");
            loggerHTML.println("</table>");
            loggerHTML.flush();
            errorCounter++;
        }
        loggerHTML.println("<font color=\"green\">Testing Recommender Films - Complete</font><br><br>");
        loggerHTML.flush();
        System.out.println("Checking Recommender Films - Complete");
        return errorCounter;
    }
}
