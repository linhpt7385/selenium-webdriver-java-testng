package javaTester;


import java.util.ArrayList;
import java.util.List;

public class Topic_15_Generic {
    public static void main(String[] args) {
        //list chỉ chứa kiểu dữ liệu string
        // E T U K L the type of element in the List
        List<String> students = new ArrayList<String>();
        students.add("Jory");
        students.add("Thing");
        students.add("Nery");

        List addresses = new ArrayList<>();
        addresses.add("123 Trinh Ding Trong");
        addresses.add(13); //integer
        addresses.add(true); //boolean
        addresses.add(13.45); //float


    }

}
