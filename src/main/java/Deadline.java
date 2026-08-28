/**
 * Represents a task that must be completed by a specified time.
 */
public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by) {
        this(description, by, false);
    }

    /**
     * Creates a deadline with its saved completion state.
     *
     * @param description description of the deadline
     * @param by time by which the deadline should be completed
     * @param isDone whether the deadline has been completed
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
