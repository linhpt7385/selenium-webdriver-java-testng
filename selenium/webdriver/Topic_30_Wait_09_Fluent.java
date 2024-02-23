package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic_30_Wait_09_Fluent {
    WebDriver driver;
    FluentWait<WebElement> fluentElement;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        //fluentDriver = new FluentWait<WebDriver>(driver);
    }

    @Test
    public void TC_01_(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        waitAndFindElement(By.cssSelector("div#start>button")).click();
        String helloText = waitAndFindElement(By.xpath("//div[@id='finish']/h4")).getText();
        Assert.assertEquals(helloText, "Hello World!");

//        driver.findElement(By.cssSelector("div#start>button")).click();
//
//        //chờ cho HelloWord hiển thị trong 10s
//        //setting
//        fluentDriver.withTimeout(Duration.ofSeconds(10))
//                .pollingEvery(Duration.ofMillis(100))
//                .ignoring(NoSuchElementException.class);
//
//        //condition
////        fluentDriver.until(new Function<WebDriver, Boolean>() {
////            @Override
////            public Boolean apply(WebDriver webDriver) {
////                return webDriver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
////            }
////        });
//
//        String helloText = fluentDriver.until(new Function<WebDriver, String>() {
//            @Override
//            public String apply(WebDriver driver) {
//                String text = driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();
//                System.out.println("Get text: " +text);
//                return text;
//            }
//        });
//        Assert.assertEquals(helloText, "Hello World!");
    }
    @Test
    public void TC_02_(){
        driver.get("https://automationfc.github.io/fluent-wait/");

        WebElement countDownTime = driver.findElement(By.cssSelector("div#javascript_countdown_time"));
        fluentElement = new FluentWait<WebElement>(countDownTime);

        fluentElement.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
        fluentElement.until(new Function<WebElement, Boolean>() {

            @Override
            public Boolean apply(WebElement webElement) {
                String text = webElement.getText();
                System.out.println(text);
                return text.endsWith("00");
            }
        });
    }

    public WebElement waitAndFindElement(By locator) {
        FluentWait<WebDriver> fluentDriver = new FluentWait<WebDriver>(driver);
        fluentDriver.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        return fluentDriver.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(locator);
            }
        });
    }
    @AfterClass
    public void afterClass() {
        driver.quit();

    }
}
