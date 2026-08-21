import java.util.Scanner;

/**
 * Starts the Larp chatbot and manages an in-memory task list.
 */
public class Larp {
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
        Task[] tasks = new Task[100];
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
                    System.out.println((i + 1) + "." + tasks[i]);
                }
                continue;
            }

            if (input.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(input.substring(5)) - 1;
                tasks[taskIndex].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[taskIndex]);
                continue;
            }

            if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                tasks[taskIndex].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[taskIndex]);
                continue;
            }

            Task task = new Task(input);
            tasks[taskCount] = task;
            taskCount++;
            System.out.println("Added: " + task);
        }
    }
}
