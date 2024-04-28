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
        managerTask.id = count;
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
        managerEpic.id = count;
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
        ArrayList<Integer> idsOfSub = (epics.get(managerId)).getSubIds();  //  Список id подзадач Эпика
        ArrayList<SubTask> subByEpic = new ArrayList<>();
        for(int ids : idsOfSub){
            subByEpic.add(subTasks.get(ids));  // Добавляем в список нужные SubTask
        }
        return subByEpic;
    }

    // Методы для подзадач

    public ArrayList<SubTask> getAllSubTasks(){
        return (ArrayList<SubTask>) subTasks.values();
    }
    public void deleteAllSubTasks(){
        subTasks.clear();
    }
    public SubTask getSubTaskById(Integer managerId){
        return subTasks.get(managerId);
    }
    public void createSubTask(SubTask managerSubTask, int epicId){
        managerSubTask.id = count;
        subTasks.put(count, managerSubTask);
        managerSubTask.setEpicId(epicId);
        ((epics.get(epicId)).getSubIds()).add(count);
        count++;
    }
    public void updateSubTask(SubTask managerSubTask){
        subTasks.put(managerSubTask.getId(), managerSubTask);
        Epic theEpic = epics.get(managerSubTask.getEpicId());
        boolean flag = true;
        int countNew = 0;
        int countDone = 0;
        ArrayList<Integer> subs = theEpic.getSubIds();
        if (subs == null) {
            theEpic.setStatus(Status.NEW);
            return;
        }
        for (int i : subs) {
            if ( (subTasks.get(i)).getStatus() == Status.NEW ) {
                countNew += 1;
            } else if ((subTasks.get(i)).getStatus() == Status.DONE ) {
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
    public void deleteSubTaskById(Integer managerId){
        subTasks.remove(managerId);
    }

}
