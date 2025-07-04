public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        System.out.println("Добавление первой таски:");
        Task task_1 = new Task("Task-1", "Description task-1");
        taskManager.addTask(task_1);
        System.out.println(taskManager.getTask(1));
        System.out.println("Обновление первой таски:");
        Task task_1_Update = new Task(1, "Task-1-update", "Description task-1-update", Status.IN_PROGRESS);
        taskManager.updateTask(task_1_Update);
        System.out.println(taskManager.getTask(1));
        System.out.println("Обновление таски которой нет в мапе:");
        Task task_2 = new Task("Task-2", "Description task-2");
        System.out.println(taskManager.updateTask(task_2));
        System.out.println("Добавление второй таски:");
        taskManager.addTask(task_2);
        System.out.println(taskManager.getTask(2));
        System.out.println("Получение всех тасков:");
        System.out.println(taskManager.getAllTasks());
        System.out.println();

        System.out.println("Заведение нового эпика:");
        Epic epic_1 = new Epic("Epic-1", "Description epic-1");
        taskManager.addEpic(epic_1);
        System.out.println(taskManager.getEpic(3));
        System.out.println();

        System.out.println("Добавление сабтаски:");
        Subtask subtask_1 = new Subtask("Subtask-1", "Description subtask-1", 3);
        taskManager.addSubtask(subtask_1);
        System.out.println(taskManager.getSubtask(4));
        System.out.println(taskManager.getEpic(3));

        System.out.println("Добавление второй сабтаски:");
        Subtask subtask_2 = new Subtask("Subtask-2", "Description subtask-2", 3);
        taskManager.addSubtask(subtask_2);
        System.out.println(taskManager.getSubtask(5));
        System.out.println(taskManager.getEpic(3));
        System.out.println();

        System.out.println("Обновление первой сабтаски в статус IN_PROGRESS:");
        Subtask subtask_1_update_1 = new Subtask(4,"Subtask-1", "Description subtask-1",Status.IN_PROGRESS, 3);
        taskManager.updateSubtask(subtask_1_update_1);
        System.out.println(taskManager.getEpic(3));
        System.out.println("Обновление второй сабтаски в первом epic в статус DONE:");
        Subtask subtask_2_update_2 = new Subtask(5,"Subtask-2", "Description subtask-2",Status.DONE, 3);
        taskManager.updateSubtask(subtask_2_update_2);
        System.out.println(taskManager.getEpic(3));
        System.out.println("Обновление первой сабтаски в первом epic в статус DONE:");
        Subtask subtask_1_update_2 = new Subtask(4,"Subtask-1", "Description subtask-1",Status.DONE, 3);
        taskManager.updateSubtask(subtask_1_update_2);
        System.out.println(taskManager.getEpic(3));
        System.out.println();

        System.out.println(taskManager.getSubtask(4));
        System.out.println(taskManager.getSubtask(5));
        System.out.println(taskManager.getEpic(3));
        System.out.println();

        System.out.println("Заведение второго эпика:");
        Epic epic_2 = new Epic("Epic-2", "Description epic-2");
        taskManager.addEpic(epic_2);
        System.out.println(taskManager.getEpic(6));
        System.out.println();

        System.out.println("Получение всех subtask первого эпика:");
        System.out.println(taskManager.getEpicSubtasks(3));
        System.out.println();

        System.out.println("Добавление первой subtask во второй epic:");
        Subtask subtask_3 = new Subtask("Subtask-3", "Description subtask-3", 6);
        taskManager.addSubtask(subtask_3);
        System.out.println(taskManager.getEpic(6));
        System.out.println("Обновление первой сабтаски во втором epic в статус IN_PROGRESS:");
        Subtask subtask_3_update_1 = new Subtask(7,"Subtask-3", "Description subtask-3",Status.IN_PROGRESS, 6);
        taskManager.updateSubtask(subtask_3_update_1);
        System.out.println(taskManager.getEpic(6));
        System.out.println();

        System.out.println("Удаление первой subtask во втором epic:");
        taskManager.removeSubtask(7);
        System.out.println(taskManager.getEpic(6));
        System.out.println();

        System.out.println("Удаление второй task:");
        taskManager.removeTask(2);
        System.out.println(taskManager.getAllTasks());

        System.out.println("Удаление первого epic:");
        taskManager.removeEpic(3);
        System.out.println(taskManager.getAllEpic());
        System.out.println(taskManager.getAllSubtask());
    }
}
