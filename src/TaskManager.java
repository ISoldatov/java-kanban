import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    private int countId;
    private final HashMap<Integer, Task> taskMap = new HashMap<>();
    private final HashMap<Integer, Epic> epicMap = new HashMap<>();
    private final HashMap<Integer, Subtask> subtaskMap = new HashMap<>();

    public TaskManager() {
        this.countId = 0;
    }

    public Task addTask(Task task) {
        setTaskId(task);
        return taskMap.computeIfAbsent(task.getId(), (b) -> task);
    }

    public Task updateTask(Task task) {
        return taskMap.computeIfPresent(task.getId(), (a, b) -> task);
    }

    public Task getTask(Integer taskId) {
        return taskMap.get(taskId);
    }

    public List<Task> getAllTasks() {
        return taskMap.values().stream().toList();
    }

    public Task removeTask(Integer taskId) {
        return taskMap.remove(taskId);
    }

    public void removeAllTask() {
        taskMap.clear();
    }
    //----------------------------------------------------------------------------------------------------------------------

    public Subtask addSubtask(Subtask subtask) {
        setTaskId(subtask);
        epicMap.get(subtask.getEpicId()).getSubtasks().add(subtask.getId());
        subtaskMap.computeIfAbsent(subtask.getId(), b -> subtask);
        updateEpicStatus(subtask.getEpicId());
        return subtask;
    }

    public Subtask updateSubtask(Subtask subtask) {
        subtaskMap.computeIfPresent(subtask.getId(), (a, b) -> subtask);
        updateEpicStatus(subtask.getEpicId());
        return subtask;
    }

    public Subtask getSubtask(int id) {
        return subtaskMap.get(id);
    }

    public List<Subtask> getAllSubtask() {
        return subtaskMap.values().stream().toList();
    }

    public Subtask removeSubtask(int id) {
        Subtask subtask = subtaskMap.remove(id);
        List<Integer> epicSubtasks = epicMap.get(subtask.getEpicId()).getSubtasks();
        epicSubtasks.remove((Integer) id);
        updateEpicStatus(subtask.getEpicId());
        return subtask;
    }

    public void removeAllSubtask() {
        subtaskMap.clear();
        for (Epic epic : epicMap.values()) {
            epic.getSubtasks().clear();
            epic.setStatus(Status.NEW);
        }
    }
    //----------------------------------------------------------------------------------------------------------------------
    public Epic addEpic(Epic epic) {
        setTaskId(epic);
        return epicMap.computeIfAbsent(epic.getId(), b -> epic);
    }

    public Epic updateEpic(Epic epic) {
        return epicMap.computeIfPresent(epic.getId(), (a, b) -> epic);
    }

    public Epic getEpic(Integer epicId) {
        return epicMap.get(epicId);
    }

    public List<Subtask> getEpicSubtasks(int epicId) {
        List<Integer> subtasksId = epicMap.get(epicId).getSubtasks();
        List<Subtask> subtasks = new ArrayList<>();
        for (int id : subtasksId) {
            subtasks.add(subtaskMap.get(id));
        }
        return subtasks;
    }

    public List<Epic> getAllEpic() {
        return epicMap.values().stream().toList();
    }

    public Epic removeEpic(int epicId) {
        List<Integer> subtasksId = epicMap.get(epicId).getSubtasks();
        for (int id : subtasksId) {
            subtaskMap.remove(id);
        }
        return epicMap.remove(epicId);
    }

    public void removeAllEpic() {
        subtaskMap.clear();
        epicMap.clear();
    }
    //----------------------------------------------------------------------------------------------------------------------
    private void setTaskId(Task task) {
        if (task.getId() == null) {
            task.setId(++countId);
        }
    }

    private void updateEpicStatus(int epicId) {
        Epic epic = epicMap.get(epicId);
        ArrayList<Integer> epicSubtasksId = epic.getSubtasks();
        ArrayList<Status> epicSubtasksStatus = new ArrayList<>();
        for (Integer id : epicSubtasksId) {
            epicSubtasksStatus.add(subtaskMap.get(id).getStatus());
        }

        if (epicSubtasksStatus.isEmpty() || (epicSubtasksStatus.contains(Status.NEW) && !epicSubtasksStatus.contains(Status.IN_PROGRESS)
                && !epicSubtasksStatus.contains(Status.DONE))) {
            epic.setStatus(Status.NEW);
        } else if (epicSubtasksStatus.contains(Status.DONE) && !epicSubtasksStatus.contains(Status.NEW)
                && !epicSubtasksStatus.contains(Status.IN_PROGRESS)) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }
}
