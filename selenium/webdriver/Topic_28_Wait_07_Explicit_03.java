package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class Topic_28_Wait_07_Explicit_03 {
    WebDriver driver;
    WebDriverWait explicitWait; //khai báo chưa khởi tạo
    String projectPath = System.getProperty("user.dir");
    String image1File = "image1.jpg";
    String image2File = "image2.jpg";
    String image3File = "image3.jpg";
    String img1FilePath = projectPath + File.separator + "UploadFile" + File.separator + image1File;
    String img2FilePath = projectPath + File.separator + "UploadFile" + File.separator + image2File;
    String img3FilePath = projectPath + File.separator + "UploadFile" + File.separator + image3File;
    @BeforeClass //precondition - khởi tạo dữ liệu / data test/ page class / variable
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_AjaxLoading(){
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        By selectedDateBy = By.cssSelector("span#ctl00_ContentPlaceholder1_Label1");
        //WebElement selectedDate = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));
        Assert.assertEquals(driver.findElement(selectedDateBy).getText(), "No Selected Dates to display.");

        driver.findElement(By.xpath("//a[text()='17']")).click();
        //wait cho cái icon loading biến mất  div[id*='RadCalendar1']>div.raDiv
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));

        Assert.assertEquals(driver.findElement(selectedDateBy).getText(), "Wednesday, January 17, 2024");

    }
    @Test
    public void TC_02_UploadFile(){
        driver.get("https://gofile.io/?t=uploadFiles");
        //wait + verify spinner icon biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border"))));

        //wait + click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ajaxLink > button"))).click();

        //wait + verify spinner icon biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

        driver.findElement(By.cssSelector("input[type = 'file']")).sendKeys(img1FilePath +"\n"+ img2FilePath + "\n" + img3FilePath);

        //wait + verify spinner icon biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

        //wait progress bar biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress")))));

        //wait + click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink"))).click();

        //wait + verify button play có tại từng hình được load
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ image1File +"']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ image2File +"']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ image3File +"']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());

        //wait + verify button Download có tại từng hình được load
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ image1File +"']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ image2File +"']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ image3File +"']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());

    }
    @AfterClass
    public void afterClass() {
        driver.quit();

    }

}
