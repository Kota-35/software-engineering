package bt5;
import java.io.*;
import java.util.*;
public class WordSearcher {
    public List<SearchResult> search() {
        LinkedList<SearchResult> searchResults = new LinkedList<>();
        File searchTarget = this.requireFile();
        String key = this.requireSearchKey();
        searchResults.addAll(this.search(searchTarget, key));
        this.outputFile(searchResults);
        return searchResults;
    }

    @SuppressWarnings("resource")
    private File requireFile() {
        File targetFile = new File("."); // "."は現在の作業フォルダを指す
        System.out.println("Input file name:");
        // ユーザから検索対象のファイル名の入力を受け付けて，
        // そのファイルを指すFileインスタンスをtargetFileに参照させる
        // ここでは英文のテキスト(txt)ファイルさえ扱えればよいとする
        // ここから

        // ここまで
        return targetFile;
    }

    @SuppressWarnings("resource")
    private String requireSearchKey() {
        String key = "";
        System.out.println("Input search key:");
        // ユーザから検索キーの入力を受け付けて，keyに参照させる
        // ここから

        // ここまで
        return key;
    }

    private LinkedList<SearchResult> search(File searchTarget, String key) {
        LinkedList<SearchResult> results = new LinkedList<>();

        LinkedList<String> targetStrings = new LinkedList<>();
        targetStrings.addAll(readStrings(searchTarget));
        for (int i = 0; i < targetStrings.size(); i++) { // 各行を処理する
            String line = targetStrings.get(i); // i番目（=i+1行目）を取得
            String[] split = line.split(" "); // 半角空白区切りの文字列分割
            for (int j = 0; j < split.length; j++) { // 各単語を処理する
                if (split[j].equals(key)) { // "完全一致"で検索
                    // "行番号"や"単語の位置"は1から始まるものとする
                    results.add(new SearchResult(i + 1, j + 1));
                }
            }
        }

        return results;
    }

    private LinkedList<String> readStrings(File searchTarget) {
        LinkedList<String> lines = new LinkedList<>();
        // ファイルから全文字列を読み出す．
        // 読み出す文字列は1行（改行まで）を1要素としてlinesに全行分追加する．
        // ここから

        // ここまで
        return lines;
    }

    private void outputFile(LinkedList<SearchResult> searchResults) {
        // 標準出力と同じ内容をoutput.txtに出力する．
        // ここから

        // ここまで
    }
    
}
