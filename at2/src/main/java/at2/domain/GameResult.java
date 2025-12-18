package at2.domain;

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
}

