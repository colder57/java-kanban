import logics.TaskManager;
import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
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
        System.out.println((manager.getTaskById(1)).toString());
        System.out.println((manager.getTaskById(1)).toString());
        System.out.println((manager.getSubTaskById(4)).toString());
        System.out.println((manager.getSubTaskById(5)).toString());
        System.out.println((manager.getEpicById(3)).toString());
        System.out.println(((manager.getSubTaskById(7))).toString());
        System.out.println((manager.getEpicById(6)).toString());
        manager.deleteEpicById(6);
     //   System.out.println((manager.getEpicById(3)).toString());            При раскоментировании появляется ошибка - тк обьект удален
     //   System.out.println(((manager.getSubTaskById(7))).toString());



    }
}
