package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Topic_09_Parameter {
    WebDriver driver;
    @Parameters({"browser","version"})
    @BeforeClass
    public void beforeClass(String browseName, String browseVersion) {
        driver = getBrowserDriver(browseName);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }
   @Parameters("environment")
    @Test()
    public void TC_01_Login(String environmentName)  {
        driver.get(getEnvironmentURL(environmentName) + "/index.php/customer/account/login/");

        driver.findElement(By.xpath("//*[@id='email']")).sendKeys("selenium_11_01@gmail.com");
        driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("111111");
        driver.findElement(By.xpath("//*[@id='send2']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));

    }
    private WebDriver getBrowserDriver (String browseName){
        WebDriver driver;
        if (browseName.equals("firefox")){
            driver = new FirefoxDriver();
        }else if (browseName.equals("chrome")){
            driver = new ChromeDriver();
        }else if (browseName.equals("edge")){
            driver = new EdgeDriver();
        }else{
            throw new RuntimeException("Browser name in not valid");
        }
        return driver;
    }

    private String getEnvironmentURL (String environmentName){
        String urlValue;
        if (environmentName.equals("dev")){
            urlValue = "http://dev.techpanda.org";
        }else if (environmentName.equals("testing")){
            urlValue = "http://testing.techpanda.org";
        }else if (environmentName.equals("staging")){
            urlValue = "http://staging.techpanda.org";
        } else if (environmentName.equals("live")){
            urlValue = "http://live.techpanda.org";
        }else{
            throw new RuntimeException("environment name in not valid");
        }
        return urlValue;
    }
    @AfterClass
    public void afterClass() {
        driver.quit();

    }
}
