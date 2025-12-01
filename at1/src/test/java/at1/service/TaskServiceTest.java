package at1.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at1.domain.Task;
import at1.domain.TaskRepository;
import at1.infrastructure.FileTaskStorage;
import at1.infrastructure.InMemoryTaskRepository;

public class TaskServiceTest {
    
    private TaskRepository repository;
    private FileTaskStorage storage;
    private TaskService service;
    private static final String TEST_FILE = "todo.txt";

    @Before
    public void setUp() {
        repository = new InMemoryTaskRepository();
        storage = new FileTaskStorage();
        service = new TaskService(repository, storage);
        // テスト前に既存のファイルを削除
        deleteTestFile();
    }

    @After
    public void tearDown() {
        // テスト後にファイルを削除
        deleteTestFile();
    }

    private void deleteTestFile() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testSave() throws IOException {
        repository.add("Task 1");
        repository.add("Task 2");
        repository.add("Task 3");
        
        service.save();
        
        // ファイルが作成されていることを確認
        File file = new File(TEST_FILE);
        assertEquals(true, file.exists());
    }

    @Test
    public void testSaveEmpty() throws IOException {
        service.save();
        
        // 空のリストでもファイルが作成されることを確認
        File file = new File(TEST_FILE);
        assertEquals(true, file.exists());
    }

    @Test
    public void testLoad() throws IOException {
        // まずタスクを追加して保存
        repository.add("Task 1");
        repository.add("Task 2");
        service.save();
        
        // リポジトリをクリア
        repository.clear();
        assertEquals(0, repository.findAll().size());
        
        // ファイルから読み込み
        service.load();
        
        // タスクが復元されていることを確認
        List<Task> tasks = repository.findAll();
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getName());
        assertEquals("Task 2", tasks.get(1).getName());
    }

    @Test
    public void testLoadEmptyFile() throws IOException {
        // ファイルが存在しない場合でも例外が発生しないことを確認
        service.load();
        
        List<Task> tasks = repository.findAll();
        assertEquals(0, tasks.size());
    }

    @Test
    public void testSaveAndLoad() throws IOException {
        // タスクを追加
        Task task1 = repository.add("Task 1");
        Task task2 = repository.add("Task 2");
        repository.markAsDone(1);
        
        // 保存
        service.save();
        
        // 新しいリポジトリで読み込み
        TaskRepository newRepository = new InMemoryTaskRepository();
        TaskService newService = new TaskService(newRepository, storage);
        newService.load();
        
        // データが正しく復元されていることを確認
        List<Task> tasks = newRepository.findAll();
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getName());
        assertEquals(true, tasks.get(0).isDone());
        assertEquals("Task 2", tasks.get(1).getName());
        assertEquals(false, tasks.get(1).isDone());
    }
}

