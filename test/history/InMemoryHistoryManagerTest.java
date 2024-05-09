package history;

import model.Task;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
    @Test
    void shouldBeFalseWhenHistoreManagerChangeTasks(){
        Task task1 = new Task("Name1", "Description1");
        task1.setId(13);
        historyManager.add(task1);
        List<Task> tasks = historyManager.getHistory();
        assertEquals(tasks.get(0).getName(), "Name1");
        assertEquals(tasks.get(0).getDescription(), "Description1");
    }
}