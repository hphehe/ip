package larp;

/**
 * Coordinates the Larp chatbot's user interface, task list, parser, and storage.
 */
public class Larp {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Creates a Larp chatbot that stores tasks at the specified file path.
     *
     * @param filePath Path of the task data file.
     */
    public Larp(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.tasks = new TaskList();
    }

    /**
     * Starts the command loop and processes input until the user exits.
     */
    public void run() {
        this.ui.showWelcome();
        loadTasks();

        boolean isExit = false;
        while (!isExit && this.ui.hasNextCommand()) {
            String input = this.ui.readCommand();
            try {
                isExit = executeCommand(input);
            } catch (LarpException e) {
                this.ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Loads saved tasks, falling back to an empty task list if loading fails.
     */
    private void loadTasks() {
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (LarpException e) {
            this.ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Executes one user command and reports its result through the user interface.
     *
     * @param input User command to execute.
     * @return {@code true} if the command exits Larp, or {@code false} otherwise.
     * @throws LarpException If the command or its arguments are invalid.
     */
    private boolean executeCommand(String input) throws LarpException {
        if (input.isEmpty()) {
            throw new LarpException("Please enter a command.");
        }

        if (input.equals("bye")) {
            this.ui.showGoodbye();
            return true;
        }

        if (input.equals("list")) {
            this.ui.showTaskList(this.tasks);
            return false;
        }

        if (input.equals("find") || input.startsWith("find ")) {
            String keyword = Parser.parseFindKeyword(input);
            TaskList matchingTasks = this.tasks.find(keyword);
            this.ui.showMatchingTasks(matchingTasks);
            return false;
        }

        if (input.equals("mark") || input.startsWith("mark ")) {
            int taskIndex = Parser.parseTaskIndex(input, "mark", this.tasks.size());
            Task task = this.tasks.markAsDone(taskIndex);
            this.storage.save(this.tasks);
            this.ui.showMarkedTask(task);
            return false;
        }

        if (input.equals("unmark") || input.startsWith("unmark ")) {
            int taskIndex = Parser.parseTaskIndex(input, "unmark", this.tasks.size());
            Task task = this.tasks.markAsNotDone(taskIndex);
            this.storage.save(this.tasks);
            this.ui.showUnmarkedTask(task);
            return false;
        }

        if (input.equals("delete") || input.startsWith("delete ")) {
            int taskIndex = Parser.parseTaskIndex(input, "delete", this.tasks.size());
            Task deletedTask = this.tasks.delete(taskIndex);
            this.storage.save(this.tasks);
            this.ui.showDeletedTask(deletedTask, this.tasks.size());
            return false;
        }

        Task task = Parser.parseTask(input);
        this.tasks.add(task);
        this.storage.save(this.tasks);
        this.ui.showAddedTask(task, this.tasks.size());
        return false;
    }

    /**
     * Starts Larp using its default task data file.
     *
     * @param args Command-line arguments, which are not used.
     */
    public static void main(String[] args) {
        new Larp("data/larp.txt").run();
    }
}
