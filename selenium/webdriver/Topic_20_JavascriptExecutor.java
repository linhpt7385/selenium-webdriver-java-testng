package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_20_JavascriptExecutor {
    WebDriver driver;

    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver; //ép kiểu tường mình (tù kiểu này sang kiểu khác

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_(){
        navigateToUrlByJS("http://live.techpanda.org/");
        sleepInSeconds(3);

        String techPandaDomain = (String) executeForBrowser("return document.domain;");
        Assert.assertEquals(techPandaDomain,"live.techpanda.org");

        String techtPandaURL = (String) executeForBrowser("return document.URL;");
        Assert.assertEquals(techtPandaURL,"http://live.techpanda.org/");

        hightlightElement("//a[text() ='Mobile']");
        clickToElementByJS("//a[text() ='Mobile']");

        hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
        clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");

        Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));

        hightlightElement("//a[text()='Customer Service']");
        clickToElementByJS("//a[text()='Customer Service']");

        String techPandaTitle = (String) executeForBrowser("return document.title;");
        Assert.assertEquals(techPandaTitle,"Customer Service");

        scrollToBottomPage();
        hightlightElement("//input[@id='newsletter']");
        sendkeyToElementByJS("//input[@id='newsletter']",getEmailAddress());

        hightlightElement("//span[text()='Subscribe']");
        clickToElementByJS("//span[text()='Subscribe']");

        Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
        Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));

        navigateToUrlByJS("https://www.facebook.com/");
        Assert.assertEquals(executeForBrowser("return document.domain;"),"facebook.com");

    }
    @Test
    public void TC_02_(){
        driver.get("https://sieuthimaymocthietbi.com/account/register");

        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(getElementValidationMessage("//input[@id='lastName']"),"Please fill out this field.");
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Phan");
        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(getElementValidationMessage("//input[@id='firstName']"),"Please fill out this field.");
        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("The Linh");
        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(getElementValidationMessage("//input[@id='email']"),"Please fill out this field.");
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("sdvvs@sjfj@vvv");
        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(getElementValidationMessage("//input[@id='email']"),"Please enter an email address.");

    }
    @Test
    public void TC_03_(){
        navigateToUrlByJS("http://live.techpanda.org/");
        //clickToElementByJS("//span[text()='Account']");
        clickToElementByJS("//div[@id ='header-account']//a[@title ='My Account']");
        clickToElementByJS("//a[@title='Create an Account']");

        String firstName = "automation", lastName = "FC", emailAddress = getEmailAddress(), password = "123456789";
        String fullName = firstName + " " + lastName;

        sendkeyToElementByJS("//input[@id='firstname']",firstName);
        sendkeyToElementByJS("//input[@id='lastname']",lastName);
        sendkeyToElementByJS("//input[@id='email_address']",emailAddress);
        sendkeyToElementByJS("//input[@id='password']",password);
        sendkeyToElementByJS("//input[@id='confirmation']",password);
        clickToElementByJS("//button[@title='Register']");
        sleepInSeconds(3);

        Assert.assertTrue(getInnerText().contains("Thank you for registering with Main Website Store."));
        sleepInSeconds(3);

        //logout
        clickToElementByJS("//a[@title='Log Out']");
        sleepInSeconds(3);
        //kiểm tra logout thành công
        Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(text(), 'You are now logged out')]")).isDisplayed());
        //kiểm tra về home page
        Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(), 'This is demo site for')]")).isDisplayed());
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
    public String getEmailAddress(){
        return "phanthelinh" + new Random().nextInt(9999) + "@gmail.net";
    }
    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSeconds(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSeconds(3);
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }
}
