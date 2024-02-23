package testng;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_06_Skip {
    @BeforeTest(alwaysRun = true)
    public void innit(){
        System.out.println("Bắt đầu TC");
    }
    @Test
    public void Priority_01_SearchWithDay(){

    }
    @Test(enabled = false) //hoặc xóa @Test đi nó thành 1 cái hàm thì nó sẽ ko chạy case này
    public void Priority_02_SearchWithBilling(){

    }
    @Test
    public void Priority_03_SearchWithProduct(){

    }
    @BeforeTest(alwaysRun = true)
    public void after(){
        System.out.println("Kết thúc TC");
    }
}
