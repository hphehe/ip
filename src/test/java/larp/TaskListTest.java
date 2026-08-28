package larp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests task collection operations performed by {@link TaskList}.
 */
public class TaskListTest {
    @Test
    public void find_matchingKeyword_returnsMatchingTasksInOriginalOrder() {
        TaskList tasks = new TaskList(List.of(
                new Todo("read book"),
                new Event("project meeting", "2pm", "3pm"),
                new Deadline("return book", LocalDate.of(2026, 9, 1))));

        TaskList matchingTasks = tasks.find("book");

        assertEquals(2, matchingTasks.size());
        assertEquals("read book", matchingTasks.get(0).getDescription());
        assertEquals("return book", matchingTasks.get(1).getDescription());
    }

    @Test
    public void find_noMatchingKeyword_returnsEmptyTaskList() {
        TaskList tasks = new TaskList(List.of(
                new Todo("read book"),
                new Event("project meeting", "2pm", "3pm")));

        TaskList matchingTasks = tasks.find("exercise");

        assertTrue(matchingTasks.isEmpty());
    }
}
