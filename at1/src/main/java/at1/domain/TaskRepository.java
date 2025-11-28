package at1.domain;

import java.util.List;

/**
 * タスクリポジトリのインターフェース
 * 
 */
public interface TaskRepository {
    /**
     * 新しいタスクを追加する
     * 
     * @param name タスクの名前（内容）
     * @return 追加されたタスクオブジェクト（IDが自動採番される）
     */
    Task add(String name);

    /**
     * 指定されたIDのタスクを検索する
     * 
     * @param id 検索するタスクのID
     * @return 見つかったタスクオブジェクト
     * @throws IllegalArgumentException 指定されたIDのタスクが存在しない場合
     */
    Task findById(int id) throws IllegalArgumentException;

    /**
     * 全てのタスクを取得する
     * 
     * @return 全タスクのリスト
     */
    List<Task> findAll();

    /**
     * 指定されたIDのタスクを完了状態にする
     * 
     * @param id 完了にするタスクのID
     * @throws IllegalArgumentException 指定されたIDのタスクが存在しない場合
     */
    void markAsDone(int id) throws IllegalArgumentException;

    /**
     * 指定されたIDのタスクを削除する
     * 
     * @param id 削除するタスクのID
     * @throws IllegalArgumentException 指定されたIDのタスクが存在しない場合
     */
    void delete(int id) throws IllegalArgumentException;

    /**
     * 全てのタスクを削除する
     * 
     * リポジトリ内の全タスクを削除し、ID採番もリセットする。
     */
    void clear();

    /**
     * リポジトリ内の全タスクを指定されたタスクリストで置き換える
     * 
     * 主にファイルから読み込んだタスクでリポジトリを更新する際に使用される。
     * 
     * @param tasks 置き換え後のタスクリスト
     */
    void replaceAll(List<Task> tasks);

    /**
     * 完了済みタスクの数を計算する
     * 
     * @return 完了済みタスクの数
     */
    long calculateDoneCount();

    /**
     * 未完了タスクの数を計算する
     * 
     * @return 未完了タスクの数
     */
    long calculateTodoCount();

    /**
     * 全タスクの数を取得する
     * 
     * @return タスクの総数
     */
    long getTaskSize();
}
