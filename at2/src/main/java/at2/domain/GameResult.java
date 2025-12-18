package at2.domain;

import at2.util.ConsoleColors;

/**
 * 1回の入力に対する結果を表すドメインモデル
 */
public class GameResult {
    private final Guess guess;
    private final int hit;
    private final int blow;

    /**
     * コンストラクタ
     * 
     * @param guess ユーザーの推測
     * @param hit Hit数
     * @param blow Blow数
     */
    public GameResult(Guess guess, int hit, int blow) {
        this.guess = guess;
        this.hit = hit;
        this.blow = blow;
    }

    /**
     * 推測を取得
     * 
     * @return ユーザーの推測
     */
    public Guess getGuess() {
        return guess;
    }

    /**
     * Hit数を取得
     * 
     * @return Hit数
     */
    public int getHit() {
        return hit;
    }

    /**
     * Blow数を取得
     * 
     * @return Blow数
     */
    public int getBlow() {
        return blow;
    }

    /**
     * 完全一致（4Hit）かどうかチェック
     * 
     * @return 4Hitの場合true
     */
    public boolean isComplete() {
        return hit == 4;
    }

    /**
     * 結果を文字列形式で取得（標準出力用）
     * 
     * @return "入力 Hit:数 Blow:数" 形式の文字列
     */
    public String toOutputString() {
        return guess.getValue() + " Hit:" + hit + " Blow:" + blow;
    }

    /**
     * 結果をカラー付き文字列形式で取得（標準出力用） Hitは緑色、Blowは黄色で表示
     * 
     * @return カラー装飾された "入力 Hit:数 Blow:数" 形式の文字列
     */
    public String toColoredOutputString() {
        String hitColored = ConsoleColors.success("Hit:" + hit);
        String blowColored = ConsoleColors.warning("Blow:" + blow);
        return guess.getValue() + " " + hitColored + " " + blowColored;
    }
}

