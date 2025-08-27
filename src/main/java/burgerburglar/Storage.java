package burgerburglar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Handles the loading and saving of {@link TaskList} data to a local file.
 * <p>
 * Each task is serialized into a line in the file, and the Storage class is responsible
 * for reading that file and reconstructing {@link Task} objects, as well as writing
 * updated task data back to the file.
 */
public class Storage {
    private String FILE_PATH = "./data/burgerburglar.txt";

    /**
     * Constructs a Storage object that manages a specific file path.
     *
     * @param filePath the file path where tasks will be loaded from and saved to
     */
    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    /**
     * Loads the task list from the file specified in {@link #FILE_PATH}.
     * <p>
     * If the file or its parent directories do not exist, they will be created automatically,
     * and an empty {@link TaskList} will be returned.
     *
     * @return a {@link TaskList} containing tasks loaded from the file
     * @throws BurgerException if there is an I/O error while reading the file
     */
    public TaskList load() throws BurgerException {
        File file = new File(FILE_PATH);
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // create folder if missing
                file.createNewFile();          // create file
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
            throw new BurgerException("BURGER ERROR: Failed to load tasks from file.");
        }

        return new TaskList(tasks);
    }

    /**
     * Saves the given {@link TaskList} to the file specified in {@link #FILE_PATH}.
     * <p>
     * Each task is serialized into a line in the file.
     *
     * @param list the {@link TaskList} to save
     */
    public void save(TaskList list) {
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            for (Task task : list.getTasks()) {
                fw.write(task.serialize() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("BURGER ERROR: Could not save tasks.");
        }
    }

    /**
     * Parses a single line from the storage file into a {@link Task} object.
     *
     * @param line a line from the storage file
     * @return the {@link Task} object represented by the line, or {@code null} if invalid
     */
    private Task parseLine(String line) {
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