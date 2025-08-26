import java.io.*;
import java.util.ArrayList;

public class Storage {
    private static final String FILE_PATH = "./data/burgerburglar.txt";

    public static TaskList load() {
        File file = new File(FILE_PATH);
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // create ./data folder if missing
                file.createNewFile();          // create burgerburglar.txt
                return new TaskList();
            }

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    try {
                        Task t = parseLine(line);
                        if (t != null) {
                            tasks.add(t);
                        }
                    } catch (Exception e) {
                        System.out.println("BURGER ERROR: Corrupted line skipped → " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("BURGER ERROR: Failed to load tasks.");
        }

        return new TaskList(tasks);
    }

    public static void save(TaskList list) {
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            for (Task task : list.getTasks()) {
                fw.write(task.serialize() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("BURGER ERROR: Could not save tasks.");
        }
    }

    private static Task parseLine(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");

        switch (type) {
            case "T":
                return new Todo(parts[2], isDone);
            case "D":
                return new Deadline(parts[2], parts[3], isDone);
            case "E":
                return new Event(parts[2], parts[3], parts.length > 4 ? parts[4] : "", isDone);
            default:
                return null;
        }
    }
}
