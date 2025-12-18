package at2.util;

/**
 * コンソール出力の装飾を提供するユーティリティクラス
 */
public class ConsoleColors {
    // ===== ANSIエスケープコード定数 =====

    /** 装飾をリセットするコード */
    public static final String RESET = "\033[0m";

    // ===== テキスト色 =====

    /** 黒色 */
    public static final String BLACK = "\033[30m";

    /** 赤色（エラー表示に使用） */
    public static final String RED = "\033[31m";

    /** 緑色（成功表示に使用） */
    public static final String GREEN = "\033[32m";

    /** 黄色（警告表示に使用） */
    public static final String YELLOW = "\033[33m";

    /** 青色（ID表示やプロンプトに使用） */
    public static final String BLUE = "\033[34m";

    /** マゼンタ */
    public static final String MAGENTA = "\033[35m";

    /** シアン（情報表示に使用） */
    public static final String CYAN = "\033[36m";

    /** 白色 */
    public static final String WHITE = "\033[37m";

    // ===== テキスト装飾 =====

    /** 太字 */
    public static final String BOLD = "\033[1m";

    // ===== 背景色 =====

    /** 黒背景 */
    public static final String BG_BLACK = "\033[40m";

    /** 赤背景 */
    public static final String BG_RED = "\033[41m";

    /** 緑背景 */
    public static final String BG_GREEN = "\033[42m";

    /** 黄背景 */
    public static final String BG_YELLOW = "\033[43m";

    /** 青背景 */
    public static final String BG_BLUE = "\033[44m";

    /** マゼンタ背景 */
    public static final String BG_MAGENTA = "\033[45m";

    /** シアン背景 */
    public static final String BG_CYAN = "\033[46m";

    /** 白背景 */
    public static final String BG_WHITE = "\033[47m";

    /**
     * テキストに色を付ける
     * 
     * 指定された色コードでテキストを装飾し、最後にRESETコードを付加する。
     * 
     * @param text 装飾するテキスト
     * @param color ANSIカラーコード（例: RED, GREEN, BOLD + BLUE）
     * @return 装飾されたテキスト
     */
    public static String colorize(String text, String color) {
        return color + text + RESET;
    }

    /**
     * 成功メッセージ用の緑色テキストを生成
     * 
     * @param text メッセージテキスト
     * @return 緑色に装飾されたテキスト
     */
    public static String success(String text) {
        return colorize(text, GREEN);
    }

    /**
     * エラーメッセージ用の赤色テキストを生成
     * 
     * @param text メッセージテキスト
     * @return 赤色に装飾されたテキスト
     */
    public static String error(String text) {
        return colorize(text, RED);
    }

    /**
     * 警告メッセージ用の黄色テキストを生成
     * 
     * @param text メッセージテキスト
     * @return 黄色に装飾されたテキスト
     */
    public static String warning(String text) {
        return colorize(text, YELLOW);
    }

    /**
     * 情報メッセージ用のシアン色テキストを生成
     * 
     * @param text メッセージテキスト
     * @return シアン色に装飾されたテキスト
     */
    public static String info(String text) {
        return colorize(text, CYAN);
    }

    /**
     * プロンプト用の青色・太字テキストを生成
     * 
     * @param text プロンプトテキスト
     * @return 青色・太字に装飾されたテキスト
     */
    public static String prompt(String text) {
        return colorize(text, BOLD + BLUE);
    }
}

