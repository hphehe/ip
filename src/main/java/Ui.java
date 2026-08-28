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

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(BANNER);
        System.out.println("Hello! I'm Larp.");
        System.out.println("What can I do for you?");
    }

    public boolean hasNextCommand() {
        return this.scanner.hasNextLine();
    }

    public String readCommand() {
        return this.scanner.nextLine().trim();
    }

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
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    public void showDeletedTask(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " " + getTaskNoun(taskCount) + " in the list.");
    }

    public void showAddedTask(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " " + getTaskNoun(taskCount) + " in the list.");
    }

    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    private String getTaskNoun(int taskCount) {
        return taskCount == 1 ? "task" : "tasks";
    }
}
