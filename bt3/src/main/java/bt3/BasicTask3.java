package bt3;
import java.util.Arrays;
public class BasicTask3 {
    //numbersの要素が素数か否かを判定し，素数であればprimesの要素とする
    //numbersの要素を変えても対応できるプログラムにしてください
    public static int[] numbers = { 7, 8, 9, 10, 11 };
    public static int[] primes;
    
    public static void main(String[] args) {
        preprocess(args); //ここは無視でOK
        //ここから
        primes = new int[numbers.length]; //ダミーなので消してよい
        //ここまで
        System.out.println(Arrays.toString(primes));
    }

    // このメソッドは無視でOK
    private static void preprocess(String[] args) {
        if(args.length > 0) {
            numbers = Arrays.stream(args[0].split(",")).mapToInt(Integer::parseInt).toArray();
        }
    }
}
