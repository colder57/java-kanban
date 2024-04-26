import java.util.HashMap;
import java.util.ArrayList;
public class TaskManager {
    public int count = 0;
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, SubTask> subTasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();

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
        tasks.put(count, managerTask);
        count++;
    }
    public void updateTask(Task managerTask){
        tasks.put(managerTask.getId(), managerTask);
    }
    public void deleteTaskById(Integer managerId){
        tasks.remove(managerId);
    }
    // Методы для Эпиков HashMap<Integer, Epic> epics = new HashMap<>();
    public ArrayList<Epic> getAllEpics(){
        return (ArrayList<Epic>) epics.values();
    }
    public void deleteAllEpics(){
        epics.clear();
        subTasks.clear();
    }
    public Epic getEpicById(Integer managerId){
        return epics.get(managerId);
    }
    public void createEpic(Epic managerEpic){
        epics.put(count, managerEpic);
        count++;
    }
    public void updateEpic(Epic managerEpic){
        epics.put(managerEpic.getId(), managerEpic);
    }
    public void deleteEpicById(Integer managerId){
        epics.remove(managerId);
    }
    public ArrayList<SubTask> getSubByEpic(Integer managerId){
        ArrayList<Integer> idsOfSub = (epics.get(managerId)).getSubIds();
    }
}
