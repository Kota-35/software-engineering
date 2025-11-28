package at1.infrastructure;

import at1.domain.Task;
import at1.domain.TaskRepository;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * インメモリでタスクを管理するリポジトリ実装
 * 
 * TaskRepositoryインターフェースの具体的な実装クラス。 タスクをメモリ上のMapに保存し、アプリケーション終了時にデータは失われる。
 * LinkedHashMapを使用することで、タスクの挿入順序を保持する。
 */
public class InMemoryTaskRepository implements TaskRepository {
    /** タスクをIDをキーとして保持するMap（挿入順序を保持） */
    private Map<Integer, Task> tasks;

    /** 次に割り当てるタスクID（自動採番用） */
    private int nextId;

    /**
     * コンストラクタ
     * 
     * 空のタスクMapを初期化し、IDの採番を1から開始する。
     */
    public InMemoryTaskRepository() {
        // LinkedHashMapを使用して挿入順序を保持
        this.tasks = new LinkedHashMap<>();
        // IDは1から開始
        this.nextId = 1;
    }

    /**
     * 新しいタスクを追加する
     * 
     * 現在のnextIdを使用してタスクを生成し、Mapに追加する。 その後、nextIdをインクリメントして次のID採番に備える。
     * 
     * @param name タスクの名前
     * @return 追加されたタスクオブジェクト
     */
    @Override
    public Task add(String name) {
        // 新しいタスクを現在のnextIdで生成
        Task task = new Task(nextId, name);
        // MapにタスクをIDをキーとして格納
        tasks.put(nextId, task);
        // 次のID採番のためインクリメント
        nextId++;
        return task;
    }

    /**
     * 指定されたIDのタスクを検索する
     * 
     * @param id 検索するタスクのID
     * @return 見つかったタスク
     * @throws IllegalArgumentException タスクが見つからない場合
     */
    @Override
    public Task findById(int id) throws IllegalArgumentException {
        // MapからIDに対応するタスクを取得
        Task task = tasks.get(id);
        // タスクが存在しない場合は例外をスロー
        if (task == null) {
            throw new IllegalArgumentException("Task #" + id + " not found.");
        }
        return task;
    }

    /**
     * 全てのタスクをリストとして取得する
     * 
     * Map内の全タスクを新しいArrayListに変換して返す。
     * 
     * @return 全タスクのリスト
     */
    @Override
    public List<Task> findAll() {
        // Mapのvaluesを新しいListに変換して返す
        return new ArrayList<>(tasks.values());
    }

    /**
     * 指定されたIDのタスクを完了状態にする
     * 
     * @param id 完了にするタスクのID
     * @throws IllegalArgumentException タスクが見つからない場合
     */
    @Override
    public void markAsDone(int id) throws IllegalArgumentException {
        // タスクを検索し、完了状態に設定
        // findById()が例外をスローする可能性がある
        findById(id).setDone(true);
    }

    /**
     * 指定されたIDのタスクを削除する
     * 
     * @param id 削除するタスクのID
     * @throws IllegalArgumentException タスクが見つからない場合
     */
    @Override
    public void delete(int id) throws IllegalArgumentException {
        // タスクの存在を確認（存在しない場合は例外がスローされる）
        Task task = findById(id);
        // MapからタスクのIDをキーとして削除
        tasks.remove(task.getId());
    }

    /**
     * 全てのタスクを削除し、ID採番をリセットする
     */
    @Override
    public void clear() {
        // Map内の全タスクを削除
        tasks.clear();
        // ID採番を1にリセット
        nextId = 1;
    }

    /**
     * リポジトリ内の全タスクを指定されたタスクリストで置き換える
     * 
     * ファイルからタスクを読み込む際に使用される。 既存のタスクをクリアし、新しいタスクを追加する。 nextIdは読み込まれたタスクの最大IDより1大きい値に設定される。
     * 
     * @param newTasks 置き換え後のタスクリスト
     */
    @Override
    public void replaceAll(List<Task> newTasks) {
        // 既存のタスクを全て削除
        clear();
        // 新しいタスクリストを1つずつMapに追加
        for (Task task : newTasks) {
            tasks.put(task.getId(), task);
            // 読み込んだタスクのIDが現在のnextId以上の場合、nextIdを更新
            // これにより、新規タスク追加時にIDの重複を防ぐ
            if (task.getId() >= nextId) {
                nextId = task.getId() + 1;
            }
        }
    }

    /**
     * 完了済みタスクの数を計算する
     * 
     * Java Stream APIを使用して完了状態のタスクをフィルタリングし、 その数をカウントする。
     * 
     * @return 完了済みタスクの数
     */
    @Override
    public long calculateDoneCount() {
        // Stream APIで完了タスクをフィルタリングしてカウント
        return findAll().stream().filter(task -> task.isDone()).count();
    }

    /**
     * 未完了タスクの数を計算する
     * 
     * Java Stream APIを使用して未完了状態のタスクをフィルタリングし、 その数をカウントする。
     * 
     * @return 未完了タスクの数
     */
    @Override
    public long calculateTodoCount() {
        // Stream APIで未完了タスクをフィルタリングしてカウント
        return findAll().stream().filter(task -> !task.isDone()).count();
    }

    /**
     * 全タスクの数を取得する
     * 
     * @return タスクの総数
     */
    @Override
    public long getTaskSize() {
        return findAll().size();
    }
}
