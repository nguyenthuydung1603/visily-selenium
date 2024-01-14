/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dungnt.visilytest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author THUYDUNG
 */
public class OnboardingTest {
    ChromeDriver cd = new ChromeDriver();
    String email;
    String password;
    String name;
    ExtentReports reports = new ExtentReports("C:\\Users\\dell\\Desktop\\excel\\report.html");
    ExtentTest test = reports.startTest("Report Test Case OB_01");

    @BeforeClass
    public void beforeClass() {
        email = "dungnt" + generateFakeNumber() + "@gmail.vn";
        password ="123456";
        name = "Meo";
        cd.manage().window().maximize();
        cd.get("https://www.visily.ai/");
    }

    @Test
    public void testMain() throws InterruptedException {
        try {
            cd.findElement(By.xpath("//header/div[1]/section[2]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/a[1]")).click();
            cd.findElement(By.xpath("//input[@placeholder='Work email']")).sendKeys(email, Keys.ENTER);
            cd.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys(name);
            cd.findElement(By.xpath("//input[@placeholder='Password (8+ characters)']")).sendKeys(password, Keys.ENTER);
            
            if(cd.findElement(By.xpath("//div[contains(text(),'Please answer a few questions to help us make the ')]']")).isDisplayed()) {
                test.log(LogStatus.PASS, "Navigate to Screen 1");
            } else {
                test.log(LogStatus.FAIL, "Not navigate to Screen 1");
            }
            
            cd.findElement(By.xpath("//div[@class='animate-welcome-show-up']//div[1]//div[1]")).click();
            
            if(cd.findElement(By.xpath("//div[contains(text(),'What’s your role?')]")).isDisplayed()) {
                test.log(LogStatus.PASS, "Navigate to Screen 2");
            } else {
                test.log(LogStatus.FAIL, "Not navigate to Screen 2");
            }
            
            cd.findElement(By.xpath("//div[normalize-space()='Developer']")).click();
            
            if(cd.findElement(By.xpath("//div[contains(text(),'How many employees does your company have?')]")).isDisplayed()) {
                test.log(LogStatus.PASS, "Navigate to Screen 3");
            } else {
                test.log(LogStatus.FAIL, "Not navigate to Screen 3");
            }
            
            cd.findElement(By.cssSelector("div:nth-child(5)")).click();
            
            if(cd.findElement(By.xpath("//div[contains(text(),'How did you hear about Visily?')]")).isDisplayed()){
                test.log(LogStatus.PASS, "Navigate to Screen 4");
            } else {
                test.log(LogStatus.FAIL, "Not navigate to Screen 4");
            }
            
            cd.findElement(By.xpath("//input[@placeholder='Facebook']")).click();
      
            if(cd.findElement(By.xpath("//div[contains(text(),'What do you want to work on now?')]")).isDisplayed()){
                test.log(LogStatus.PASS, "Navigate to Screen 5");
            } else {
                test.log(LogStatus.FAIL, "Not navigate to Screen 5");
            }
            reports.endTest(test);
        } catch (Exception e) {
            test.log(LogStatus.FAIL, "Something went wrong");
        }
    }

    @AfterClass
    public void afterClass() {
        reports.flush();
        cd.quit();
    }
    
    public int generateFakeNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);

    }
}
