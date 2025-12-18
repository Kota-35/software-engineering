package at2;

import at2.domain.Answer;
import at2.domain.GameHistory;
import at2.domain.GameResult;
import at2.domain.Guess;
import at2.infrastructure.ResultWriter;
import at2.infrastructure.SeikaiReader;
import at2.service.HitBlowCalculator;
import at2.service.InputValidator;
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

        } catch (IOException e) {
            System.err.println("エラーが発生しました: " + e.getMessage());
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

        for (int attempt = 0; attempt < MAX_ATTEMPTS; attempt++) {
            // ユーザー入力を受け取る（有効な入力が得られるまで繰り返す）
            String input = getValidInput(reader);

            // 推測オブジェクトを作成
            Guess guess = new Guess(input);

            // HitとBlowを計算
            GameResult result = calculator.calculate(answer, guess);

            // 履歴に追加
            history.addResult(result);

            // 標準出力に結果を表示
            System.out.println(result.toOutputString());

            // 4Hitで終了
            if (result.isComplete()) {
                break;
            }
        }

        return history;
    }

    /**
     * 有効な入力を取得する（無効な入力は再入力を求める）
     * 
     * @param reader 標準入力リーダー
     * @return 有効な4桁の数字文字列
     * @throws IOException 入出力エラー
     */
    private String getValidInput(BufferedReader reader) throws IOException {
        String input;
        while (true) {
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
            System.out.println(inputValidator.getErrorMessage(input));
        }
    }
}

