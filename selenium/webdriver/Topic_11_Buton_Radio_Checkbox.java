package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Topic_11_Buton_Radio_Checkbox {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Egov_Button(){
        driver.get("https://egov.danang.gov.vn/reg");

        WebElement registerButton = driver.findElement(By.cssSelector("input.egov-button"));

        //verify disable khi chưa click vào checkbox
        Assert.assertFalse(registerButton.isEnabled());

        driver.findElement(By.cssSelector("input#chinhSach")).click();
        sleepInSeconds(3);

        //verify button đã được enable sau khi lick vào
        Assert.assertTrue(registerButton.isEnabled());

//        //lấy ra màu nền của button
//        String registerBackgroundRGB = registerButton.getCssValue("background-color");
//        System.out.println("Background color RGB: " +registerBackgroundRGB);
//
//        //convert từ kiểu RGB sang kiểu color
//        Color registerBackgroundColor = Color.fromString(registerBackgroundRGB);
//
//        //Convert qua kiểu hexa
//        String registerBackgroundHexa = registerBackgroundColor.asHex();
//        System.out.println("Background color RGB: " +registerBackgroundHexa);

        Assert.assertEquals(Color.fromString(registerButton.getCssValue("background-color")).asHex().toLowerCase(),"#ef5a00");

    }
    @Test
    public void TC_02_Fahasa_Button(){
        driver.get("https://www.fahasa.com/customer/account/create");

        //chuyển qua tab đăng nhập
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        sleepInSeconds(2);

        WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));

        //verify login button is disable
        Assert.assertFalse(loginButton.isEnabled());

        //Verify Login button = background
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(),"#00000000");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("linhpt@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("12345678");
        sleepInSeconds(3);

        //verify login button is enable
        Assert.assertTrue(loginButton.isEnabled());

        //Verify Login button = background
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(),"#C92127");

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
