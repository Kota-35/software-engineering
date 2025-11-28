package at1.service;

import at1.domain.Task;
import at1.domain.TaskRepository;
import at1.infrastructure.FileTaskStorage;
import java.io.IOException;
import java.util.List;

/**
 * タスク管理のサービス層クラス
 */
public class TaskService {
    /** タスクの管理を行うリポジトリ */
    private TaskRepository repository;

    /** ファイル入出力を担当するストレージ */
    private FileTaskStorage storage;

    /**
     * コンストラクタ
     * 
     * @param repository タスクリポジトリ
     * @param storage ファイルストレージ
     */
    public TaskService(TaskRepository repository, FileTaskStorage storage) {
        this.repository = repository;
        this.storage = storage;
    }

    /**
     * リポジトリ内の全タスクをファイルに保存する
     * 
     * リポジトリから全タスクを取得し、ストレージに委譲して ファイルに書き込む。save/load操作の橋渡しを行う。
     * 
     * @throws IOException ファイル書き込みに失敗した場合
     */
    public void save() throws IOException {
        // リポジトリから全タスクを取得
        List<Task> tasks = repository.findAll();
        // ストレージに委譲してファイルに保存
        storage.saveTasks(tasks);
    }

    /**
     * ファイルからタスクを読み込み、リポジトリを更新する
     * 
     * ストレージからタスクを読み込み、リポジトリ内の既存タスクを 全て置き換える。load操作の橋渡しを行う。
     * 
     * @throws IOException ファイル読み込みに失敗した場合
     */
    public void load() throws IOException {
        // ストレージからタスクを読み込み
        List<Task> tasks = storage.loadTasks();
        // リポジトリの内容を読み込んだタスクで置き換え
        repository.replaceAll(tasks);
    }

    // 将来の拡張: 完了率の計算、タスクの検索、期限管理など
}
