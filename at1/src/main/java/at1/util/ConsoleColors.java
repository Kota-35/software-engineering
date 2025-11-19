package at1.util;

public class ConsoleColors {
    // ANSIエスケープコード
    public static final String RESET = "\033[0m";

    // テキスト色
    public static final String BLACK = "\033[30m";
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
    public static final String MAGENTA = "\033[35m";
    public static final String CYAN = "\033[36m";
    public static final String WHITE = "\033[37m";

    // 太字
    public static final String BOLD = "\033[1m";

    // 背景色
    public static final String BG_BLACK = "\033[40m";
    public static final String BG_RED = "\033[41m";
    public static final String BG_GREEN = "\033[42m";
    public static final String BG_YELLOW = "\033[43m";
    public static final String BG_BLUE = "\033[44m";
    public static final String BG_MAGENTA = "\033[45m";
    public static final String BG_CYAN = "\033[46m";
    public static final String BG_WHITE = "\033[47m";

    public static String colorize(String text, String color) {
        return color + text + RESET;
    }

    public static String success(String text) {
        return colorize(text, GREEN);
    }

    public static String error(String text) {
        return colorize(text, RED);
    }

    public static String warning(String text) {
        return colorize(text, YELLOW);
    }

    public static String info(String text) {
        return colorize(text, CYAN);
    }

    public static String prompt(String text) {
        return colorize(text, BOLD + BLUE);
    }
}
