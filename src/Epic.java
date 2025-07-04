import java.util.ArrayList;

public class Epic extends Task {
    private final ArrayList<Integer> subtasks = new ArrayList<>(3);

    public Epic(String title, String description) {
        super(title, description);
    }

    public Epic(int id, String title, String description) {
        super(title, description);
        this.setId(id);
    }

    public ArrayList<Integer> getSubtasks() {
        return subtasks;
    }


    @Override
    public String toString() {
        return "Epic{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", subtasks=" + subtasks +
                ", status=" + getStatus() +
                '}';
    }
}
