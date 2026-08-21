/**
 * Starts the Larp chatbot and displays its greeting and farewell messages.
 */
public class Larp {
    /**
     * Runs the initial Level 0 interaction.
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
        System.out.println("Bye. Hope to see you again soon!");
    }
}
