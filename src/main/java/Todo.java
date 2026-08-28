/**
 * Represents a task without an associated date or time.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a todo with its saved completion state.
     *
     * @param description description of the todo
     * @param isDone whether the todo has been completed
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
