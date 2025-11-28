package at1.infrastructure;

import at1.domain.Task;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ファイルベースのタスクストレージ実装
 * 
 * タスクをCSV形式でtodo.txtファイルに保存・読み込みする機能を提供する。 このクラスはインフラ層に属し、永続化の詳細を隠蔽する。
 */
public class FileTaskStorage {
    /** タスクを保存するファイル名 */
    private static final String FILE_NAME = "todo.txt";

    /**
     * タスクリストをファイルに保存する
     * 
     * 各タスクをCSV形式にシリアライズし、1行ずつファイルに書き込む。 ファイルや親ディレクトリが存在しない場合は自動的に作成する。
     * 
     * @param tasks 保存するタスクのリスト
     * @throws IOException ファイル操作に失敗した場合
     */
    public void saveTasks(List<Task> tasks) throws IOException {
        // 保存先のFileオブジェクトを生成
        File file = new File(FILE_NAME);

        // 親ディレクトリが存在しない場合は作成
        // （カレントディレクトリの場合はnullになる）
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        // ファイルが存在しない場合は新規作成
        if (!file.exists()) {
            file.createNewFile();
        }

        // try-with-resourcesでファイルライターを自動クローズ
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            // 各タスクをCSV形式にシリアライズしてファイルに書き込み
            for (Task task : tasks) {
                writer.println(serializeTask(task));
            }
        }
    }

    /**
     * ファイルからタスクリストを読み込む
     * 
     * todo.txtファイルから1行ずつ読み込み、各行をタスクオブジェクトに デシリアライズする。ファイルが存在しない場合は空のリストを返す。 不正な行は無視して処理を継続する。
     * 
     * @return 読み込んだタスクのリスト
     * @throws IOException ファイル読み込みに失敗した場合
     */
    public List<Task> loadTasks() throws IOException {
        // 読み込んだタスクを格納するリスト
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_NAME);

        // ファイルが存在しない場合は空のリストを返す
        if (!file.exists()) {
            return tasks;
        }

        // try-with-resourcesでファイルリーダーを自動クローズ
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // ファイルから1行ずつ読み込む
            while ((line = reader.readLine()) != null) {
                // 行をタスクオブジェクトにデシリアライズ
                Task task = deserializeTask(line);
                // デシリアライズに成功した場合のみリストに追加
                // （不正な行はnullが返されるため無視される）
                if (task != null) {
                    tasks.add(task);
                }
            }
        }

        return tasks;
    }

    /**
     * タスクをCSV形式の文字列にシリアライズする
     * 
     * フォーマット: "id,name,isDone,createdAt(timestamp)" 作成日時はUnixタイムスタンプ（ミリ秒）として保存する。
     * 
     * @param task シリアライズするタスク
     * @return CSV形式の文字列
     */
    private String serializeTask(Task task) {
        // タスクの各フィールドをカンマ区切りで結合
        // 作成日時はlong値（ミリ秒）として保存
        return task.getId() + "," + task.getName() + "," + task.isDone() + ","
                + task.getCreatedAt().getTime();
    }

    /**
     * CSV形式の文字列からタスクをデシリアライズする
     * 
     * カンマ区切りの文字列を解析し、タスクオブジェクトを復元する。 古い形式（Date.toString()形式）の日付にも対応する。 パースに失敗した場合はnullを返す。
     * 
     * @param line CSV形式のタスクデータ
     * @return デシリアライズされたタスク、失敗時はnull
     */
    private Task deserializeTask(String line) {
        try {
            // カンマで分割（最大4つのフィールド）
            // タスク名にカンマが含まれる可能性があるため、4分割に制限
            String[] parts = line.split(",", 4);

            // 各フィールドをパース
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            boolean isDone = Boolean.parseBoolean(parts[2]);
            Date createdAt;

            // 作成日時のパース（2つの形式に対応）
            try {
                // 新形式：Unixタイムスタンプ（ミリ秒）
                long timestamp = Long.parseLong(parts[3]);
                createdAt = new Date(timestamp);
            } catch (NumberFormatException e) {
                // 旧形式：Date.toString()形式（後方互換性のため）
                SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                createdAt = format.parse(parts[3]);
            }

            // タスクオブジェクトを生成し、フィールドを設定
            Task task = new Task(id, name);
            task.setDone(isDone);
            task.setCreatedAt(createdAt);
            return task;
        } catch (Exception e) {
            // パースに失敗した場合はnullを返す
            // （呼び出し元で無視される）
            return null;
        }
    }
}
