package at2.service;

/**
 * ユーザー入力の検証を行うサービス
 */
public class InputValidator {
    private static final int REQUIRED_LENGTH = 4;

    /**
     * 入力が有効かどうかを検証 仕様：4桁、0-9の数字、重複なし
     * 
     * @param input ユーザー入力文字列
     * @return 有効な場合true
     */
    public boolean isValid(String input) {
        if (input == null) {
            return false;
        }

        // 4桁かチェック
        if (input.length() != REQUIRED_LENGTH) {
            return false;
        }

        // 各文字が数字（0-9）かチェック
        for (char c : input.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }

        // 重複チェック
        for (int i = 0; i < REQUIRED_LENGTH; i++) {
            for (int j = i + 1; j < REQUIRED_LENGTH; j++) {
                if (input.charAt(i) == input.charAt(j)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 無効な入力に対するエラーメッセージを取得
     * 
     * @param input ユーザー入力文字列
     * @return エラーメッセージ
     */
    public String getErrorMessage(String input) {
        if (input == null || input.length() != REQUIRED_LENGTH) {
            return "4桁の数字を入力してください。";
        }

        // 数字以外の文字が含まれているかチェック
        for (char c : input.toCharArray()) {
            if (c < '0' || c > '9') {
                return "0-9の数字のみを入力してください。";
            }
        }

        // 重複チェック
        for (int i = 0; i < REQUIRED_LENGTH; i++) {
            for (int j = i + 1; j < REQUIRED_LENGTH; j++) {
                if (input.charAt(i) == input.charAt(j)) {
                    return "数字が重複しています。4つの異なる数字を入力してください。";
                }
            }
        }

        return "無効な入力です。";
    }
}

