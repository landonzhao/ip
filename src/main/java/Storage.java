import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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

        try {
            switch (type) {
                case "T":
                    return new Todo(parts[2], isDone);

                case "D":
                    LocalDateTime deadline = null;
                    if (parts.length > 3 && !parts[3].isEmpty()) {
                        try {
                            deadline = LocalDateTime.parse(parts[3]);
                        } catch (DateTimeParseException e) {
                            System.out.println("BURGER ERROR: Invalid date in saved deadline, using unspecified.");
                        }
                    }
                    return new Deadline(parts[2], deadline, isDone);

                case "E":
                    LocalDateTime from = null;
                    LocalDateTime to = null;
                    if (parts.length > 3 && !parts[3].isEmpty()) {
                        try {
                            from = LocalDateTime.parse(parts[3]);
                        } catch (DateTimeParseException e) {
                            System.out.println("BURGER ERROR: Invalid start date in saved event, using unspecified.");
                        }
                    }
                    if (parts.length > 4 && !parts[4].isEmpty()) {
                        try {
                            to = LocalDateTime.parse(parts[4]);
                        } catch (DateTimeParseException e) {
                            System.out.println("BURGER ERROR: Invalid end date in saved event, using unspecified.");
                        }
                    }
                    return new Event(parts[2], from, to, isDone);

                default:
                    return null;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("BURGER ERROR: Malformed line skipped → " + line);
            return null;
        }
    }
}