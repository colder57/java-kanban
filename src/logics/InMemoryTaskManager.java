package logics;

import history.HistoryManager;
import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static logics.Managers.getDefaultHistory;

public class InMemoryTaskManager implements TaskManager {
    public int count = 0;
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();

    HistoryManager historyManager = getDefaultHistory();

    private int increaseCount() {
        count++;
        return count;
    }



    @Override
    public ArrayList<Task> getAllTasks(){
        return (ArrayList<Task>) tasks.values();
    }
    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public Task getTaskById(Integer managerId) {
        Task theTask = tasks.get(managerId);
        historyManager.add(theTask);
        return theTask;
    }

    @Override
    public void createTask(Task managerTask) {
        managerTask.setId(increaseCount());       // задаем id в поле Таска
        tasks.put(managerTask.getId(), managerTask);  // задаем тот же id в Мапе
    }

    @Override
    public void updateTask(Task managerTask) {
        if (tasks.containsKey(managerTask.getId())) {
            tasks.put(managerTask.getId(), managerTask);
        }
    }

    @Override
    public void deleteTaskById(Integer managerId) {
        tasks.remove(managerId);
    }

    // Методы для Эпиков HashMap<Integer, Model.Epic> epics = new HashMap<>();
    @Override

    public ArrayList<Epic> getAllEpics() {
        return (ArrayList<Epic>) epics.values();
    }

    @Override
    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    @Override
    public Epic getEpicById(Integer managerId) {
        Epic theEpic = epics.get(managerId);
        historyManager.add(theEpic);
        return theEpic;
    }

    @Override
    public void createEpic(Epic managerEpic) {
        managerEpic.setId(increaseCount());
        epics.put(managerEpic.getId(), managerEpic);
    }

    @Override
    public void updateEpic(Epic managerEpic) {
        if (epics.containsKey(managerEpic.getId())) {
            epics.put(managerEpic.getId(), managerEpic);
        } else {
            return;
        }
    }

    @Override
    public void deleteEpicById(Integer managerId) {
        Epic theEpic = epics.get(managerId);
        ArrayList<Integer> theEpicSubs = theEpic.getSubIds();
        for (int i : theEpicSubs) {
            subtasks.remove(i);
        }
        epics.remove(managerId);
    }

    @Override
    public ArrayList<Subtask> getSubByEpic(Integer managerId) {
        ArrayList<Integer> idsOfSub = (epics.get(managerId)).getSubIds();  //  Список id подзадач Эпика
        ArrayList<Subtask> subByEpic = new ArrayList<>();
        for (int ids : idsOfSub) {
            subByEpic.add(subtasks.get(ids));  // Добавляем в список нужные Model.SubTask
        }
        return subByEpic;
    }

    // Методы для подзадач
    @Override

    public ArrayList<Subtask> getAllSubTasks() {
        return (ArrayList<Subtask>) subtasks.values();
    }

    @Override
    public void deleteAllSubTasks() {
        ArrayList<Epic> theEpics = (ArrayList<Epic>) epics.values();
        for (Epic theEpic : theEpics) {
            ArrayList<Integer> subIds = theEpic.getSubIds();
            subIds.clear();
        }
        subtasks.clear();
    }

    @Override
    public Subtask getSubTaskById(Integer managerId) {
        Subtask theSubtask = subtasks.get(managerId);
        historyManager.add(theSubtask);
        return theSubtask;
    }

    @Override
    public void createSubTask(Subtask managerSubTask) {
        int i = increaseCount();
        managerSubTask.setId(i);
        int epicID = managerSubTask.getEpicId();
        subtasks.put(i, managerSubTask);
        managerSubTask.setEpicId(epicID);
        ((epics.get(epicID)).getSubIds()).add(i);
    }

    @Override
    public void updateSubTask(Subtask managerSubtask) {
        subtasks.put(managerSubtask.getId(), managerSubtask);
        updateEpicStatus(managerSubtask);
    }

    @Override
    public void deleteSubTaskById(Integer managerId) {
        int theEpicId = (subtasks.get(managerId)).getEpicId();
        ((epics.get(theEpicId)).getSubIds()).remove(managerId);
        subtasks.remove(managerId);
    }

    @Override
    public void updateEpicStatus(Subtask managerSubTask) {
        Epic theEpic = epics.get(managerSubTask.getEpicId());
        int countNew = 0;
        int countDone = 0;
        ArrayList<Integer> subs = theEpic.getSubIds();
        if (subs == null) {
            theEpic.setStatus(Status.NEW);
            return;
        }
        for (int i : subs) {
            if ((subtasks.get(i)).getStatus() == Status.NEW) {
                countNew += 1;
            } else if ((subtasks.get(i)).getStatus() == Status.DONE) {
                countDone += 1;
            } else {
                theEpic.setStatus(Status.IN_PROGRESS);
                return;
            }
        }
        if (countNew == subs.size()) {
            theEpic.setStatus(Status.NEW);
        } else {
            theEpic.setStatus(Status.DONE);
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }
}
