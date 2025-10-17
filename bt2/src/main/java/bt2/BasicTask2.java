package bt2;

public class BasicTask2 {
    public static int n = 5;
    public static int r = 3;

    public static void main(String[] args) {
        preprocess(args); // ここは無視でOK
        // 順列nPrを求める．ここでの例はn=5,r=3
        System.out.println(perm(n, r));
    }

    /**
     * nかrが1<=r<=nを満たさない場合，その旨を標準出力に出力し，0を返す
     */
    public static int perm(int n, int r) {
        // この中身を実装する

        // r が条件を満たさない場合, 標準出力で理由を出力し 0 を返す
        if (r < 1 || r > n) {
            System.out.println("rは 1 <= r <=" + n + "の範囲である必要があります" );
            return 0;
        }

        // r が 1の時は nを返すようにする (初期値)
        if (r == 1) {
            return n;
        }

        // 再帰的に順列を計算する
        int result = n * perm(n - 1, r - 1);

        return result;
    }

    // このメソッドは無視してOK
    public static void preprocess(String[] args) {
        if (args.length > 1) {
            n = Integer.parseInt(args[0]);
            r = Integer.parseInt(args[1]);
        }
    }
}
