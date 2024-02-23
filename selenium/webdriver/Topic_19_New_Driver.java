package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.Set;

public class Topic_19_New_Driver {
    WebDriver userdriver;
    WebDriver admindriver;
    String firstName ="Linh", lastName ="Phan", emailAddress = getEmailAddress();
    String companyName = "Payoo PLus", password ="12345678";
    String day ="26", month ="April", year="1985";

    @BeforeClass
    public void beforeClass() {
        userdriver = new FirefoxDriver();
        System.out.println(userdriver.toString());
        userdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        admindriver = new EdgeDriver();
        System.out.println(admindriver.toString());
        admindriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }
    @Test
    public void TC_01_New_Windows(){
        userdriver.get("https://demo.nopcommerce.com/");

        userdriver.findElement(By.cssSelector("a.ico-register")).click();

        userdriver.findElement(By.id("FirstName")).sendKeys(firstName);
        userdriver.findElement(By.id("LastName")).sendKeys(lastName);

        //dayDropdown dropdown
        Select dayDropdown = new Select(userdriver.findElement(By.name("DateOfBirthDay")));
        //chọn ngày
        dayDropdown.selectByVisibleText(day);
        //verify dropdow này là single (ko phải là multiple). nếu là multiple là true, còn single là false
        Assert.assertFalse(dayDropdown.isMultiple());
        //verify s lượng trong dropdown là 32 item
        Assert.assertEquals(dayDropdown.getOptions().size(),32);

        new Select(userdriver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText(day); //dùng 1 lần
        new Select(userdriver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
        new Select(userdriver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);

        userdriver.findElement(By.id("Email")).sendKeys(emailAddress);
        userdriver.findElement(By.id("Company")).sendKeys(companyName);
        userdriver.findElement(By.id("Password")).sendKeys(password);
        userdriver.findElement(By.id("ConfirmPassword")).sendKeys(password);

        userdriver.findElement(By.cssSelector("button#register-button")).click();
        sleepInSeconds(5);

        Assert.assertEquals(userdriver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");

        //admin site
        admindriver.get("https://admin-demo.nopcommerce.com/");

        admindriver.findElement(By.cssSelector("input#Email")).clear();
        admindriver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);

        admindriver.findElement(By.cssSelector("input#Password")).clear();
        admindriver.findElement(By.cssSelector("input#Password")).sendKeys(password);

        admindriver.findElement(By.cssSelector("button.login-button")).click();
        sleepInSeconds(5);

        //login vào driver user
        userdriver.get("https://demo.nopcommerce.com/");
        userdriver.findElement(By.cssSelector("a.ico-login")).click();

        userdriver.findElement(By.cssSelector("input.email")).sendKeys(emailAddress);
        userdriver.findElement(By.cssSelector("input.password")).sendKeys(password);
        userdriver.findElement(By.cssSelector("button.login-button")).click();
        sleepInSeconds(5);

    }
    @AfterClass
    public void afterClass() {
        userdriver.quit();
        admindriver.quit();

    }

    public void sleepInSeconds(long timeInSeconds){
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public String getEmailAddress(){
        return "phanthelinh" + new Random().nextInt(9999) + "@gmail.net";
    }

}
