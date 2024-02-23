package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_31_Wait_10_Page_Ready {
    WebDriver driver;

    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));


    }

    @Test
    public void TC_01_Admin_NonCommerce(){
        driver.get("https://admin-demo.nopcommerce.com/");

        driver.findElement(By.cssSelector("input#Email")).clear();
        driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");

        driver.findElement(By.cssSelector("input#Password")).clear();
        driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.login-button")).click();

        Assert.assertTrue(isPageLoadedSuccess());

        explicitWait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//i[contains(@class,'fa-user')]/following-sibling::p"))).click();
        driver.findElement(By.
                xpath("//ul[contains(@style,'display: block;')]//i[contains(@class,'fa-dot-circle')]/following-sibling::p[contains(string(),'Customers')]")).click();
        Assert.assertTrue(isPageLoadedSuccess());

        explicitWait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//i[contains(@class,'fa-book')]/following-sibling::p"))).click();
        driver.findElement(By.
                xpath("//ul[contains(@style,'display: block;')]//i[contains(@class,'fa-dot-circle')]/following-sibling::p[contains(string(),'Products')]")).click();
        Assert.assertTrue(isPageLoadedSuccess());

    }
    @Test
    public void TC_02_OrangeHRM_API_Document(){
        driver.get("https://api.orangehrm.com/");

        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loader>div.spinner"))));

        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='OrangeHRM REST API Documentation']")).isDisplayed());
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public boolean isPageLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }
}
