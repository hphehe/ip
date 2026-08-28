package larp;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Interprets user input and converts it into task data.
 */
public class Parser {
    private Parser() {
    }

    /**
     * Returns the keyword specified by a find command.
     *
     * @param input Full user input.
     * @return Keyword to search for.
     * @throws LarpException If the keyword is missing.
     */
    public static String parseFindKeyword(String input) throws LarpException {
        String keyword = input.substring("find".length()).trim();
        if (keyword.isEmpty()) {
            throw new LarpException("Please provide a keyword after 'find'.");
        }
        return keyword;
    }

    /**
     * Returns the zero-based task index specified by a command.
     *
     * @param input Full user input.
     * @param command Command whose task number is being parsed.
     * @param taskCount Number of tasks currently stored.
     * @return Zero-based index of the selected task.
     * @throws LarpException If the task number is missing or invalid.
     */
    public static int parseTaskIndex(String input, String command, int taskCount)
            throws LarpException {
        String indexText = input.substring(command.length()).trim();
        if (indexText.isEmpty()) {
            throw new LarpException("Please provide a task number after '" + command + "'.");
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(indexText);
        } catch (NumberFormatException e) {
            throw new LarpException("The task number must be a whole number.");
        }

        if (taskNumber < 1 || taskNumber > taskCount) {
            throw new LarpException("That task number is outside your task list.");
        }
        return taskNumber - 1;
    }

    /**
     * Creates a task from a todo, deadline, or event command.
     *
     * @param input Full user input.
     * @return Task described by the command.
     * @throws LarpException If the command or its arguments are invalid.
     */
    public static Task parseTask(String input) throws LarpException {
        if (input.equals("todo")) {
            throw new LarpException("The description of a todo cannot be empty.");
        }
        if (input.startsWith("todo ")) {
            String description = input.substring(5).trim();
            if (description.isEmpty()) {
                throw new LarpException("The description of a todo cannot be empty.");
            }
            return new Todo(description);
        }

        if (input.equals("deadline")) {
            throw new LarpException("Use: deadline DESCRIPTION /by yyyy-MM-dd.");
        }
        if (input.startsWith("deadline ")) {
            String[] parts = input.substring(9).split(" /by ", -1);
            if (parts.length != 2 || parts[0].isBlank() || parts[1].isBlank()) {
                throw new LarpException("Use: deadline DESCRIPTION /by yyyy-MM-dd.");
            }
            try {
                LocalDate by = LocalDate.parse(parts[1].trim());
                return new Deadline(parts[0].trim(), by);
            } catch (DateTimeParseException e) {
                throw new LarpException("Use a valid deadline date in yyyy-MM-dd format.");
            }
        }

        if (input.equals("event")) {
            throw new LarpException("Use: event DESCRIPTION /from START /to END.");
        }
        if (input.startsWith("event ")) {
            String[] eventParts = input.substring(6).split(" /from ", -1);
            if (eventParts.length != 2 || eventParts[0].isBlank()) {
                throw new LarpException("Use: event DESCRIPTION /from START /to END.");
            }

            String[] timeParts = eventParts[1].split(" /to ", -1);
            if (timeParts.length != 2 || timeParts[0].isBlank() || timeParts[1].isBlank()) {
                throw new LarpException("Use: event DESCRIPTION /from START /to END.");
            }
            return new Event(eventParts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
        }

        throw new LarpException("I don't recognize that command.");
    }
}
