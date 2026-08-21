import java.util.Scanner;

/**
 * Starts the Larp chatbot and echoes user input until the user exits.
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
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(input);
        }
    }
}
