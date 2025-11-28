package at1.ui;

import at1.domain.Task;
import at1.domain.TaskRepository;
import at1.service.TaskService;
import at1.util.ConsoleColors;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * コマンド入力を処理するハンドラークラス
 */
public class CommandHandler {
    /** タスク操作のためのリポジトリ */
    private TaskRepository repository;

    /** ファイル操作のためのサービス */
    private TaskService service;

    /** ユーザー入力を読み取るスキャナー */
    private Scanner scanner;

    /**
     * コンストラクタ
     * 
     * @param repository タスクリポジトリ
     * @param service タスクサービス
     */
    public CommandHandler(TaskRepository repository, TaskService service) {
        this.repository = repository;
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    /**
     * コマンドループを実行する
     */
    public void run() {
        // exitコマンドが入力されるまで無限ループ
        while (true) {
            // [UX向上] プロンプトを青色・太字で表示
            System.out.print(ConsoleColors.prompt("TODO> "));

            // ユーザー入力を読み取り、前後の空白を除去
            String input = scanner.nextLine().trim();

            // 空入力の場合は次のループへ
            if (input.isEmpty())
                continue;

            // 入力をコマンドと引数に分割（最大2つに分割）
            String[] parts = input.split(" ", 2);
            String command = parts[0];

            try {
                // コマンドに応じて処理を分岐
                if (command.equals("exit")) {
                    // exit: ループを抜けてアプリケーション終了
                    break;
                } else if (command.equals("post") && parts.length == 2) {
                    // post <TODO>: 新規タスク追加
                    handlePost(parts[1]);
                } else if (command.equals("done") && parts.length == 2) {
                    // done <id>: タスク完了
                    handleDone(parts[1]);
                } else if (command.equals("show")) {
                    // show [options]: タスク一覧表示
                    String options = parts.length > 1 ? parts[1] : "";
                    handleShow(options);
                } else if (command.equals("delete") && parts.length == 2) {
                    // delete <id>: タスク削除
                    handleDelete(parts[1]);
                } else if (command.equals("save")) {
                    // save: ファイルに保存
                    handleSave();
                } else if (command.equals("load")) {
                    // load: ファイルから読み込み
                    handleLoad();
                } else if (command.equals("help")) {
                    // help: ヘルプメッセージ表示
                    handleHelp();
                } else {
                    // 不明なコマンド
                    System.out.println("Command: " + command + " not found.");
                }
            } catch (Exception e) {
                // 例外発生時はエラーハンドラーに委譲
                handleError(e);
            }
        }
    }

    /**
     * postコマンドを処理する
     * 
     * 新しいタスクをリポジトリに追加し、成功メッセージを表示する。
     * 
     * @param name タスクの名前（内容）
     */
    private void handlePost(String name) {
        // リポジトリに新しいタスクを追加
        Task addedTask = repository.add(name);
        // 成功メッセージを緑色のチェックマーク付きで表示
        System.out.println(ConsoleColors.success("✓") + " Added task #" + addedTask.getId() + " "
                + addedTask.getName());
    }

    /**
     * doneコマンドを処理する
     * 
     * 指定されたIDのタスクを完了状態にする。 IDが数値でない場合や、タスクが存在しない場合は 適切なエラーメッセージを表示する。
     * 
     * @param idStr タスクIDの文字列
     */
    private void handleDone(String idStr) {
        try {
            // 文字列のIDを整数にパース
            int id = Integer.parseInt(idStr);

            // リポジトリでタスクを完了状態に更新
            repository.markAsDone(id);
        } catch (NumberFormatException e) {
            // IDが数値でない場合のエラー
            System.out.println(ConsoleColors.error("Error: ") + "Invalid ID " + idStr
                    + ". ID must be a number.");
        } catch (IllegalArgumentException e) {
            // タスクが存在しない場合のエラー
            System.out.println(ConsoleColors.error("Error: ") + e.getMessage()
                    + " Use 'show' to see available tasks.");
        }

    }

    /**
     * showコマンドを処理する
     * 
     * タスク一覧を表示する。オプションに応じてフィルタリングが可能。 - オプションなし: 全タスクを表示 - --done: 完了済みタスクのみ表示 - --todo: 未完了タスクのみ表示
     * 
     * 表示形式: [チェックボックス] ID: タスク名 DONE!
     * 
     * @param options フィルタリングオプション
     */
    private void handleShow(String options) {
        // リポジトリから全タスクを取得
        List<Task> allTasks = repository.findAll();
        List<Task> tasks;

        // オプションに基づいてタスクをフィルタリング
        if (options.contains("--done")) {
            // 完了済みタスクのみ抽出
            tasks = allTasks.stream().filter(Task::isDone)
                    .collect(java.util.stream.Collectors.toList());
        } else if (options.contains("--todo")) {
            // 未完了タスクのみ抽出
            tasks = allTasks.stream().filter(task -> !task.isDone())
                    .collect(java.util.stream.Collectors.toList());
        } else {
            // フィルタリングなし（全タスク）
            tasks = allTasks;
        }

        // ヘッダー部分：統計情報をカラフルに表示
        String headerText = ConsoleColors.colorize("Tasks: " + tasks.size() + " total, ",
                ConsoleColors.BOLD + ConsoleColors.CYAN)
                + ConsoleColors.colorize(repository.calculateDoneCount() + " done, ",
                        ConsoleColors.GREEN + ConsoleColors.BOLD)
                + ConsoleColors.colorize(repository.calculateTodoCount() + " todo\n",
                        ConsoleColors.BOLD + ConsoleColors.YELLOW);
        System.out.println(headerText);

        // 各タスクを1行ずつ表示
        for (Task task : tasks) {
            // チェックボックス：完了なら[✓]、未完了なら[ ]
            String checkBox = task.isDone() ? ConsoleColors.success("[✓]") : "[ ]";

            // フォーマット：[✓] ID: タスク名 DONE!
            System.out.println(checkBox + " "
                    + ConsoleColors.colorize(task.getId() + ":", ConsoleColors.BLUE) + " "
                    + task.getName() + " " + (task.isDone() ? ConsoleColors.success("DONE!") : ""));
        }
    }

    /**
     * deleteコマンドを処理する
     * 
     * 指定されたIDのタスクを削除する。 誤削除を防ぐため、削除前に確認プロンプトを表示する。 ユーザーが'y'または'yes'を入力した場合のみ削除を実行する。
     * 
     * @param idStr タスクIDの文字列
     */
    private void handleDelete(String idStr) {

        try {
            // 文字列のIDを整数にパース
            int id = Integer.parseInt(idStr);

            // タスクの存在確認（存在しない場合は例外がスローされる）
            Task task = repository.findById(id);

            // 削除確認プロンプトをタスク内容と共に表示
            System.out.print(ConsoleColors.prompt("Delete task #" + id + ": \"") + task.getName()
                    + ConsoleColors.prompt("\"? (y/N): "));

            // ユーザーの確認入力を読み取る
            String confirmation = scanner.nextLine().trim().toLowerCase();

            // 'y'または'yes'の場合のみ削除を実行
            if (confirmation.equals("y") || confirmation.equals("yes")) {
                repository.delete(id);
                System.out.println(ConsoleColors.success("✓") + " Deleted task #" + id);
            } else {
                // それ以外の入力の場合は削除をキャンセル
                System.out.println(ConsoleColors.warning("Cancelled deletion."));
            }
        } catch (NumberFormatException e) {
            // IDが数値でない場合のエラー
            System.out.println(ConsoleColors.error("Error: ") + "Invalid ID " + idStr
                    + ". ID must be a number.");
        } catch (IllegalArgumentException e) {
            // タスクが存在しない場合のエラー
            System.out.println(ConsoleColors.error("Error: ") + e.getMessage()
                    + " Use 'show' to see available tasks.");
        }

    }

    /**
     * saveコマンドを処理する
     * 
     * 現在のタスクリストをファイル（todo.txt）に保存する。
     * 
     * @throws IOException ファイル書き込みに失敗した場合
     */
    private void handleSave() throws IOException {
        // サービス層に委譲してファイルに保存
        service.save();
        // 成功メッセージを表示
        System.out.println(ConsoleColors.success("✓") + " Saved tasks to file.");
    }

    /**
     * loadコマンドを処理する
     * 
     * ファイル（todo.txt）からタスクリストを読み込み、 現在のタスクリストを置き換える。
     * 
     * @throws IOException ファイル読み込みに失敗した場合
     */
    private void handleLoad() throws IOException {
        // サービス層に委譲してファイルから読み込み
        service.load();
        // 読み込んだタスク数と共に成功メッセージを表示
        System.out.println(ConsoleColors.success("✓") + " Loaded " + repository.getTaskSize()
                + " tasks from file.");
    }

    /**
     * エラーを処理する
     * 
     * 予期しない例外が発生した場合にエラーメッセージを表示する。
     * 
     * @param e 発生した例外
     */
    private void handleError(Exception e) {
        // エラーメッセージを標準エラー出力に表示
        System.err.println("Error: " + e.getMessage());
    }

    /**
     * helpコマンドを処理する
     * 
     * 利用可能な全コマンドの一覧と使用方法を表示する。 初めて使用するユーザーや、コマンドを忘れた場合に役立つ。
     */
    private void handleHelp() {
        System.out.println("Available commands: ");
        System.out.println("  post <text>      Add a new task");
        System.out.println("  done <id>        Mark a task as done");
        System.out.println("  show             Show all tasks");
        System.out.println("  show --done      Show only completed tasks");
        System.out.println("  show --todo      Show only incomplete tasks");
        System.out.println("  delete <id>      Delete a task");
        System.out.println("  save             Save tasks to file");
        System.out.println("  load             Load tasks from file");
        System.out.println("  help             Show this help message");
        System.out.println("  exit             Quit application");
    }
}


