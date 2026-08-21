import java.util.Scanner;

/**
 * Starts the Larp chatbot and manages an in-memory task list.
 */
public class Larp {
    /**
     * Runs the interactive chatbot session.
     *
     * @param args command-line arguments; they are not used
     */
    public static void main(String[] args) {
        String banner = " _        _    ____  ____\n"
                + "| |      / \\  |  _ \\|  _ \\\n"
                + "| |     / _ \\ | |_) | |_) |\n"
                + "| |___ / ___ \\|  _ <|  __/\n"
                + "|_____/_/   \\_\\_| \\_\\_|\n";

        System.out.println(banner);
        System.out.println("Hello! I'm Larp.");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCount = 0;

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (input.equals("list")) {
                if (taskCount == 0) {
                    System.out.println("Your task list is empty.");
                    continue;
                }

                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                continue;
            }

            tasks[taskCount] = input;
            taskCount++;
            System.out.println("Added: " + input);
        }
    }
}
