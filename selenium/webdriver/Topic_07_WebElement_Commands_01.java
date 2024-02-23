package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Topic_07_WebElement_Commands_01 {

    WebDriver driver;
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

    }

    @Test
    public void TC_01_Element(){
        //Xóa dữ lieuj trong 12 file cho phép nhập (editable)
        //Textbox / textArea / Dropdown
        // thường được s dụng trước hàm sendKeys
        driver.findElement(By.id("")).clear();

        //Dùng để nhập liệu cho các file bên trên
        driver.findElement(By.id("")).sendKeys("");

        //Dùng để clip lên element
        driver.findElement(By.id("")).click();

        driver.findElement(By.xpath("//rttrtyy")).click();

        //timf từ node cha vào node con
        driver.findElement(By.id("")).findElement(By.id("lastName"));
        driver.findElement(By.id("")).findElement(By.cssSelector("lastName"));

        //trả về 1 element khớp với điều kiện
        WebElement fullTextbox = driver.findElement(By.xpath(""));

        //trả về nhiều element khớp với đk
        List<WebElement> texbox = driver.findElements(By.cssSelector(""));

        //dung để verfiy 1  checkbox, dropdown, radio default đã được chọn hay chưa
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isSelected());
        Assert.assertFalse(driver.findElement(By.cssSelector("")).isSelected());

        //Dropdow (default/custom)
        Select select = new Select(driver.findElement(By.id("")));

        //Dùng để vrify 1 elament có hiển thị hay không
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("")).isDisplayed());

        //Dùng để vrify 1 elament có được thao tác lên hay không (ko phải read onliy)
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isEnabled());
        Assert.assertFalse(driver.findElement(By.cssSelector("")).isEnabled());

        //HTML Element
        driver.findElement(By.id("")).getAttribute("title");
        driver.findElement(By.id("")).getAttribute("value");
        driver.findElement(By.id("")).getAttribute("id");
        driver.findElement(By.id("")).getAttribute("type");

        //Tab Accesibility/ Properties trong element
        driver.findElement(By.id("")).getAccessibleName();
        driver.findElement(By.id("")).getDomAttribute("checked");
        driver.findElement(By.id("")).getDomProperty("baseURL");
        driver.findElement(By.id("")).getDomProperty("outerHTML");

        //Tab style trong element (GUI)
        //font size, color, background...
        driver.findElement(By.id("")).getCssValue("background color");
        driver.findElement(By.id("")).getCssValue("font-size");

        //vi trí của element so với độ phaan giải màn hih
        Point nameTextBoxLoacation = driver.findElement(By.id("")).getLocation();
        nameTextBoxLoacation.getX();
        nameTextBoxLoacation.getY();

        //Chiều cao + rộng
        Dimension addressSize = driver.findElement(By.id("")).getSize();

        //Location +size
        Rectangle nameTexxbox = driver.findElement(By.id("")).getRect();
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
