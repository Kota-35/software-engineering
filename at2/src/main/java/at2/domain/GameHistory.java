package at2.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 1ゲームの履歴を表すドメインモデル
 */
public class GameHistory {
    private final int gameNumber;
    private final List<GameResult> results;

    /**
     * コンストラクタ
     * 
     * @param gameNumber ゲーム番号（1から開始）
     */
    public GameHistory(int gameNumber) {
        this.gameNumber = gameNumber;
        this.results = new ArrayList<>();
    }

    /**
     * 結果を追加
     * 
     * @param result ゲーム結果
     */
    public void addResult(GameResult result) {
        results.add(result);
    }

    /**
     * ゲーム番号を取得
     * 
     * @return ゲーム番号
     */
    public int getGameNumber() {
        return gameNumber;
    }

    /**
     * 結果リストを取得
     * 
     * @return 結果リスト
     */
    public List<GameResult> getResults() {
        return new ArrayList<>(results);
    }

    /**
     * 入力回数を取得
     * 
     * @return 入力回数
     */
    public int getInputCount() {
        return results.size();
    }

    /**
     * Hit率を計算（小数点以下切り捨て）
     * 
     * @return Hit率（%）
     */
    public int calculateHitRate() {
        if (results.isEmpty()) {
            return 0;
        }
        int totalHit = results.stream().mapToInt(GameResult::getHit).sum();
        return (totalHit * 100) / (4 * results.size());
    }

    /**
     * Blow率を計算（小数点以下切り捨て）
     * 
     * @return Blow率（%）
     */
    public int calculateBlowRate() {
        if (results.isEmpty()) {
            return 0;
        }
        int totalBlow = results.stream().mapToInt(GameResult::getBlow).sum();
        return (totalBlow * 100) / (4 * results.size());
    }
}

