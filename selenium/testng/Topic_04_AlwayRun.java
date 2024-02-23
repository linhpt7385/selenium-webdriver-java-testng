package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_04_AlwayRun {
    WebDriver driver;
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        System.out.println("Run before class");
        Assert.assertTrue(false);
    }

    @Test
    public void TC_01_(){
        System.out.println("Run testcase 01");
    }
    @Test
    public void TC_02_(){
        System.out.println("Run testcase 02");
    }
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();

    }
}
