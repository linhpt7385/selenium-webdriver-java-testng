package giftcard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;

public class Login_WebFE {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }
    @Test
    public void TC_Login_01_Empty_Data(){
        driver.get("https://giftcard-sbb.payoo.com.vn/login");
        WebElement loginButton = driver.findElement(By.cssSelector("button#submit"));
        Assert.assertFalse(loginButton.isEnabled());
        //verify button Đăng nhập - convert rgba to hexa
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(),"#000000");
    }
    @Test
    public void TC_Login_02_Incorrect_Username(){

    }
    @Test
    public void TC_Login_03_Incorrect_Password(){

    }
    @Test
    public void TC_Login_04_Incorrect_VerifyCode(){

    }
    @Test
    public void TC_Login_05_Incorrect_User_Pass(){
        driver.get("https://giftcard-sbb.payoo.com.vn/login");
        WebElement loginButton = driver.findElement(By.cssSelector("button#submit"));

        driver.findElement(By.cssSelector("input#username")).sendKeys("phanthelinh");
        driver.findElement(By.cssSelector("input#passworsd")).sendKeys("123456789A");
        driver.findElement(By.cssSelector("input#captcha")).sendKeys();

        Assert.assertTrue(loginButton.isEnabled());
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(),"#1e88e5");
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("button#submit")).click();

    }
    @Test
    public void TC_Login_06_Success(){

    }
    @AfterClass
    public void afterClass() {
        driver.quit();

    }
    public void sleepInSeconds(long timeInSeconds){
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
