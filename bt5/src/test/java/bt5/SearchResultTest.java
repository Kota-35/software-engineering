package bt5;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SearchResultTest {
    
    @Test
    public void toStringTest() {
        SearchResult searchResult = new SearchResult(1, 2);

        String expected = "1:2";
        String result = searchResult.toString();

        assertEquals(expected, result);
    }

    @Test
    public void toStringTest2() {
        SearchResult searchResult = new SearchResult(5, 30);

        String expected = "5:30";
        String result = searchResult.toString();

        assertEquals(expected, result);
    }
}
