package burgerburglar;

import java.util.ArrayList;

/**
 * Represents a command to find tasks containing a specific keyword in their description.
 * <p>
 * When executed, this command searches through a TaskList for tasks whose description
 * contains the given keyword (case-insensitive), and displays the matching tasks using the UI.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task t : tasks.getTasks()) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(t);
            }
        }

        ui.showLine();
        if (matches.isEmpty()) {
            System.out.println("BURGER FOUND NO MATCH FOR: " + keyword);
        } else {
            System.out.println("BURGER FOUND THESE TASKS: ");
            for (int i = 0; i < matches.size(); i++) {
                System.out.println((i + 1) + "." + matches.get(i));
            }
        }
        ui.showLine();
    }
}
