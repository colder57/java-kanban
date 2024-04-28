import java.util.ArrayList;

public class Epic extends Task {
    public ArrayList<Integer> subIds = new ArrayList<>();

    public Epic(String name, String description, int id, Status status, ArrayList<Integer> subIds) {
        super(name, description, id, status);
        this.subIds = subIds;
    }

    public ArrayList<Integer> getSubIds() {
        return subIds;
    }
}
