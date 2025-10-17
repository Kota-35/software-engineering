package bt1;

public class BasicTask1 {
    public static int max = 100; // 2^31-1 >= max >= 3

    public static void main(String[] args) {
        preprocess(args); // ここは無視でOK
        boolean result = isRem1();
        System.out.print("奇数の二乗を8で割ると常に余りは1");
        if(result) {
            System.out.println("である");
        } else {
            System.out.println("ではない");
        }
    }

    /**
     * isRem1メソッドの仕様：
     * ・区間[3, max]にある各奇数の二乗数を8で割ると1余るかを判定する．
     * ・常に1余るようであれば，resultを変更しない．
     * ・いずれか1つでも余りが1にならなければ，resultにfalseを代入する．
     */
    public static boolean isRem1() {
        boolean result = true;
        // この中身を実装する

        for (int i = 3; i <= max; i++) {
            if (i % 2 == 1) {
                int modulo = i * i % 8;
                if (modulo != 1) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    // このメソッドは無視してOK
    public static void preprocess(String[] args) {
        if(args.length > 0) {
            max = Integer.parseInt(args[0]);
        }
    }
}