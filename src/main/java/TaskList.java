import java.util.ArrayList;

public class TaskList {
    private static String lineBreak = "______________________________________________________________________\n";
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.print(lineBreak);
        System.out.println("YOU BETTER FINISH THAT SOON. BURGERS.");
        System.out.println("ADDED: " + task);
        System.out.println("NOW YOU HAVE " + tasks.size() + " TASKS.");
        System.out.println(lineBreak);
    }

    public Task markTask(int index, boolean isDone) {
        Task task = tasks.get(index);
        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }
        return task;
    }

    public Task deleteTask(int index) {
        Task removed = tasks.remove(index);
        System.out.println("______________________________________________________________________");
        System.out.println("BURGER HAS REMOVED THIS TASK:");
        System.out.println("  " + removed);
        System.out.println("NOW YOU HAVE " + tasks.size() + " TASKS IN THE LIST.");
        System.out.println("______________________________________________________________________");
        return removed;
    }

    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("______________________________________________________________________\n");
        if (tasks.isEmpty()) {
            sb.append("ALL DONE. BURGER!\n");
        } else {
            sb.append("YOU SHOULD GET STARTED SOON:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
        }
        sb.append("______________________________________________________________________");
        return sb.toString();
    }
}

