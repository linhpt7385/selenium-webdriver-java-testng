package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_16_Shadow_DOM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Shadow_Dom(){
        driver.get("https://automationfc.github.io/shadow-dom/");
        sleepInSeconds(2);
        //shadowRootContext là cái shadow DOM 1 bên trong
        WebElement shadowHostElement = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext shadowRootContext = shadowHostElement.getShadowRoot();

        String text1 = shadowRootContext.findElement(By.cssSelector("span#shadow_content>span")).getText();
        System.out.println(text1);
        Assert.assertEquals(text1,"some text");

        WebElement checkboxShadow = shadowRootContext.findElement(By.cssSelector("input[type=checkbox]"));
        Assert.assertFalse(checkboxShadow.isSelected());

        List<WebElement> allIput = shadowRootContext.findElements(By.cssSelector("input"));
        System.out.println(allIput.size());

        //nestedShadowRootContext - đại diện cho DOm 2
        WebElement shadow2 = shadowRootContext.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext nestedShadowRootContext = shadow2.getShadowRoot();

        String text2 = nestedShadowRootContext.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();
        Assert.assertEquals(text2,"nested text");

    }
    @Test
    public void TC_02_Random_InDom(){
        driver.get("https://shopee.vn/");
        sleepInSeconds(5);

        WebElement shadowRootElentment = driver.findElement(By.cssSelector("div.home-page>shopee-banner-popup-stateful"));
        SearchContext shadowRootContext = shadowRootElentment.getShadowRoot();

        //verify popup hiển thị
        if(shadowRootContext.findElements(By.cssSelector("div.home-popup__content")).size()>0
                && shadowRootContext.findElements(By.cssSelector("div.home-popup__content")).get(0).isDisplayed()){
            shadowRootContext.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
            sleepInSeconds(3);
        }
        //Nếu ko hiển thị hoặc đã đóng thì vào search iphone 15 pro max
        driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("Iphone 15 Pro max");
        sleepInSeconds(3);
        driver.findElement(By.cssSelector("button.shopee-searchbar__search-button")).click();
        sleepInSeconds(5);

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
