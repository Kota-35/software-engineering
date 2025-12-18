package at2.domain;

/**
 * ユーザーの推測を表すドメインモデル
 * 4桁の数字（0-9、重複なし）を保持する
 */
public class Guess {
    private final String value;

    /**
     * コンストラクタ
     * @param value 4桁の数字文字列（検証済みであることを前提）
     */
    public Guess(String value) {
        this.value = value;
    }

    /**
     * 推測の値を取得
     * @return 4桁の数字文字列
     */
    public String getValue() {
        return value;
    }

    /**
     * 指定位置の数字を取得
     * @param index 位置（0-3）
     * @return 数字文字
     */
    public char getDigit(int index) {
        return value.charAt(index);
    }
}

