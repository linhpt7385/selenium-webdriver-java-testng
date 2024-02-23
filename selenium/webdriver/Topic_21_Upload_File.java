package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_21_Upload_File {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String image1File = "image1.jpg";
    String image2File = "image2.jpg";
    String image3File = "image3.jpg";
    String img1FilePath = projectPath + File.separator + "UploadFile" + File.separator + image1File;
    String img2FilePath = projectPath + File.separator + "UploadFile" + File.separator + image2File;
    String img3FilePath = projectPath + File.separator + "UploadFile" + File.separator + image3File;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Single_File(){
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadBy = By.xpath("//input[@name='files[]']");
        driver.findElement(uploadBy).sendKeys(img1FilePath);
        sleepInSeconds(2);
        driver.findElement(uploadBy).sendKeys(img2FilePath);
        sleepInSeconds(2);
        driver.findElement(uploadBy).sendKeys(img3FilePath);
        sleepInSeconds(2);

        //verify load success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = '" + image1File + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = '" + image2File + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = '" + image3File + "']")).isDisplayed());

        List<WebElement> startButton = driver.findElements(By.cssSelector("td>button.start"));
        for (WebElement button: startButton){
            button.click();
            sleepInSeconds(3);
        }

        //verify upload success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title = '"+ image1File +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title = '"+ image2File +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title = '"+ image3File +"']")).isDisplayed());

    }
    @Test
    public void TC_02_Multiple_File(){
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadBy = By.xpath("//input[@name='files[]']");
        driver.findElement(uploadBy).sendKeys(img1FilePath +"\n"+ img2FilePath + "\n" + img3FilePath);
        sleepInSeconds(2);

        //verify load success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = '" + image1File + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = '" + image2File + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = '" + image3File + "']")).isDisplayed());

        List<WebElement> startButton = driver.findElements(By.cssSelector("td>button.start"));
        for (WebElement button: startButton){
            button.click();
            sleepInSeconds(3);
        }

        //verify upload success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title = '"+ image1File +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title = '"+ image2File +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title = '"+ image3File +"']")).isDisplayed());

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

}
