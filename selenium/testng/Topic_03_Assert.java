package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_03_Assert {
    WebDriver driver;
    @Test
    public void test01(){
        String fullName = "Payoo - Thanh toan truc tuyen";
        Assert.assertEquals(fullName,"Payoo","Vietunion");

        //Mong đợi kết quả trả về đúng
        Assert.assertTrue(isElementDisplayed(By.cssSelector("")));
        Assert.assertTrue(isElementDisplayed(By.cssSelector("")), "Element is not displayed");

        //Mong đợi kết quả trả về sai
        Assert.assertFalse(isElementDisplayed(By.cssSelector("")));
    }
    private boolean isElementDisplayed(By locator){
        return driver.findElement(locator).isDisplayed();
    }
}
