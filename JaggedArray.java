import java.util.Scanner;

public class JaggedArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of sub");
        int n = sc.nextInt();
        int jArr[][] = new int[n][];
        for (int i = 0; i < n; i++) {
            System.out.println("enter size of sub array"+(i+1));
            int size = sc.nextInt();
            jArr[i] = new int[size];
            
        }
        for (int i = 0; i < n; i++) {
            System.out.println("Enter ele for sun "+(i+1));
            for (int j = 0; j < jArr[i].length; j++) {
                jArr [i][j] = sc.nextInt();

            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < jArr[i].length; j++) {
                System.out.print(jArr[i][j]+" ");

            }
            System.out.println();

        }
        sc.close();
    }
}
