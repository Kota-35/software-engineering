package at2.service;

import at2.domain.GameHistory;
import java.util.List;

/**
 * 全ゲームの統計情報を計算するサービス
 */
public class GameStatistics {
    private final List<GameHistory> histories;

    /**
     * コンストラクタ
     * 
     * @param histories 全ゲームの履歴
     */
    public GameStatistics(List<GameHistory> histories) {
        this.histories = histories;
    }

    /**
     * 平均試行回数を計算（小数点以下切り捨て）
     * 
     * @return 平均試行回数
     */
    public int calculateAverageAttempts() {
        if (histories.isEmpty()) {
            return 0;
        }
        int totalAttempts = histories.stream().mapToInt(GameHistory::getInputCount).sum();
        return totalAttempts / histories.size();
    }

    /**
     * 最短クリア回数を取得
     * クリアされたゲームがない場合は-1を返す
     * 
     * @return 最短クリア回数、クリアされたゲームがない場合は-1
     */
    public int getBestClearAttempts() {
        return histories.stream()
                .filter(GameHistory::isCleared)
                .mapToInt(GameHistory::getClearAttempts)
                .min()
                .orElse(-1);
    }

    /**
     * 最長クリア回数を取得
     * クリアされたゲームがない場合は-1を返す
     * 
     * @return 最長クリア回数、クリアされたゲームがない場合は-1
     */
    public int getWorstClearAttempts() {
        return histories.stream()
                .filter(GameHistory::isCleared)
                .mapToInt(GameHistory::getClearAttempts)
                .max()
                .orElse(-1);
    }

    /**
     * クリア率を計算（小数点以下切り捨て）
     * 
     * @return クリア率（%）
     */
    public int calculateClearRate() {
        if (histories.isEmpty()) {
            return 0;
        }
        long clearedCount = histories.stream().filter(GameHistory::isCleared).count();
        return (int) (clearedCount * 100 / histories.size());
    }

    /**
     * 総ゲーム数を取得
     * 
     * @return 総ゲーム数
     */
    public int getTotalGames() {
        return histories.size();
    }

    /**
     * クリアしたゲーム数を取得
     * 
     * @return クリアしたゲーム数
     */
    public int getClearedGames() {
        return (int) histories.stream().filter(GameHistory::isCleared).count();
    }
}

