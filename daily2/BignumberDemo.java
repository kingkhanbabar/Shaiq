package daily2;

import java.math.BigInteger;
import java.util.Scanner;

public class BignumberDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigInteger x = new BigInteger("567");
        System.out.println(x);
        BigInteger y = new BigInteger("567");
        System.out.println(y);
        BigInteger z = x.add(y);
        System.out.println(z);
        BigInteger c = BigInteger.valueOf(34);
        System.out.println(c);
        BigInteger d = BigInteger.TWO;
        System.out.println(d);
        BigInteger e = BigInteger.TEN;
        System.out.println(e);


    }
}
