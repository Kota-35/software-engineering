package at1.domain;

import java.util.Date;

/**
 * タスクエンティティクラス
 */
public class Task {

    /** タスクを一意に識別するID */
    private int id;

    /** タスクの内容を表す名前 */
    private String name;

    /** タスクの完了状態（true: 完了, false: 未完了） */
    private boolean isDone;

    /** タスクが作成された日時 */
    private Date createdAt;

    /**
     * タスクのコンストラクタ
     * 
     * 新規タスクを生成する。完了状態は初期値として未完了（false）、 作成日時は現在時刻で初期化される。
     * 
     * @param id タスクを一意に識別するID
     * @param name タスクの内容を表す名前
     */
    public Task(int id, String name) {
        this.id = id;
        this.name = name;
        // 初期状態は未完了
        this.isDone = false;
        // 作成時の現在時刻を記録
        this.createdAt = new Date();
    }

    /**
     * タスクのIDを取得する
     * 
     * @return タスクのID
     */
    public int getId() {
        return this.id;
    }

    /**
     * タスクの名前を取得する
     * 
     * @return タスクの名前（内容）
     */
    public String getName() {
        return this.name;
    }

    /**
     * タスクの完了状態を取得する
     * 
     * @return タスクが完了している場合true、未完了の場合false
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * タスクの完了状態を設定する
     * 
     * @param done 設定する完了状態（true: 完了, false: 未完了）
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * タスクの作成日時を取得する
     * 
     * @return タスクが作成された日時
     */
    public Date getCreatedAt() {
        return this.createdAt;
    }

    /**
     * タスクの作成日時を設定する
     * 
     * 主にファイルから読み込んだタスクを復元する際に使用される。
     * 
     * @param createdAt 設定する作成日時
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * タスクをCSV形式の文字列に変換する（保存用）
     * 
     * ファイルに保存する際のシリアライズ形式を提供する。 フォーマット: "id,name,isDone,createdAt"
     * 
     * @return CSV形式のタスク情報
     */
    public String toSaveString() {
        return id + "," + name + "," + isDone + "," + createdAt;
    }
}
