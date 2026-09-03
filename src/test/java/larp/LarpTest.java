package larp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class LarpTest {
    @TempDir
    private Path temporaryDirectory;

    @Test
    void getResponse_addAndListTask_returnsUpdatedTaskList() {
        Larp larp = new Larp(temporaryDirectory.resolve("tasks.txt").toString());

        String addResponse = larp.getResponse("todo read book");
        String listResponse = larp.getResponse("list");

        assertTrue(addResponse.contains("read book"));
        assertEquals("Here are the tasks in your list:\n1.[T][ ] read book", listResponse);
    }

    @Test
    void getResponse_emptyCommand_returnsErrorMessage() {
        Larp larp = new Larp(temporaryDirectory.resolve("tasks.txt").toString());

        assertEquals("OOPS!!! Please enter a command.", larp.getResponse("   "));
    }

    @Test
    void isExitCommand_onlyExactByeCommand_returnsTrue() {
        Larp larp = new Larp(temporaryDirectory.resolve("tasks.txt").toString());

        assertTrue(larp.isExitCommand(" bye "));
        assertFalse(larp.isExitCommand("bye now"));
    }
}
