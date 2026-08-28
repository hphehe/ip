/**
 * Represents a task that occurs between specified start and end times.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        this(description, from, to, false);
    }

    /**
     * Creates an event with its saved completion state.
     *
     * @param description description of the event
     * @param from start time of the event
     * @param to end time of the event
     * @param isDone whether the event has been completed
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from + " to: " + this.to + ")";
    }
}
