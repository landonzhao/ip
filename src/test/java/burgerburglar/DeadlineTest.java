package burgerburglar;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void toString_includesDescriptionAndDate() {
        LocalDateTime date = LocalDateTime.of(2025, 8, 27, 23, 59); // Example: 2025-08-27 23:59
        Deadline d = new Deadline("finish burger", date);
        String output = d.toString();

        assertTrue(output.contains("finish burger"));
        assertTrue(output.contains("Aug 27 2025")); // date part
    }

    @Test
    void markAsDone_setsTaskDone() {
        LocalDateTime date = LocalDateTime.of(2025, 8, 27, 23, 59);
        Deadline d = new Deadline("finish burger", date);

        assertFalse(d.isDone());

        d.markAsDone();
        assertTrue(d.isDone());
    }
}

