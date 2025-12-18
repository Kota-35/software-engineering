package at2.infrastructure;

import at2.domain.GameHistory;
import at2.domain.GameResult;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Kekka.txtに結果を書き込むクラス
 */
public class ResultWriter {
    private static final String FILE_NAME = "Kekka.txt";
    private final List<GameHistory> allHistories;

    /**
     * コンストラクタ
     * 
     * @param allHistories 全ゲームの履歴
     */
    public ResultWriter(List<GameHistory> allHistories) {
        this.allHistories = allHistories;
    }

    /**
     * 全ゲームの結果をKekka.txtに書き込む
     * 
     * @throws IOException ファイル書き込みエラー
     */
    public void write() throws IOException {
        Path filePath = getFilePath();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            for (GameHistory history : allHistories) {
                writeGameHistory(writer, history);
            }
        }
    }

    /**
     * 1ゲーム分の履歴を書き込む
     * 
     * @param writer バッファ付きライター
     * @param history ゲーム履歴
     * @throws IOException ファイル書き込みエラー
     */
    private void writeGameHistory(BufferedWriter writer, GameHistory history) throws IOException {
        // ゲーム番号
        writer.write("ゲーム " + history.getGameNumber() + " 回目");
        writer.newLine();

        // 各入力結果（最大10回分）
        List<GameResult> results = history.getResults();
        int maxResults = Math.min(results.size(), 10);
        for (int i = 0; i < maxResults; i++) {
            writer.write(results.get(i).toOutputString());
            writer.newLine();
        }

        // Hit率とBlow率
        int hitRate = history.calculateHitRate();
        int blowRate = history.calculateBlowRate();
        writer.write("Hit 率: " + hitRate + "% Blow 率: " + blowRate + "%");
        writer.newLine();
    }

    /**
     * Kekka.txtのファイルパスを取得 プロジェクト直下（srcと同階層）を想定
     * 
     * @return ファイルパス
     */
    private Path getFilePath() {
        // 現在の作業ディレクトリから相対的にKekka.txtを探す
        // 実行時はプロジェクトルートがカレントディレクトリになることを想定
        return Paths.get(FILE_NAME);
    }
}

