package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_17_Frame_Iframe {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Form_Site(){
       //trang A
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
        sleepInSeconds(5);

        WebElement formIframe = driver.findElement(By.cssSelector("div#formTemplateContainer>iframe"));
        Assert.assertTrue(formIframe.isDisplayed());
        //swich vào iframe,frame trước khi thao tác với các element bên trong
        driver.switchTo().frame(formIframe);
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        sleepInSeconds(3);

        //swich re lại trang A
        driver.switchTo().defaultContent();

        //thao tác phần tử bên ngoài - trang A
        driver.findElement(By.cssSelector("nav.header--desktop-floater a.menu-item-login")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("button#login")).click();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(),"Username and password are both required.");

    }
    @Test
    public void TC_02_KynaEnglish(){
        driver.get("https://skills.kynaenglish.vn/");
        //switch vào trang iframe chưa fanpage
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content>iframe")));
        //verify follow number
        Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(),"169K followers");

        //switch về trang default
        driver.switchTo().defaultContent();

        //switch vaò trang chat
        driver.switchTo().frame("cs_chat_iframe");

        driver.findElement(By.cssSelector("div.button_bar")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("input.input_name")).sendKeys("PhanLinh");
        driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0909555666");
        new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
        driver.findElement(By.cssSelector("textarea.input_textarea")).sendKeys("chọn 1 khóa học");
        sleepInSeconds(3);

        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Java");
        sleepInSeconds(3);
        driver.findElement(By.cssSelector("button.search-button")).click();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.content>h4")).getText(),"Lập trình Java trong 4 tuần");

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
