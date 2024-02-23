package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Topic_23_Wait_02_Find_Element {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_FindElement(){
        //Case 1 - Element được tìm thấy chỉ có 1
        //Sẽ ko cần chờ hết timeout
        //Tìm thấy sẽ trả về 1 element
        //Qua step tiếp theo
//        System.out.println("Start step: "+getDateTimeNow());
//        driver.findElement(By.cssSelector("input#email"));
//        System.out.println("End step: "+getDateTimeNow());

        //Case 2 - Element được tìm thấy nhưng nhiều hơn 1
        //Sẽ ko cần chờ hết timeout
        //Lấy element đầu tiên dù có cả n node
        //Qua step tiếp theo
//        System.out.println("Start step: "+getDateTimeNow());
//        driver.findElement(By.cssSelector("input[type ='text'],[type='password']")).sendKeys("linhphan@gmail.com");
//        System.out.println("End step: "+getDateTimeNow());

        //Case 3 - Element không tìm thấy
        //chờ hết timeout là 10s
        //trong thời gian 10s này cứ mỗi nữa s sẽ tìm lại 1 lần
        //Nếu tìm lại mà thấy cũng trả về element rồi qua bước tiếp theo
        //Nếu tìm lại mà ko thấy thì đánh fail testcase và throw exception: NoSuchElementException
        //Các step còn lại không chạy nữa
        System.out.println("Start step: "+getDateTimeNow());
        driver.findElement(By.cssSelector("input[name='reg_email__']"));
        System.out.println("End step: "+getDateTimeNow());
    }
    @Test
    public void TC_02_FindElements(){
        List<WebElement> elementlist;
        //Case 1 - Element được tìm thấy chỉ có 1
        //ko cần chờ hết timeout hết 10s
        //trả về 1 list element chứa đúng 1 element
//        System.out.println("Start step: "+getDateTimeNow());
//        elementlist = driver.findElements(By.cssSelector("input#email"));
//        System.out.println("List have: " +elementlist.size());
//        System.out.println("End step: "+getDateTimeNow());

        //Case 2 - Element được tìm thấy nhưng nhiều hơn 1
        //ko cần chờ hết timeout hết 10s
        //trả về 1 list element chứa nhiều element
//        System.out.println("Start step: "+getDateTimeNow());
//        elementlist = driver.findElements(By.cssSelector("input[type ='text'],[type='password']"));
//        System.out.println("List have: " +elementlist.size());
//        System.out.println("End step: "+getDateTimeNow());


        //Case 3 - Element không tìm thấy
        //chờ hết timeout là 10s
        //trong thời gian 10s này cứ mỗi nữa s sẽ tìm lại 1 lần
        //Nếu tìm lại mà thấy cũng trả về list chứa các element đó
        //Nếu hết thời gian tìm lại mà ko thấy thì trả về list rỗng và ko đánh fail testcase
        //Qua step tiếp theo
        System.out.println("Start step: "+getDateTimeNow());
        elementlist = driver.findElements(By.cssSelector("input[name='reg_email__']"));
        System.out.println("List have: " +elementlist.size());
        System.out.println("End step: "+getDateTimeNow());
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
    public String getDateTimeNow(){
        Date date = new Date();
        return date.toString();
    }

}
