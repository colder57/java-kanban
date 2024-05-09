package history;

import logics.TaskManager;
import model.Epic;
import model.Subtask;
import model.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static logics.Managers.getDefault;
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
    @Test
    void getRightHistory(){
        TaskManager manager = getDefault();
        ArrayList<Integer> subs1 = new ArrayList<>();  // Список id подзадач Эпика1
        subs1.add(4);
        subs1.add(5);
        ArrayList<Integer> subs2 = new ArrayList<>();  // Список id подзадач Эпика2
        subs2.add(7);
        Task task1 = new Task("Уборка", "Убраться в доме");
        Task task2 = new Task("Стирка","Постирать белье");
        Epic epic1 = new Epic("Эпик1","Починить мебель");
        Subtask subtask1 = new Subtask("Подзадача1","Починить картину", 3);
        Subtask subtask2 = new Subtask("Подзадача2","Починить стул", 3);
        Epic epic2 = new Epic("Эпик2","Написать курсач");
        Subtask subtask3 = new Subtask("Подзадача1","Купить курсач", 6);
        manager.createTask(task1);
        manager.createTask(task2);
        manager.createEpic(epic1);
        manager.createSubTask(subtask1);
        manager.createSubTask(subtask2);
        manager.createEpic(epic2);
        manager.createSubTask(subtask3);
        Task task111 = manager.getTaskById(1);
        Task task222 = manager.getTaskById(2);
        Task task3 = manager.getSubTaskById(4);
        Task task4 = manager.getSubTaskById(5);
        Task task5 = manager.getEpicById(3);
        Task task6 = manager.getSubTaskById(7);
        Task task7 = manager.getEpicById(6);
        Task task8 = manager.getSubTaskById(4);
        Task task9 = manager.getSubTaskById(5);
        Task task10 = manager.getSubTaskById(4);
        Task task11 = manager.getSubTaskById(5);
        Task task12 = manager.getSubTaskById(4);
        Task task13 = manager.getSubTaskById(5);
        Task task14 = manager.getSubTaskById(7);
        List<Task> history = manager.getHistory();
        assertEquals(history.get(9), task14);
        assertEquals(history.get(0), task5);

    }
}