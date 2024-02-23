package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_18_Windows_Tab {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Basic_Form(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        //lấy ra ID của tab hiện tại
        String formBasicID = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSeconds(3);

        //switch để qua trang Goole
        switchToWindowsByTitle("Google");

        driver.findElement(By.cssSelector("textarea[name ='q']")).sendKeys("Selenium");
        sleepInSeconds(3);
        //switch để quay lại trang basic form
        switchToWindowsByTitle("Selenium WebDriver");

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSeconds(3);

        //switch để qua trang Facebook
        switchToWindowsByTitle("Facebook – log in or sign up");
        driver.findElement(By.cssSelector("input#email")).sendKeys("phanthelinh@gmail.com");
        sleepInSeconds(3);

        //switch để quay lại trang basic form
        switchToWindowsByTitle("Selenium WebDriver");
    }
    @Test
    public void TC_02_KynaEnglish(){
        driver.get("https://skills.kynaenglish.vn/");
        String parentID = driver.getWindowHandle();

        driver.findElement(By.cssSelector("div.hotline img[alt='facebook']")).click();
        sleepInSeconds(3);
        switchToWindowsByTitle("Kyna.vn | Ho Chi Minh City | Facebook");
        driver.findElement(By.cssSelector("form#login_popup_cta_form input[name='email']")).sendKeys("phanthelinh@gmail.com");
        sleepInSeconds(3);

        switchToWindowsByTitle("Kyna.vn - Học online cùng chuyên gia");

        driver.findElement(By.cssSelector("div.hotline img[alt='youtube']")).click();
        sleepInSeconds(3);

        switchToWindowsByTitle("Kyna.vn - YouTube");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#inner-header-container yt-formatted-string#text")).getText(),"Kyna.vn");

        closeAllWindowsWithoutParent(parentID);
        sleepInSeconds(3);
    }
    @Test
    public void TC_03_TechPanda(){
        driver.get("http://live.techpanda.org/");
        String parentID = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        sleepInSeconds(3);

        driver.findElement(By.xpath("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product IPhone has been added to comparison list.");

        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        sleepInSeconds(3);

        switchToWindowsByTitle("Products Comparison List - Magento Commerce");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title h1")).getText(),"COMPARE PRODUCTS");

        switchToWindowsByTitle("Mobile");
        driver.findElement(By.cssSelector("input#search")).sendKeys("Samsunng Galaxy");

        closeAllWindowsWithoutParent(parentID);
        sleepInSeconds(3);
    }
    @Test
    public void TC_04_Selenium_Version_4x(){
        //driver đang ở trang home
        driver.get("https://skills.kynaenglish.vn/");
        System.out.println("Driver ID =" +driver.toString());
        System.out.println("Windows ID =" +driver.getWindowHandle());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        driver.switchTo().newWindow(WindowType.WINDOW).get("https://skills.kynaenglish.vn/phan-tich-ky-thuat-trong-chung-khoan");
        System.out.println("Driver ID =" +driver.toString());
        System.out.println("Windows ID =" +driver.getWindowHandle());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        driver.findElement(By.cssSelector("a.cod-btn")).click();
        sleepInSeconds(3);

        driver.switchTo().newWindow(WindowType.TAB).get("https://www.facebook.com/kyna.vn");
        System.out.println("Driver ID =" +driver.toString());
        System.out.println("Windows ID =" +driver.getWindowHandle());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        driver.findElement(By.cssSelector("form#login_popup_cta_form input[name ='email']")).sendKeys("phanthelinh@gmail.com");
        sleepInSeconds(3);

        switchToWindowsByTitle("Kyna.vn - Học online cùng chuyên gia");
    }
    @AfterClass
    public void afterClass() {
        driver.quit();

    }
    public void switchToWindowsByID(String expectedID){
        //Lấy hết tất cả các ID cuả windows và tab
        Set<String> allIDs = driver.getWindowHandles();

        //dùng vòng lập duyệt qua các Set ID trên
        for (String id:allIDs){
            if(!id.equals(expectedID)){ //nếu như 1 ID nào mà khác với allIDs thì switch vào
                driver.switchTo().window(id);
                break; //thoát ra khỏi vòng lặp không cần kiểm tra các giá trị còn lại(nếu có)
            }
        }
    }
    public void switchToWindowsByTitle(String expectedTitle) {
        //Lấy hết tất cả các ID của windows và tab
        Set<String> allIDs = driver.getWindowHandles();
        //dùng vòng lập duyệt qua set title trên
        for (String id : allIDs) {
            //cho switch vaào từng ID trước
            driver.switchTo().window(id);
            sleepInSeconds(2);
            //lấy ra title của tab và window hiện taị
            String actualTitle = driver.getTitle();
            if(actualTitle.equals(expectedTitle)){
                break;
            }
        }
    }
    public void closeAllWindowsWithoutParent(String parentID){
        Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs) {
            if(!id.equals(parentID)){
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }
    public void sleepInSeconds(long timeInSeconds){
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
