import java.util.ArrayList;

public class Epic extends Task {
    private final ArrayList<Subtask> subtasks = new ArrayList<>(3);

    public Epic(int id, String title, String description, Status status) {
        super(id, title, description, status);
    }
}
