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

            Task task;
            if (input.startsWith("todo ")) {
                String description = input.substring(5);
                task = new Todo(description);
            } else if (input.startsWith("deadline ")) {
                String[] deadlineParts = input.substring(9).split(" /by ", 2);
                task = new Deadline(deadlineParts[0], deadlineParts[1]);
            } else if (input.startsWith("event ")) {
                String[] eventParts = input.substring(6).split(" /from ", 2);
                String[] timeParts = eventParts[1].split(" /to ", 2);
                task = new Event(eventParts[0], timeParts[0], timeParts[1]);
            } else {
                task = new Task(input);
            }

            tasks[taskCount] = task;
            taskCount++;
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + task);
            String taskNoun = taskCount == 1 ? "task" : "tasks";
            System.out.println("Now you have " + taskCount + " " + taskNoun + " in the list.");
        }
    }
}
