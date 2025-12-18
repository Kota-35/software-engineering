package at2.infrastructure;

import at2.domain.Answer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Seikai.txtから正解を読み込むクラス
 */
public class SeikaiReader {
    private static final String FILE_NAME = "Seikai.txt";

    /**
     * Seikai.txtから正解リストを読み込む
     * 
     * @return 正解リスト（重複があってもそのまま含める）
     * @throws IOException ファイル読み込みエラー
     */
    public List<Answer> readAnswers() throws IOException {
        List<Answer> answers = new ArrayList<>();
        Path filePath = getFilePath();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                // 空行はスキップ
                if (line.isEmpty()) {
                    continue;
                }
                // 4桁の数字として読み込む（検証は簡易的に行う）
                if (line.length() == 4) {
                    answers.add(new Answer(line));
                }
            }
        }

        return answers;
    }

    /**
     * Seikai.txtのファイルパスを取得 プロジェクト直下（srcと同階層）を想定
     * 
     * @return ファイルパス
     */
    private Path getFilePath() {
        // 現在の作業ディレクトリから相対的にSeikai.txtを探す
        // 実行時はプロジェクトルートがカレントディレクトリになることを想定
        return Paths.get(FILE_NAME);
    }
}

