package logics;

import model.Epic;
import model.Subtask;
import model.Task;
import org.junit.jupiter.api.Test;

import static logics.Managers.getDefault;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    InMemoryTaskManager manager = (InMemoryTaskManager) getDefault();
    @Test
    void shouldBeFalseWhenTaskManagerNotAddTasks() {
        Task task1 = new Task("Name1", "Description1");
        manager.createTask(task1);
        assertNotNull(manager.getTaskById(1));
    }
    @Test
    void shouldBeFalseWhenTaskManagerNotAddSubs() {
        Epic epic1 = new Epic("name1", "des1");
        manager.createEpic(epic1);
        Subtask subtask1 = new Subtask("Name1", "Description1", 1);
        manager.createSubTask(subtask1);
        assertNotNull(manager.getSubTaskById(2));
    }
    @Test
    void shouldBeFalseWhenTaskManagerNotAddEpics() {
        Epic epic1 = new Epic("name1", "des1");
        manager.createEpic(epic1);
        assertNotNull(manager.getEpicById(1));
    }
    @Test
    void shouldBeFalseWhenTaskChange(){
        Task task1 = new Task("Name1", "Description1");
        manager.createTask(task1);
        assertEquals(task1.getName(), "Name1");
        assertEquals(task1.getDescription(), "Description1");
    }
    @Test
    void shouldBeFalseWhenIdsNotCoincide(){
        InMemoryTaskManager manager = (InMemoryTaskManager) getDefault();
        Task task1 = new Task("Name1", "Description1");
        task1.setId(13);
        Task task2 = new Task("Name2", "Description2");
        task2.setId(13);
        manager.createTask(task1);
        manager.createTask(task2);
        Task newTask1 = manager.getTaskById(1);
        Task newTask2 = manager.getTaskById(2);
        assertNotEquals(newTask1.getId(), newTask2.getId());
    }
}