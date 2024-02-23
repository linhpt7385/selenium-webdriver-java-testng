package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

public class Topic_05_Register {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    String username, password;
    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Register(){
        // Truy cập trang Register: https://demo.guru99.com/
        driver.get("https://demo.guru99.com/");

        // nhập vào email bất kỳ: random
        driver.findElement(By.name("emailid")).sendKeys("linhthe@gmail.com");

        // click vào submit
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        // Get user/pass và lưu vào 1 biến
        username = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
        password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

    }
    @Test
    public void TC_02_Login(){
        // truy cập trang login:https://demo.guru99.com/v4/
        driver.get("https://demo.guru99.com/v4/");

        //Nhập user / password
        driver.findElement(By.name("uid")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);

        // nhấn nút Login
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

    }
    @AfterClass
    public void afterClass() {
        driver.quit();

    }

}
