package webdriver;

import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/register");

    }
    // TestNG: order TC theo Alphabet (0-9,A-Z)

    /*<input type="text" data-val="true" data-val-required="First name is required." id="FirstName" name="FirstName">
    */
    @Test
    public void TC_01_ID(){
        //Tìm element có Id là Firstname
        driver.findElement(By.id("FirstName")).sendKeys("PhanLinh");

    }
    @Test
    public void TC_02_Class(){
        driver.findElement(By.className("header-logo"));

    }
    @Test
    public void TC_03_Name(){
        driver.findElement(By.name("DateOfBirthDay"));
    }
    @Test
    public void TC_04_Tagname(){
        driver.findElements(By.tagName("input"));
    }
    @Test
    public void TC_05_LinkText(){
        //chính xác cao
        driver.findElement(By.linkText("Shipping & returns"));
    }

    @Test
    public void TC_06_Partial_LinkText(){
        //độ chính xác ko cao - tương đối
        driver.findElement(By.partialLinkText("for vendor"));
    }
    @Test
    public void TC_07_Css(){
        //Css với ID
        driver.findElement(By.cssSelector("input[id='FirstName']"));
        driver.findElement(By.cssSelector("input#FirstName"));
        driver.findElement(By.cssSelector("#FirstName"));

        //Css với class
        driver.findElement(By.cssSelector("div[class='page-title']"));
        driver.findElement(By.cssSelector("div.page-title"));
        driver.findElement(By.cssSelector(".page-title"));

        //Css với name
        driver.findElement(By.cssSelector("input[name ='FirstName'"));

        //Css với Tagname
        driver.findElement(By.cssSelector("input"));

        //CSS với link
        driver.findElement(By.cssSelector("a[href='/customer/addresses']"));

        //CSS với partial link
        driver.findElement(By.cssSelector("a[href*='addresses']"));
        //driver.findElement(By.cssSelector("a[href^='addresses']"));
        //driver.findElement(By.cssSelector("a[href$='addresses']"));

    }
    @Test
    public void TC_08_xPath(){
        //XPath với ID
        driver.findElement(By.xpath("//input[@id='FirstName']"));

        //XPath với class
        driver.findElement(By.xpath("//div[@class='page-title']"));

        //XPath với name
        driver.findElement(By.xpath("//input[@name ='FirstName']"));

        //XPath với Tagname
        driver.findElement(By.xpath("//input"));

        //XPath với link
        driver.findElement(By.xpath("//a[@href='/customer/addresses']"));
        driver.findElement(By.xpath("//a[text()='Addresses']"));

        //XPath với partial link
        driver.findElement(By.xpath("//a[contains(@href,'addresses')]"));
        driver.findElement(By.xpath("//a[contains(text(),'Addresses')]"));
    }
    @AfterClass
    public void afterClass() {
        //driver.quit();

    }
}
