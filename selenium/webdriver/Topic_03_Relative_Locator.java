package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_03_Relative_Locator {
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
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Relative(){
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2Fregister");

        //Login button
        By loginButtomBy = By.cssSelector("button.login-button");
        WebElement loginButtomElement = driver.findElement(By.cssSelector("button.login-button"));

        RelativeLocator.with(By.tagName("label")).above(loginButtomBy);
        //RelativeLocator.with(By.tagName("lable")).above(loginButtomElement);

        //Remember me chẹckbox
        By rememberMeCheckboxBy = By.id("RememberMe");

        //forgotpassword link
        WebElement forgotPassworkLink = driver.findElement(By.cssSelector("span.forgot-password"));

        //password textbox
        By passworkTextboxBy = By.cssSelector("input#Password");

        // GUI locator/position
        WebElement rememberMeTextElement = driver
                .findElement(RelativeLocator.with(By.tagName("label"))
                .above(loginButtomBy)
                .toRightOf(rememberMeCheckboxBy)
                .toLeftOf(forgotPassworkLink)
                .below(passworkTextboxBy)
                .near(forgotPassworkLink));
        System.out.println(rememberMeTextElement.getText());

        List<WebElement> aslinks = driver.findElements(RelativeLocator.with(By.tagName("a")));
        System.out.println(aslinks.size());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();

    }

}
