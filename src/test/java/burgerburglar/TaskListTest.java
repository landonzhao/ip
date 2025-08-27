package burgerburglar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void addTask_increasesSize() {
        Task t = new Todo("eat burger");
        taskList.addTask(t);
        assertEquals(1, taskList.size());
    }

    @Test
    void deleteTask_removesCorrectTask() {
        Task t1 = new Todo("eat burger");
        Task t2 = new Todo("steal fries");
        taskList.addTask(t1);
        taskList.addTask(t2);

        Task removed = taskList.deleteTask(1); // assuming 1-based index?
        assertEquals("eat burger", removed.getDescription());
        assertEquals(1, taskList.size());
    }

    @Test
    void deleteTask_invalidIndex_throwsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.deleteTask(99);
        });
    }
}
