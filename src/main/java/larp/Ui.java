package larp;

import java.util.Scanner;

/**
 * Handles console input and output for Larp.
 */
public class Ui {
    private static final String BANNER = " _        _    ____  ____\n"
            + "| |      / \\  |  _ \\|  _ \\\n"
            + "| |     / _ \\ | |_) | |_) |\n"
            + "| |___ / ___ \\|  _ <|  __/\n"
            + "|_____/_/   \\_\\_| \\_\\_|\n";

    private final Scanner scanner;

    /**
     * Creates a console user interface that reads from standard input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the application banner and greeting.
     */
    public void showWelcome() {
        System.out.println(BANNER);
        System.out.println("Hello! I'm Larp.");
        System.out.println("What can I do for you?");
    }

    /**
     * Returns whether another command is available from standard input.
     *
     * @return {@code true} if another command can be read, or {@code false} otherwise.
     */
    public boolean hasNextCommand() {
        return this.scanner.hasNextLine();
    }

    /**
     * Reads and trims the next command from standard input.
     *
     * @return Trimmed user command.
     */
    public String readCommand() {
        return this.scanner.nextLine().trim();
    }

    /**
     * Displays the farewell message.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays every task with a one-based list number.
     *
     * @param tasks Tasks to display.
     */
    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty.");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        showNumberedTasks(tasks);
    }

    /**
     * Displays tasks that match a find command.
     *
     * @param tasks Matching tasks to display.
     */
    public void showMatchingTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
            return;
        }

        System.out.println("Here are the matching tasks in your list:");
        showNumberedTasks(tasks);
    }

    private void showNumberedTasks(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Displays confirmation that a task was marked as completed.
     *
     * @param task Updated task.
     */
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Displays confirmation that a task was marked as incomplete.
     *
     * @param task Updated task.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Displays a removed task and the remaining task count.
     *
     * @param task Removed task.
     * @param taskCount Number of tasks remaining.
     */
    public void showDeletedTask(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " " + getTaskNoun(taskCount) + " in the list.");
    }

    /**
     * Displays an added task and the updated task count.
     *
     * @param task Added task.
     * @param taskCount Updated number of tasks.
     */
    public void showAddedTask(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " " + getTaskNoun(taskCount) + " in the list.");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message Explanation of the error.
     */
    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    private String getTaskNoun(int taskCount) {
        return taskCount == 1 ? "task" : "tasks";
    }
}
