package bt3;
import java.util.Arrays;
import java.util.stream.IntStream;

public class BasicTask3 {
    //numbersの要素が素数か否かを判定し，素数であればprimesの要素とする
    //numbersの要素を変えても対応できるプログラムにしてください
    public static int[] numbers = { 7, 8, 9, 10, 11 };
    public static int[] primes;
    
    public static void main(String[] args) {
        preprocess(args); //ここは無視でOK
        //ここから

        primes = getPrimeNumberArray(numbers);

        //ここまで
        System.out.println(Arrays.toString(primes));
    }

    /**
     * テストコードでテストしやすくするために関数に分離
     */
    public static int[] getPrimeNumberArray(int[] numberArray) {
        return Arrays.stream(numberArray).filter(i -> isPrime(i)).toArray();
    }

    /**
     * 引数n が 素数かどうか判定する
     */
    public static boolean isPrime(int n) {
        // 2は唯一の偶数の素数
        if (n == 2) {
            return true;
        } 
        // 2以外の偶数は素数ではない
        else if (n % 2 == 0) {
            return false;
        }

        // 奇数のみをチェック（3から開始）
        int i = 3;
        // 平方根までチェックすれば十分(の合成数は全て以下である。)
        while (i <= Math.sqrt(n)) {
            // 割り切れる数があれば合成数
            if (n % i == 0) {
                return false;
            } 
            // 次の奇数に進む（偶数は既に除外済み）
            i+=2;
        }

        // すべてのチェックを通過したら素数
        return true;
    }

    /**
     * 引数n が 素数かどうか判定する( Stream と ラムダ式を用いた)
     * こちらの法が、可読性がたかいがパフォーマンスがオーバーヘッドでかつメモリ使用量の増加につながる
     */
    public static boolean isPrimeFP(int n) {
        // 2は唯一の偶数の素数
        if (n == 2) {
            return true;
        } 
        // 2以外の偶数は素数ではない
        else if (n % 2 == 0) {
            return false;
        }

        boolean result = IntStream.range(3, (int)Math.sqrt(n) + 1)
            .filter(k -> k % 2 == 1)
            .noneMatch(j -> n % j == 0);

        // すべてのチェックを通過したら素数
        return result;
    }

    // このメソッドは無視でOK
    private static void preprocess(String[] args) {
        if(args.length > 0) {
            numbers = Arrays.stream(args[0].split(",")).mapToInt(Integer::parseInt).toArray();
        }
    }
}
