/**
 * Represents an error caused by an invalid command given to Larp.
 */
public class LarpException extends Exception {
    private static final long serialVersionUID = 1L;

    public LarpException(String message) {
        super(message);
    }
}
