package daily2;

import java.util.Scanner;

public class JagguArru {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int booked =0;
        int empty =0;
        System.out.println("Enter number of rows ");
        int n = sc.nextInt();
        int jarr[][] = new int[n][];
        for (int i = 0; i < n; i++) {
            System.out.println("Enter the size of subarray"+(i+1)+" ");
            int size = sc.nextInt();
            jarr[i] = new int[size];

        }
        for (int i = 0; i < n; i++) {
            System.out.println("enter thwe ele for sub array"+(i+1));
            for (int j = 0; j < jarr[i].length; j++) {
                jarr[i][j] = sc.nextInt();
             if(jarr[i][j]==0){
                 booked++;
             }
             else{
                 empty++;
             }
            }

        }
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < jarr[i].length; j++) {
                System.out.print(jarr[i][j]+" ");

            }

            System.out.println();
        }
        System.out.println(booked);
        System.out.println(empty);

    }
}
