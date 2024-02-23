package webdriver;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Topic_13_Alert {
    WebDriver driver;
    WebDriverWait explicitWait;

    By resultText = By.cssSelector("p#result");
    @BeforeClass
    public void beforeClass() {

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        driver = new ChromeDriver(options);
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Accept_Alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text() = 'Click for JS Alert']")).click();
        //Nếu thời gian chờ mà xuất hiện thì switch vào
        //Nếu thời gian chờ chưa xuất hiện thì mới fail
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(),"I am a JS Alert");

        //khi nhấn eccept hoặc cancel thì alert sẽ mất luôn
        alert.accept();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked an alert successfully");

    }
    @Test
    public void TC_02_Confirm_Alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(),"I am a JS Confirm");

        alert.dismiss();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Cancel");

    }

    @Test
    public void TC_03_Prompt_Alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        sleepInSeconds(3);
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(),"I am a JS prompt");

        String text = "Phan Thế Linh";
        alert.sendKeys(text);
        sleepInSeconds(3);

        alert.accept();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(resultText).getText(), "You entered: " + text);
    }
    @Test
    public void TC_04_Authentication_Pass_To_URL(){
        String username = "admin";
        String password = "admin";
        //cách 1: truyền thẳng user/pas vào url
        //driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
        //Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());

        //cachs 2 từ page A thao tác lên 1 element sẽ qua page B
        driver.get("http://the-internet.herokuapp.com/");

        String authenLinkURL = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        driver.get(getAuthenAlertByUrl(authenLinkURL,username, password));

        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }

    @Test
    public void TC_05_Authentication_Selenium_4x(){
//        // Get DevTool object
//        DevTools devTools = ((HasDevTools) driver).getDevTools();
//
//        // Start new session
//        devTools.createSession();
//
//        // Enable the Network domain of devtools
//        //devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
//
//        // Encode username/ password
//        Map<String, Object> headers = new HashMap<String, Object>();
//        String basicAuthen = "Basic " + new String(new Base64().encode(String.format("%s:%s", "admin", "admin").getBytes()));
//        headers.put("Authorization", basicAuthen);
//
//        // Set to Header
//        //devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));
//
//        driver.get("https://the-internet.herokuapp.com/basic_auth");

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
    public String getAuthenAlertByUrl(String url, String username, String password){
        String[] authenArray = url.split("//");
        return authenArray[0] + "//" + username + ":" + password + "@" + authenArray[1];

    }

}
