package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_29_Wait_08_Mix_Implicit_Explicit {
    WebDriver driver;
    WebDriverWait explicitWait;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_Only_Implicit_Found(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");

        //khi vào tìm element thì nó tìm thấy ngay, ko cần chờ hết timeout
        driver.findElement(By.cssSelector("input#email"));

    }
    @Test
    public void TC_02_Only_Implicit_Not_Found(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");

        //khi vào tìm không thấy thì nó mỗi 0,5s nó tìm lại đến khi hết timeout sẽ đánh fail TC: NoSuchElementException
        driver.findElement(By.cssSelector("input#automation"));
    }
    @Test
    public void TC_03_Only_Explicit_Found_Param_By(){
        driver.get("https://www.facebook.com/");

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        //khi vào tìm element thì nó tìm thấy ngay, ko cần chờ hết timeout
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));

    }
    @Test
    public void TC_04_Only_Explicit_Not_Found_Param_By(){
        driver.get("https://www.facebook.com/");

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        //khi vào tìm không thấy thì nó mỗi 0,5s nó tìm lại đến khi hết timeout sẽ đánh fail TC: TimeoutException
        //TimeoutException: Expected condition failed: waiting for visibility of element located by By.cssSelector: input#automation
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#automation")));
    }
    @Test
    public void TC_05_Only_Explicit_Not_Found_Param_WebElement(){
        driver.get("https://www.facebook.com/");

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        //khi vào tìm không thấy thì nó mỗi 0,5s nó tìm lại đến khi hết timeout sẽ đánh fail TC:
        System.out.println("Start time: " +getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#automation"))));
        }catch (Exception e){
            System.out.println("End time: " +getDateTimeNow());
            e.printStackTrace();
        }
    }
    @Test
    public void TC_06_Mix_Explicit_Implicit(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        System.out.println("Start time: " +getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#automation")));
        }catch (Exception e){
            System.out.println("End time: " +getDateTimeNow());
            e.printStackTrace();
        }
    }
    @AfterClass
    public void afterClass() {
        driver.quit();

    }
    public String getDateTimeNow(){
        Date date = new Date();
        return date.toString();
    }

}
