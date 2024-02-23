package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.http.WebSocket;
import java.time.Duration;
//@Listeners(Listener.ExtendReport.class)
public class Topic_12_Dependencies {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {


    }

    @Test
    public void TC_01_CreateNewUser(){

    }
    @Test(dependsOnMethods = "TC_01_CreateNewUser")
    public void TC_02_ViewAndSearUser(){

    }
    @Test(dependsOnMethods = "TC_01_CreateNewUser")
    public void TC_03_UpdateExistingUser(){
        Assert.assertTrue(false);
    }
    @Test(dependsOnMethods = "TC_03_UpdateExistingUser")
    public void TC_04_MoveExistingOtherRole(){

    }
    @Test(dependsOnMethods = "TC_04_MoveExistingOtherRole")
    public void TC_05_DeleteExistingUser(){

    }
    @AfterClass
    public void afterClass() {
        //driver.quit();

    }
}
