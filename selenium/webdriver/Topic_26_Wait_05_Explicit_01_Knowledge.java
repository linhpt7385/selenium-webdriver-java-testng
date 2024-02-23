package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.regex.Pattern;

public class Topic_26_Wait_05_Explicit_01_Knowledge {
    WebDriver driver;

    WebDriverWait explicitWait; //khai báo chưa khởi tạo
    @BeforeClass //precondition - khởi tạo dữ liệu / data test/ page class / variable
    public void beforeClass() {
        driver = new FirefoxDriver();

        //Khởi tao 1 explicit wait có tổng thời gian là 10s - polling là 0,5s mặc định
        //500 milisaconds = 0,5 seconds
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));

        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10),Duration.ofSeconds(300));

    }

    @Test
    public void TC_01_(){
        //Chờ cho 1 Alert presence trong HTML/DOM trước khi thao tác lên
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        //chờ cho 1 element không còn ở trong DOM
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));

        //Chờ cho 1 element có trong DOM(ko quan tâm có trong UI)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

        //Chờ cho 1 list element có trong DOM
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));

        explicitWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector(""), By.cssSelector("")));

        //chờ cho 1 - n element hiển thị trên UI
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector("")),
                driver.findElement(By.cssSelector(""))));

        //chờ cho element được phép click: link/button/checkbox/radio....
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));

        //chờ cho page hiện tại có title như mong đợi
        explicitWait.until(ExpectedConditions.titleIs("dfsgff"));
        driver.getTitle();

        //kết hợp nhiều điêu kiện - 2 đk đều đúng
        explicitWait.until(ExpectedConditions.and(
                ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")),
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(""))));

        //kết hợp nhiều điêu kiện - 1 trong 2 đk đúng
        explicitWait.until(ExpectedConditions.or(
                ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")),
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(""))));

        //Chờ cho 1 element có attribute có giá trị mong đợi(tương đối)
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("input#search"),"placeholder", "fdđhddhj"));

        //Chờ cho 1 element có attribute có giá trị mong đợi(tuyệt đối)
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector("input#search"),"placeholder", "fdđhddhj"));

        //Chờ cho 1 element có attribute khác null
        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.cssSelector("")),"45345355"));

        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector("")),"546454545", "33434343"));
        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("")),"546454545", "33434343"));

        //chờ cho 1 element được selected thành công
        //checkbox/radio/dropdown
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));

        // chờ cho 1 element được selected rồi
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""),true));

        // chờ cho 1 element chưa selected
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""),false));

        //chờ cho 1 frame /iframe được visible và switch qua
        //Name or ID
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(""));

        //index
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));

        //By or Element
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.cssSelector(""))));

        //chờ cho 1 element biến mất (ko hiển thị trên UI)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));

        //chờ cho 1 đoạn code JS trả về giá trị
        explicitWait.until(ExpectedConditions.jsReturnsValue("return arguments[0].validationMessage"));
        explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions(""));

        //chờ cho 1 đoạn code JS thực thi không ném ra ngoại lệ nào hết
        //ko ném ra: true // có ngoại lệ false
        explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("document.documentElement"));
        Assert.assertTrue(explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("document.documentElement")));

        //chờ số lượng element bawngf  con số cố định
        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""),6));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(""),6));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(""),6));

        //chờ cho window/tab là bao nhiêu
        explicitWait.until(ExpectedConditions.numberOfWindowsToBe(8));

        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""),"455"));

        Pattern pattern = Pattern.compile("",Pattern.CASE_INSENSITIVE);
        explicitWait.until(ExpectedConditions.textMatches(By.cssSelector(""),pattern));

        //bắt buộc cái text này có trong DOM/HTML
        explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(""),"56rtrtrt"));

        explicitWait.until(ExpectedConditions.urlToBe(""));
        explicitWait.until(ExpectedConditions.urlContains(""));
        explicitWait.until(ExpectedConditions.urlMatches(""));
    }
    @Test
    public void TC_02_(){

    }
    @AfterClass
    public void afterClass() {
        driver.quit();

    }

}
