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
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_12_Checkbox_Radio {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Default_Telerik_Checkbox(){
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        By rearSideCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");
        By duaZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");

        //Case1: Nếu như app này mở ra mà checkbox đã được chọn thì sao
        checkElement(rearSideCheckbox);
        //Case 2: Nếu như app này mở ra mà checkbox chưa được chọn thì sao
        checkElement(duaZoneCheckbox);

        //verify checkbox đã được chọn thành công
        Assert.assertTrue(driver.findElement(rearSideCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(duaZoneCheckbox).isSelected());

        unCheckElement(rearSideCheckbox);
        unCheckElement(duaZoneCheckbox);

        Assert.assertFalse(driver.findElement(rearSideCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(duaZoneCheckbox).isSelected());
    }
    @Test
    public void TC_02_Default_Telerik_Radio(){
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        By twoPetrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
        By twoDieselRadio = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input");

        checkElement(twoPetrolRadio);

        Assert.assertTrue(driver.findElement(twoPetrolRadio).isSelected());
        Assert.assertFalse(driver.findElement(twoDieselRadio).isSelected());

        checkElement(twoDieselRadio);

        Assert.assertFalse(driver.findElement(twoPetrolRadio).isSelected());
        Assert.assertTrue(driver.findElement(twoDieselRadio).isSelected());
    }

    @Test
    public void TC_03_Select_All_Checkbox(){
        driver.get("https://automationfc.github.io/multiple-fields/");

        List<WebElement> allCheckbox = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));

        //chọn hết tất cả các checkbox ở man hình
        for (WebElement checkbox: allCheckbox){
            if (!checkbox.isSelected()){
                checkbox.click();
            }
        }
        //verify hết tất cả các checkbox
        for (WebElement checkbox: allCheckbox){
            Assert.assertTrue(checkbox.isSelected());
        }

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        allCheckbox = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));

        //chỉ chọn 1 checkbox Heart Attack  trong tất cả các checkbox
        for (WebElement checkbox: allCheckbox){
            if (checkbox.getAttribute("value").equals("Heart Attack") && !checkbox.isSelected()){
                checkbox.click();
                sleepInSeconds(1);
            }
        }
        //verify hết tất cả các checkbox
        for (WebElement checkbox: allCheckbox){
            if (checkbox.getAttribute("value").equals("Heart Attack")){
                Assert.assertTrue(checkbox.isSelected());
            }else{
                Assert.assertFalse(checkbox.isSelected());
            }
        }
    }
    @Test
    public void TC_04_Custom_Radio(){
        driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");

        //Dùng thẻ div để click --> pass
        //DÙng thẻ input để verify -->pass
//        driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/div[@class='mat-radio-outer-circle']")).click();
//        sleepInSeconds(3);
//        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());

        //dùng js để click
        By registerRadio = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");

        ((JavascriptExecutor)driver).executeScript("arguments[0].click()",driver.findElement(registerRadio));
        Assert.assertTrue(driver.findElement(registerRadio).isSelected());

    }

    @Test
    public void TC_05_Custom_Google_Docs(){
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        By canThoRadio = By.xpath("//div[@aria-label ='Cần Thơ']");

        //verify radio is not selected
        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"),"false");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label ='Cần Thơ' and @aria-checked ='false']")).isDisplayed()); //cách 2

        driver.findElement(canThoRadio).click();
        sleepInSeconds(3);

        //verify radio is selected
        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"),"true");
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
    public void checkElement(By xpath){
        if(!driver.findElement(xpath).isSelected()){
            driver.findElement(xpath).click();
            sleepInSeconds(3);
        }
    }
    public void unCheckElement(By xpath){
        if(driver.findElement(xpath).isSelected()){
            driver.findElement(xpath).click();
            sleepInSeconds(3);
        }
    }
}
