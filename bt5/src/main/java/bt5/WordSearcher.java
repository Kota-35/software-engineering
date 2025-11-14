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

        // System.inに標準入力から入力されたデータが流れる．
        // それをjava.util.Scannerクラスのインスタンスを使って読み取る．
        Scanner scanner = new Scanner(System.in);
        
        // Scannerによって様々な入力データの読み取り方があるが，
        // 一行分を読み取るにはnextLineメソッドを利用する
        String filename = scanner.nextLine();
        targetFile = new File(filename);

        // ここまで
        return targetFile;
    }

    @SuppressWarnings("resource")
    private String requireSearchKey() {
        String key = "";
        System.out.println("Input search key:");
        // ユーザから検索キーの入力を受け付けて，keyに参照させる
        // ここから
        // keyの入力を取得
        Scanner scanner = new Scanner(System.in);
        key = scanner.nextLine();

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
        // ファイルから全行を読み込むために，try-with-resources文を使用して
        // BufferedReaderとFileReaderを組み合わせて読み込む．
        try (BufferedReader reader = new BufferedReader(new FileReader(searchTarget))) {
            String line;
            // readLine()メソッドで1行ずつ読み込み，ファイルの終わり（null）に達するまで繰り返す．
            // 各行をlinesリストに追加することで，ファイル全体の内容を保持する．
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            // ファイル読み込み中にエラーが発生した場合，スタックトレースを出力して処理を継続する．
            e.printStackTrace();
        }

        // ここまで
        return lines;
    }

    private void outputFile(LinkedList<SearchResult> searchResults) {
        // 標準出力と同じ内容をoutput.txtに出力する．
        // ここから
        // 検索結果をoutput.txtに出力するために，try-with-resources文を使用して
        // PrintWriterとFileWriterを組み合わせて書き込む．
        try (PrintWriter writer = new PrintWriter(new FileWriter("output.txt"))) {
            // 検索結果が空の場合は，"Not Found"を出力する．
            if (searchResults.isEmpty()) {
                writer.println("Not Found");
            } else {
                // 検索結果がある場合，各SearchResultを1行ずつ出力する．
                // SearchResultのtoString()メソッドにより，"行番号:位置"の形式で出力される．
                for (SearchResult result : searchResults) {
                    writer.println(result);
                }
            }
        } catch (IOException e) {
            // ファイル書き込み中にエラーが発生した場合，スタックトレースを出力して処理を継続する．
            e.printStackTrace();
        }

        // ここまで
    }
    
}
