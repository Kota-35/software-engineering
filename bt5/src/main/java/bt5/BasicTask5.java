package bt5;
import java.util.List;
import java.util.Objects;
public class BasicTask5 {
    public static void main(String[] args) {
        WordSearcher wordSearcher = new WordSearcher();
        List<SearchResult> searchResults = wordSearcher.search();
        //検索結果が空であったとき
        if(Objects.isNull(searchResults) || searchResults.isEmpty()) {
            System.out.println("Not Found");
            return;
        }
        //検索結果が空でなかったとき
        for(SearchResult searchResult : searchResults) {
            //searchResultの部分はsearchResult.toString()に
            //置き換えられて実行される．
            System.out.println(searchResult);
        }
    }
}
