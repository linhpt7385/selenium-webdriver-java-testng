package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_11_Timeout {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }
    @Test(timeOut = 10000)
    public void Register() {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        //sleepInSeconds(2);

        //Register
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        //sleepInSeconds(2);

        String firstName = "automation", lastName = "FC", emailAddress = getEmailAddress(), password = "123456789";
        String fullName = firstName + " " + lastName;

        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);
        driver.findElement(By.cssSelector("button[title='Register']")).click();
        //sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(),"Hello, " + fullName +"!");

        String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        //logout
        driver.findElement(By.cssSelector("a.skip-account")).click();
        //sleepInSeconds(2);
        driver.findElement(By.xpath("//a[@title ='Log Out']")).click();

        //login
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        //sleepInSeconds(2);

        driver.findElement(By.cssSelector("input#email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#pass")).sendKeys(password);
        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(),"Hello, " + fullName +"!");

        contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        //Verify Account
        driver.findElement(By.xpath("//a[text() ='Account Information']")).click();
        //sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getAttribute("value"),lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getAttribute("value"),emailAddress);

        //logout
        driver.findElement(By.cssSelector("a.skip-account")).click();
        //sleepInSeconds(2);
        driver.findElement(By.xpath("//a[@title ='Log Out']")).click();

        System.out.println("Email Address/password = "  + emailAddress + " - " + password);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public String getEmailAddress(){

        return "automation" + new Random().nextInt(9999) + "@gmail.net";
    }

}
