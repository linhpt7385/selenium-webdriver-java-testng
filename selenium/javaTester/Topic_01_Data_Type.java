package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Topic_01_Data_Type {
    //Kiểu dữ liệu trong java - có 2 nhóm

    //1. kiểu dl nguyên thủy - Primitive type - gồm 8 loại
    //Số nguyên: byte - short - int - long
    byte hocsinh = 40;
    short sNumber = 30000;
    int iNumber = 1546656;
    long lNumber = 24344445;

    //Số thực: float - double
    float diem = 7.67f;
    double dNumber = 9.34d;

    //Ký tự: char - đại diện duy nhất 1 ký tự
    char c = 'M';
    char n ='K';

    //Logic: boolean
    boolean status = true;

    //2. kiểu dl tham chiếu - Referent type
    // Class

    FirefoxDriver firefoxDriver = new FirefoxDriver();
    Select select = new Select(firefoxDriver.findElement(By.className("")));
    Topic_01_Data_Type topic01 = new Topic_01_Data_Type();
    // Interface
    WebDriver webDriver;
    JavascriptExecutor jsExecutor;
    //Object
    Object names = "Phan The Linh";
    //Array
    int[] studentAge = {15, 28, 8};
    String[] studentName = {"Automation", "Testing" };
    //Colection: list/set/queue
    List<String> studentAddress = new ArrayList<String>();
    List<String> studentCity = new LinkedList<>();
    List<String> studentPhone = new Vector<>();

    //String: chuỗi ký tự
    String name = "Minh";
}
