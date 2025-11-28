package at1;

import at1.domain.TaskRepository;
import at1.infrastructure.FileTaskStorage;
import at1.infrastructure.InMemoryTaskRepository;
import at1.service.TaskService;
import at1.ui.CommandHandler;

/**
 * TODO管理アプリケーションの中核クラス
 * 
 * このクラスは依存性の注入（DI）を手動で行い、 アプリケーションの各層を初期化・連携させる役割を持つ。
 */
public class TodoManager {
    /**
     * TODO管理アプリケーションを実行する
     * 
     * 各層のコンポーネントを以下の順序で初期化し、 CommandHandlerにコントロールを渡す。 1. リポジトリ層（データ管理） 2. ストレージ層（ファイル永続化） 3.
     * サービス層（ビジネスロジック） 4. UI層（ユーザーインタラクション）
     */
    public void run() {
        // インメモリでタスクを管理するリポジトリを生成
        TaskRepository repository = new InMemoryTaskRepository();

        // ファイルへの保存・読み込みを担当するストレージを生成
        FileTaskStorage storage = new FileTaskStorage();

        // リポジトリとストレージを連携させるサービスを生成
        TaskService service = new TaskService(repository, storage);

        // ユーザーからのコマンド入力を処理するハンドラーを生成
        CommandHandler handler = new CommandHandler(repository, service);

        // コマンドハンドラーを起動し、ユーザー入力の受付を開始
        handler.run();
    }
}
