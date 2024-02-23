package javaTester;

public class Topic_09_Array {
    static String[] studentName = {"Nguyễn Thành An", "Nguyễn Cẩm Vân"};
    public static void main(String[] args) {
        String[] studentAddress = new String[5];
        studentAddress[0] = "Nguyễn Ngọc Tư";
        studentAddress[1] = "Nguyễn Ngọc Tịnh";
        studentAddress[2] = "Nguyễn Ngọc Nhân";
        studentAddress[3] = "Nguyễn Ngọc Ngạn";
        studentAddress[4] = "Nguyễn Ngọc Ngân";

        for (int i = 0; i < studentAddress.length ; i++) {
            System.out.println(studentAddress[i]);
        }

        System.out.println(studentName[0]);
        System.out.println(studentName[1]);

    }
}
