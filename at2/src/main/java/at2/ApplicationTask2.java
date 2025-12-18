package at2;

/**
 * Hit & Blowゲームアプリケーションのエントリーポイント
 */
public class ApplicationTask2 {
    /**
     * メインメソッド
     * 
     * @param args コマンドライン引数（未使用）
     */
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.run();
    }
}
