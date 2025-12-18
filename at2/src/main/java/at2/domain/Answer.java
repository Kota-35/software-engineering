package at2.domain;

/**
 * 正解を表すドメインモデル 4桁の数字（0-9、重複なし）を保持する
 */
public class Answer {
    private final String value;

    /**
     * コンストラクタ
     * 
     * @param value 4桁の数字文字列（検証済みであることを前提）
     */
    public Answer(String value) {
        this.value = value;
    }

    /**
     * 正解の値を取得
     * 
     * @return 4桁の数字文字列
     */
    public String getValue() {
        return value;
    }

    /**
     * 指定位置の数字を取得
     * 
     * @param index 位置（0-3）
     * @return 数字文字
     */
    public char getDigit(int index) {
        return value.charAt(index);
    }

    /**
     * 指定の数字が正解に含まれるかチェック
     * 
     * @param digit 数字文字
     * @return 含まれる場合true
     */
    public boolean contains(char digit) {
        return value.indexOf(digit) != -1;
    }

    /**
     * 指定位置の数字が一致するかチェック
     * 
     * @param index 位置（0-3）
     * @param digit 数字文字
     * @return 一致する場合true
     */
    public boolean matchesAt(int index, char digit) {
        return value.charAt(index) == digit;
    }
}

