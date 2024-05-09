package logics;
import model.Epic;
import model.Subtask;
import model.Task;


import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public interface TaskManager {



    // Методы для обычных задач:
    ArrayList<Task> getAllTasks();
    void deleteAllTasks();
    Task getTaskById(Integer managerId);
    void createTask(Task managerTask);
    void updateTask(Task managerTask);
    void deleteTaskById(Integer managerId);

    // Методы для Эпиков HashMap<Integer, Model.Epic> epics = new HashMap<>();

    ArrayList<Epic> getAllEpics();
    void deleteAllEpics();
    Epic getEpicById(Integer managerId);
    void createEpic(Epic managerEpic);
    void updateEpic(Epic managerEpic);
    void deleteEpicById(Integer managerId);
    ArrayList<Subtask> getSubByEpic(Integer managerId);

    // Методы для подзадач

    ArrayList<Subtask> getAllSubTasks();
    void deleteAllSubTasks();
    Subtask getSubTaskById(Integer managerId);
    void createSubTask(Subtask managerSubTask);
    void updateSubTask(Subtask managerSubtask);
    void deleteSubTaskById(Integer managerId);
    void updateEpicStatus(Subtask managerSubTask);
    List<Task> getHistory();

}
