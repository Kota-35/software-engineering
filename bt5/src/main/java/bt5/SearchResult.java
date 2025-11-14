package bt5;
public class SearchResult {
    private int rowNumber; //行番号
    private int position; //単語の位置
    public SearchResult(int rowNumber, int position) {
        this.rowNumber = rowNumber; //行番号を初期化
        this.position = position; //単語の位置を初期化
    }
    @Override
    public String toString() {
        //例えば，5行目の3単語目に検索キーが登場するならば，
        //5:3 と出力する．
        return rowNumber + ":" + position;
    }
}
