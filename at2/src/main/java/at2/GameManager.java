package at2;

import at2.domain.Answer;
import at2.domain.GameHistory;
import at2.domain.GameResult;
import at2.domain.Guess;
import at2.infrastructure.ResultWriter;
import at2.infrastructure.SeikaiReader;
import at2.service.GameStatistics;
import at2.service.HitBlowCalculator;
import at2.service.InputValidator;
import at2.util.ConsoleColors;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Hit & Blowゲーム全体を管理するクラス
 */
public class GameManager {
    private static final int MAX_ATTEMPTS = 10;
    private final InputValidator inputValidator;
    private final HitBlowCalculator calculator;
    private final SeikaiReader seikaiReader;
    private final List<GameHistory> allHistories;

    /**
     * コンストラクタ
     */
    public GameManager() {
        this.inputValidator = new InputValidator();
        this.calculator = new HitBlowCalculator();
        this.seikaiReader = new SeikaiReader();
        this.allHistories = new ArrayList<>();
    }

    /**
     * ゲームを実行する
     */
    public void run() {
        try {
            // Seikai.txtから正解リストを読み込む
            List<Answer> answers = seikaiReader.readAnswers();

            if (answers.isEmpty()) {
                System.out.println("正解が読み込めませんでした。Seikai.txtを確認してください。");
                return;
            }

            // 標準入力から読み込むためのリーダー
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // 各正解に対してゲームを実行
            for (int i = 0; i < answers.size(); i++) {
                Answer answer = answers.get(i);
                int gameNumber = i + 1;
                GameHistory history = playGame(answer, gameNumber, reader);
                allHistories.add(history);
            }

            // 全ゲーム終了後、Kekka.txtに結果を書き込む
            ResultWriter writer = new ResultWriter(allHistories);
            writer.write();

            // 全ゲーム終了時の統計情報を表示
            displayOverallStatistics();

        } catch (IOException e) {
            System.err.println(ConsoleColors.error("エラーが発生しました: " + e.getMessage()));
            e.printStackTrace();
        }
    }

    /**
     * 1ゲームを実行する
     * 
     * @param answer 正解
     * @param gameNumber ゲーム番号
     * @param reader 標準入力リーダー
     * @return ゲーム履歴
     * @throws IOException 入出力エラー
     */
    private GameHistory playGame(Answer answer, int gameNumber, BufferedReader reader)
            throws IOException {
        GameHistory history = new GameHistory(gameNumber);

        // ゲーム開始メッセージ
        System.out.println(ConsoleColors.info("=== ゲーム " + gameNumber + " 回目 ==="));

        for (int attempt = 0; attempt < MAX_ATTEMPTS; attempt++) {
            // 進捗インジケーターを表示
            displayProgressIndicator(attempt + 1, MAX_ATTEMPTS);

            // プロンプトを表示してユーザー入力を受け取る（有効な入力が得られるまで繰り返す）
            String input = getValidInput(reader, attempt + 1);

            // 推測オブジェクトを作成
            Guess guess = new Guess(input);

            // HitとBlowを計算
            GameResult result = calculator.calculate(answer, guess);

            // 履歴に追加
            history.addResult(result);

            // 標準出力に結果を表示（カラー付き）
            System.out.println(result.toColoredOutputString());

            // 4Hitで終了
            if (result.isComplete()) {
                break;
            }
        }

        // ゲーム終了時のサマリーを表示
        displayGameSummary(history);

        return history;
    }

    /**
     * 有効な入力を取得する（無効な入力は再入力を求める）
     * 
     * @param reader 標準入力リーダー
     * @param attemptNumber 試行回数
     * @return 有効な4桁の数字文字列
     * @throws IOException 入出力エラー
     */
    private String getValidInput(BufferedReader reader, int attemptNumber) throws IOException {
        String input;
        while (true) {
            // プロンプトを表示
            System.out.print(ConsoleColors.prompt("[" + attemptNumber + "回目] 4桁の数字を入力: "));

            input = reader.readLine();
            if (input == null) {
                // EOFの場合の処理（通常は発生しないが念のため）
                throw new IOException("入力が終了しました。");
            }
            input = input.trim();

            // 有効性チェック
            if (inputValidator.isValid(input)) {
                return input;
            }

            // 無効な入力の場合、エラーメッセージを表示して再入力
            System.out.println(ConsoleColors.error(inputValidator.getErrorMessage(input)));
        }
    }

    /**
     * 進捗インジケーターを表示
     * 
     * @param current 現在の試行回数
     * @param max 最大試行回数
     */
    private void displayProgressIndicator(int current, int max) {
        int remaining = max - current;
        String indicator = ConsoleColors.info("[残り試行回数: " + remaining + "/" + max + "]");
        System.out.println(indicator);
    }

    /**
     * ゲーム終了時のサマリーを表示
     * 
     * @param history ゲーム履歴
     */
    private void displayGameSummary(GameHistory history) {
        System.out.println();
        if (history.isCleared()) {
            int attempts = history.getClearAttempts();
            System.out.println(ConsoleColors
                    .success("✓ ゲーム" + history.getGameNumber() + "回目: " + attempts + "回でクリア！"));
        } else {
            System.out.println(
                    ConsoleColors.warning("✗ ゲーム" + history.getGameNumber() + "回目: 10回で終了（未クリア）"));
        }
        System.out.println(ConsoleColors.info("  Hit率: " + history.calculateHitRate() + "%  Blow率: "
                + history.calculateBlowRate() + "%"));
        System.out.println();
    }

    /**
     * 全ゲーム終了時の統計情報を表示
     */
    private void displayOverallStatistics() {
        if (allHistories.isEmpty()) {
            return;
        }

        GameStatistics statistics = new GameStatistics(allHistories);

        System.out.println(ConsoleColors.info("=== 全ゲーム統計 ==="));
        System.out.println(ConsoleColors.info("総ゲーム数: " + statistics.getTotalGames()));
        System.out.println(ConsoleColors.success("クリア数: " + statistics.getClearedGames()));
        System.out.println(ConsoleColors.info("クリア率: " + statistics.calculateClearRate() + "%"));

        int averageAttempts = statistics.calculateAverageAttempts();
        if (averageAttempts > 0) {
            System.out.println(ConsoleColors.info("平均試行回数: " + averageAttempts + "回"));
        }

        int bestAttempts = statistics.getBestClearAttempts();
        if (bestAttempts > 0) {
            System.out.println(ConsoleColors.success("最短クリア: " + bestAttempts + "回"));
        }

        int worstAttempts = statistics.getWorstClearAttempts();
        if (worstAttempts > 0) {
            System.out.println(ConsoleColors.warning("最長クリア: " + worstAttempts + "回"));
        }

        System.out.println();
    }
}

