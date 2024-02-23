package javaTester;

import java.util.Random;

public class Topic_06_Random {
    //java buitin (lấy ra dùng)
    //Java labraries do 1 cá nhân/ tổ chức tự viết)

    public static void main (String[] args){
        Random rand = new Random();

        System.out.println(rand.nextInt());
        System.out.println(rand.nextDouble());
        System.out.println(rand.nextBoolean());
        System.out.println(rand.nextFloat());

        System.out.println(rand.nextInt(999));
        System.out.println(rand.nextInt(999));

    }
}
