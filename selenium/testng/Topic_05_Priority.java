package testng;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_05_Priority {
    @BeforeTest(alwaysRun = true)
    public void innit(){
        System.out.println("Bắt đầu TC");
    }
    @Test
    public void Priority_01_SearchWithDay(){

    }
    @Test
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
