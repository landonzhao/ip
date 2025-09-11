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
        assert keyword != null && !keyword.isBlank() : "Keyword must not be null or blank";
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        ArrayList<Task> matches = new ArrayList<>();
        for (Task t : tasks.getTasks()) {
            assert t != null : "Task in TaskList should not be null";
            assert t.getDescription() != null : "Task description should not be null";
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(t);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        if (matches.isEmpty()) {
            sb.append("BURGER FOUND NO MATCH FOR: ").append(keyword).append("\n");
        } else {
            sb.append("BURGER FOUND THESE TASKS:\n");
            for (int i = 0; i < matches.size(); i++) {
                assert matches.get(i) != null : "Matched task should not be null";
                sb.append(i + 1).append(". ").append(matches.get(i)).append("\n");
            }
        }
        sb.append("\n");
        return sb.toString();
    }
}
