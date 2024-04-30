package Logics;
import Model.Epic;
import Model.Subtask;
import Model.Task;
import Model.Status;

import java.util.HashMap;
import java.util.ArrayList;
public class TaskManager {
    public int count = 0;
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();

    private int increaseCount(){
        count++;
        return count;
    }

    // Методы для обычных задач:
    public ArrayList<Task> getAllTasks(){
        return (ArrayList<Task>) tasks.values();
    }
    public void deleteAllTasks(){
        tasks.clear();
    }
    public Task getTaskById(Integer managerId){
        return tasks.get(managerId);
    }
    public void createTask(Task managerTask){
        int i = increaseCount();
        managerTask.id = i;
        tasks.put(i, managerTask);
    }
    public void updateTask(Task managerTask){
        if(tasks.containsKey(managerTask.getId())) {
            tasks.put(managerTask.getId(), managerTask);
        } else {
            return;
        }
    }
    public void deleteTaskById(Integer managerId){
        tasks.remove(managerId);
    }

    // Методы для Эпиков HashMap<Integer, Model.Epic> epics = new HashMap<>();

    public ArrayList<Epic> getAllEpics(){
        return (ArrayList<Epic>) epics.values();
    }
    public void deleteAllEpics(){
        epics.clear();
        subtasks.clear();
    }
    public Epic getEpicById(Integer managerId){
        return epics.get(managerId);
    }
    public void createEpic(Epic managerEpic){
        int i = increaseCount();
        managerEpic.id = i;
        epics.put(i, managerEpic);
    }
    public void updateEpic(Epic managerEpic){
        if(epics.containsKey(managerEpic.getId())) {
            epics.put(managerEpic.getId(), managerEpic);
        } else {
            return;
        }
    }
    public void deleteEpicById(Integer managerId){
        Epic theEpic = epics.get(managerId);
        ArrayList<Integer> theEpicSubs = theEpic.getSubIds();
        for(int i : theEpicSubs){
            subtasks.remove(i);
        }
        epics.remove(managerId);
    }
    public ArrayList<Subtask> getSubByEpic(Integer managerId){
        ArrayList<Integer> idsOfSub = (epics.get(managerId)).getSubIds();  //  Список id подзадач Эпика
        ArrayList<Subtask> subByEpic = new ArrayList<>();
        for(int ids : idsOfSub){
            subByEpic.add(subtasks.get(ids));  // Добавляем в список нужные Model.SubTask
        }
        return subByEpic;
    }

    // Методы для подзадач

    public ArrayList<Subtask> getAllSubTasks(){
        return (ArrayList<Subtask>) subtasks.values();
    }
    public void deleteAllSubTasks(){
        ArrayList<Epic> theEpics = (ArrayList<Epic>) epics.values();
        for(Epic theEpic : theEpics){
            (theEpic.getSubIds()).clear();
        }
        subtasks.clear();
    }
    public Subtask getSubTaskById(Integer managerId){
        return subtasks.get(managerId);
    }
    public void createSubTask(Subtask managerSubTask){
        int i = increaseCount();
        managerSubTask.id = i;
        int epicID = managerSubTask.getEpicId();
        subtasks.put(i, managerSubTask);
        managerSubTask.setEpicId(epicID);
        ((epics.get(epicID)).getSubIds()).add(i);
    }
    public void updateSubTask(Subtask managerSubtask){
        subtasks.put(managerSubtask.getId(), managerSubtask);
        updateEpicStatus(managerSubtask);
    }
    public void deleteSubTaskById(Integer managerId){
        int theEpicId = (subtasks.get(managerId)).getEpicId();
        ((epics.get(theEpicId)).getSubIds()).remove(managerId);
        subtasks.remove(managerId);
    }

    public void updateEpicStatus(Subtask managerSubTask){
        Epic theEpic = epics.get(managerSubTask.getEpicId());
        int countNew = 0;
        int countDone = 0;
        ArrayList<Integer> subs = theEpic.getSubIds();
        if (subs == null) {
            theEpic.setStatus(Status.NEW);
            return;
        }
        for (int i : subs) {
            if ( (subtasks.get(i)).getStatus() == Status.NEW ) {
                countNew += 1;
            } else if ((subtasks.get(i)).getStatus() == Status.DONE ) {
                countDone += 1;
            } else {
                theEpic.setStatus(Status.IN_PROGRESS);
                return;
            }
        }
        if(countNew == subs.size()){
            theEpic.setStatus(Status.NEW);
        } else {
            theEpic.setStatus(Status.DONE);
        }
    }

}
