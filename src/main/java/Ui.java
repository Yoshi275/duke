/**
 * Deals with user interaction.
 */
public class Ui {
    /**
     * Constant indentation after start of line (formatting).
     */
    private String indent;

    /**
     * Constructor for UI to indent correctly.
     * @param indent Constant indentation from start of line (formatting)
     */
    public Ui(String indent) {
        this.indent = indent;
    }

    /**
     * Returns message to greet user and begin program.
     */
    public String showWelcome() {
        return "Hello! I'm Duke\n" + indent + " " + "What can I do for you?";
    }

    /**
     * Returns message to greet user goodbye and end program.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns error message in loading.
     */
    public String showLoadingError() {
        return "Loading error! New file created.";
    }

    /**
     * Returns input error message.
     * @param errorMessage Relevant error message
     */
    public String printError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Returns response message to user.
     * @param responseMessage Relevant message
     */
    public String printResponse(String responseMessage) {
        return responseMessage;
    }
}
