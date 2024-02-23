package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_10_Custom_Dropdown {
    WebDriver driver;
    //tường mình: trạng thái cụ thể của 1 element
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));

        //ngầm định: ko rõ ràng cho 1 trạng thái của element, mà nó ngầm định cho việc tìm element
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_JQuery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        selectItemInDropdown("span#speed-button","li.ui-menu-item div","Faster");
        sleepInSeconds(3);

        selectItemInDropdown("span#files-button","ul#files-menu div","Some unknown file");
        sleepInSeconds(3);

        selectItemInDropdown("span#number-button","ul#number-menu div","10");
        sleepInSeconds(3);

        selectItemInDropdown("span#salutation-button","ul#salutation-menu div","Prof.");
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button >span.ui-selectmenu-text")).getText(),"Faster");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button >span.ui-selectmenu-text")).getText(),"Some unknown file");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button >span.ui-selectmenu-text")).getText(),"10");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button >span.ui-selectmenu-text")).getText(),"Prof.");

    }

    @Test
    public void TC_02_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectItemInDropdown("i.dropdown.icon", "div.item > span.text", "Stevie Feliciano");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Stevie Feliciano");
        sleepInSeconds(3);

        selectItemInDropdown("i.dropdown.icon", "div.item > span.text", "Justen Kitsune");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Justen Kitsune");
        sleepInSeconds(3);

        selectItemInDropdown("i.dropdown.icon", "div.item > span.text", "Christian");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Christian");
        sleepInSeconds(3);

    }

    @Test
    public void TC_03_VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Second Option");
        sleepInSeconds(3);

        selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Third Option");
        sleepInSeconds(3);

    }

    @Test
    public void TC_04_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        selectItemInEditableDropdown("input.search", "div.item span", "Algeria");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Algeria");
        sleepInSeconds(2);

        selectItemInEditableDropdown("input.search", "div.item span", "Bangladesh");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Bangladesh");
        sleepInSeconds(2);


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
    public void selectItemInDropdown (String parentCSS, String childItemCSS, String itemTextExpected){
        driver.findElement(By.cssSelector(parentCSS)).click();
        sleepInSeconds(1);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCSS)));
        for (WebElement item: allItems){
            if(item.getText().equals(itemTextExpected)){
                item.click();
                break;
            }
        }
    }

    public void selectItemInEditableDropdown (String parentCSS, String childItemCSS, String itemTextExpected){
        driver.findElement(By.cssSelector(parentCSS)).clear();
        driver.findElement(By.cssSelector(parentCSS)).sendKeys(itemTextExpected); //input.search
        sleepInSeconds(1);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCSS)));
        for (WebElement item: allItems){
            if(item.getText().equals(itemTextExpected)){
                item.click();
                break;
            }
        }
    }
}