package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Scope {
    //Các biến được khai báo ở ngoài hàm -->pham vi là class
    //Biến global (toàn cục)
    //Có thể dùng cho tất cả các hàm trong class đó
    WebDriver driver;
    String homePageURL; // Khai báo (declare)
    String fullName = "automation FC"; //khai báo + khởi tạo

    @BeforeClass
    public void beforeClass (){
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01(){
        //Các biến được khai báo trong 1 hàm/ block code --> pham vi cục bộ (local)
        //Dung các hàm nó được khai báo/ block code được sinh ra
        String homePageURL = "https://www.facebook.com/";
        //trong 1 hàm nếu có 2 biến cùng tên (Global/local ) thì n ưu tiên lấy biến local dùng
        // 1 biến local nếu như gọi tới dùng mà chưa được khởi tạo thì sẽ bị lỗi
        //biến local chưa khởi taạo mà gọi ra dùng thì nó sẽ lỗi ngay (compile code)

        this.driver.get(homePageURL);
        //trong 1 hàm nếu có 2 biến cùng tên (Global/local ) mà mình muốn ấy biên Glabal ra để dùng
        //từ khóa this
        //biến Global chưa khởi tạo mà gọi ra dùng thì không báo lỗi ở level (compile code)
        // level runtime sẽ lỗi
        this.driver.get(this.homePageURL);

    }
    @Test
    public void TC_02(){

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
