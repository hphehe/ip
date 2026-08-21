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
            String input = scanner.nextLine().trim();
            try {
                if (input.isEmpty()) {
                    throw new LarpException("Please enter a command.");
                }

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

                if (input.equals("mark") || input.startsWith("mark ")) {
                    int taskIndex = parseTaskIndex(input, "mark", taskCount);
                    tasks[taskIndex].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[taskIndex]);
                    continue;
                }

                if (input.equals("unmark") || input.startsWith("unmark ")) {
                    int taskIndex = parseTaskIndex(input, "unmark", taskCount);
                    tasks[taskIndex].markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[taskIndex]);
                    continue;
                }

                if (input.equals("delete") || input.startsWith("delete ")) {
                    int taskIndex = parseTaskIndex(input, "delete", taskCount);
                    Task deletedTask = tasks[taskIndex];
                    for (int i = taskIndex; i < taskCount - 1; i++) {
                        tasks[i] = tasks[i + 1];
                    }
                    tasks[taskCount - 1] = null;
                    taskCount--;

                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + deletedTask);
                    String taskNoun = taskCount == 1 ? "task" : "tasks";
                    System.out.println("Now you have " + taskCount + " " + taskNoun + " in the list.");
                    continue;
                }

                Task task = parseTask(input);
                if (taskCount == tasks.length) {
                    throw new LarpException("Your task list is full.");
                }

                tasks[taskCount] = task;
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                String taskNoun = taskCount == 1 ? "task" : "tasks";
                System.out.println("Now you have " + taskCount + " " + taskNoun + " in the list.");
            } catch (LarpException e) {
                System.out.println("OOPS!!! " + e.getMessage());
            }
        }
    }

    private static int parseTaskIndex(String input, String command, int taskCount)
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

    private static Task parseTask(String input) throws LarpException {
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
            throw new LarpException("Use: deadline DESCRIPTION /by TIME.");
        }
        if (input.startsWith("deadline ")) {
            String[] parts = input.substring(9).split(" /by ", -1);
            if (parts.length != 2 || parts[0].isBlank() || parts[1].isBlank()) {
                throw new LarpException("Use: deadline DESCRIPTION /by TIME.");
            }
            return new Deadline(parts[0].trim(), parts[1].trim());
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
