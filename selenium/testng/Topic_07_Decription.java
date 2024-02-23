package testng;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_07_Decription {
    @BeforeTest
    public void innit(){
        System.out.println("Bắt đầu TC");
    }
    @Test(description = "adfsfdf- dsffđffd")
    public void Priority_01_SearchWithDay(){

    }
    @Test
    public void Priority_02_SearchWithBilling(){

    }
    @Test
    public void Priority_03_SearchWithProduct(){

    }
    @BeforeTest
    public void after(){
        System.out.println("Kết thúc TC");
    }
}
