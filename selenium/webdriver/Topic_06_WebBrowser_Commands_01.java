package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class Topic_06_WebBrowser_Commands_01 {
    WebDriver driver;
    @BeforeClass
    public void beforeClass(){
        //Muốn dùng được thì phải khởi tạo
        //Nếu ko khởi tạo sẽ gập lỗi NullPointerException
        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver = new EdgeDriver();
        driver = new SafariDriver();

        //Selenium ver 3,2,1
        //driver.manage().timeouts().implicitlyWait(time30, timeUnit.SECONDS);

        //selenium ver 4
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01() throws MalformedURLException {
        // mở ra 1 page bất kỳ
        driver.get("https://payoo.vn/");
        driver.get("https://www.facebook.com/");


        //nếu như có 1 tab/action thì tính năng này tự quit
        //Nhiều hơn 1 thì nó sẽ đóng cái nó đang action
        driver.close();

        //đóng broswer (ko care bao nhiêu tab/window)
        driver.quit();
        //2 hàm nay ảnh hưởng timeout của implicitlyWait
        //findElement / findEleemts


        WebElement emailAddressTextbox =  driver.findElement(By.id("email"));

        //Nó sẽ đi tìm loại by nào trả về 1 ds Element nấu như được tìm thấy (list webElement)
        // ko được tìm thấy - ko bị fail - trả về 1 list rỗng
        List<WebElement> checkboxes = driver.findElements(By.xpath("input[@type='checkbox']"));

        //lấy rea title của page hiện tại
        driver.getTitle();
        //lấy ra ID / tab của cửa sổ hiện tại
        driver.getWindowHandle();
        driver.getWindowHandles();

        //cooke / framework
        driver.manage().getCookies();

        //get ra những log ở dev tool - framework
        driver.manage().logs().get(LogType.DRIVER);

        //Apply cho viêc tìm findElement / findElemrnts
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        //Chờ cho page được load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        //set trước khi dùng thư viện javascriptExcuter
        //Inject 1 đoạn code vao trong browser / element
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(30));

        //selenium 4 mơí có
        driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().getPageLoadTimeout();
        driver.manage().timeouts().getScriptTimeout();

        //chạy full màn hình
        driver.manage().window().fullscreen();
        driver.manage().window().maximize();
        driver.manage().window().minimize();

        //test UI / Responsive

        driver.manage().window().setSize(new Dimension(1366,768));

        //set cho vị trí nào ở độ phân giải màn hiình(run trên mh có kích bao nhiêu)
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().getPosition();

        driver.navigate().back();
        driver.navigate().refresh();
        driver.navigate().forward();

        driver.navigate().to("https://www.facebook.com");
        driver.navigate().to(new URL("https://www.facebook.com"));


    }

    //selenium 4 mới có


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
