package bt4;

public class BasicTask4 {
    public static int[] numbers = { 1, 3, 4, 32 };
    public static void main(String[] args) {
        preprocess(args); //無視してOK
        NumberSlime s1 = new NumberSlime(numbers[0]);
        NumberSlime s2 = new NumberSlime(numbers[1]);
        NumberSlime s3 = new NumberSlime(numbers[2]);
        NumberSlime s4 = new NumberSlime(numbers[3]);
        s1.absorb(s3); // s1がs3を吸収
        s3.average(s2); // s3がs2と平均化
        NumberSlime s5 = s4.split(); // s4が分裂
        s2.average(s5); // s2とs5が平均化
        s4.absorb(s1); // s4がs1を吸収
        NumberSlime s6 = s2.split(); // s2が分裂
        System.out.println("s1 = " + s1.getNumber());
        System.out.println("s2 = " + s2.getNumber());
        System.out.println("s3 = " + s3.getNumber());
        System.out.println("s4 = " + s4.getNumber());
        System.out.println("s5 = " + s5.getNumber());
        System.out.println("s6 = " + s6.getNumber());
    }

    //このメソッドは無視してOK
    public static void preprocess(String[] args) {
        if(args.length == 4) {
            for(int i = 0; i < numbers.length; i++) {
                numbers[i] = Integer.parseInt(args[i]);
            }
        }
    }
}
