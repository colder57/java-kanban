package history;

import model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    List<Task> lastTenRequests = new ArrayList<>();
    @Override
    public void add(Task task){
        if(lastTenRequests.size() == 10){
            lastTenRequests.removeFirst();
        }
        lastTenRequests.add(task);
    }
    @Override
    public List<Task> getHistory(){
        return lastTenRequests;
    }
}
