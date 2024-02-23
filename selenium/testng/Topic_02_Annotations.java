package testng;

import org.testng.annotations.*;

public class Topic_02_Annotations {
    @BeforeClass
    public void beforeClass(){
        System.out.println("Before Class");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("After Class");
    }

    @BeforeGroups
    public void beforeGroup(){
        System.out.println("Before Group");
    }

    @AfterGroups
    public void afterGroup(){
        System.out.println("After Group");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Before Group");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("Before Group");
    }
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("before Suite");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("after Suite");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("before Test");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("after Test");
    }
    @Test
    public void test01(){
        System.out.println("Test 01");
    }
    @Test
    public void test02(){
        System.out.println("Test 02");
    }
    @Test
    public void test03(){
        System.out.println("Test 03");
    }
}
