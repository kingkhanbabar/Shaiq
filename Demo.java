import java.util.Scanner;

class Triplet<X,Y,Z>{
    private  X first;
    private Y second;
    private Z third;
    public Triplet(X first,Y second,Z third){
        this.first = first;
        this.second = second;
        this.third= third;
    }
    public void display(){
        System.out.println("triplets are "+first+","+second+","+third);
    }

}
public class Demo {
    public static void main(String[] args) {
        Triplet<Integer,String,Double>t = new Triplet<>(23,"Shan",89.56);
        t.display();
        Triplet<Integer,Integer,Double>t1 = new Triplet<>(23,0,89.56);
        t1.display();
        Scanner sc = new Scanner(System.in);
        Integer s = sc.nextInt();
        System.out.println(s);
        sc.nextLine();
        String shan = sc.nextLine();

    }
}
