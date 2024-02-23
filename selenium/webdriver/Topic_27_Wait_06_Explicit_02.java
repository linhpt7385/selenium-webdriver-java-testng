package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_27_Wait_06_Explicit_02 {
    WebDriver driver;

    WebDriverWait explicitWait; //khai báo chưa khởi tạo
    @BeforeClass //precondition - khởi tạo dữ liệu / data test/ page class / variable
    public void beforeClass() {
        driver = new FirefoxDriver();

    }

    @Test
    public void TC_01_equal_5_second(){
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));

        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
        //wait cho cái icon loading biến mất trong x giây
        //explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

        //wait cho Hello World! xuất hiện
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }
    @Test
    public void TC_02_Less_Than_5_Second(){
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(3));

        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();

        //explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }
    @Test
    public void TC_02_Greater_Than_5_Second(){
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(100));

        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();

        //explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }
    @AfterClass
    public void afterClass() {
        driver.quit();

    }

}
