/**
 * Deals with making sense of user commands into Command classes.
 */
public class Parser {
    /**
     * String array containing user input string.
     */
    private String[] inputArr;

    /**
     * Restrict all valid command options
     */
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";
    private static final String ADD_COMMAND = "add";

    /**
     * Constructor for splitting input into main command and command details.
     * @param input original user input string
     */
    public Parser(String input) {
        this.inputArr = input.split(" ", 2);
    }

    /**
     * Returns main command.
     * @return main user command
     */
    public String getCommand() {
        return inputArr[0];
    }

    /**
     * Returns command details as integer for deleting and marking tasks as complete.
     * @return array index pointer for task in task list
     */
    public int getPointer() {
        return Integer.parseInt(inputArr[1]);
    }

    /**
     * Returns command details.
     * @return command details
     */
    public String getDetail() {
        if (inputArr.length > 1) {
            return inputArr[1];
        } else {
            return "";
        }
    }

    /**
     * Parses user commands into relevant Command classes to execute relevant actions.
     * @param command First keyword entered by user determining command type
     * @param commandDetails Following indicating keywords
     * @param indent Constant indentation from start of line (formatting)
     * @return Commands which execute relevant actions to follow user commands
     */
    public static Command parse(String command, String commandDetails, String indent) {
        if (command.equals(BYE_COMMAND)) {
            return new ExitCommand(command, commandDetails, indent);
        } else if (command.equals(LIST_COMMAND)) {
            return new ListCommand(command, commandDetails, indent);
        } else if (command.equals(DONE_COMMAND)) {
            return new DoneCommand(command, commandDetails, indent);
        } else if (command.equals(DELETE_COMMAND)) {
            return new DeleteCommand(command, commandDetails, indent);
        } else if (command.equals(FIND_COMMAND)) {
            return new FindCommand(command, commandDetails, indent);
        } else if (command.equals(ADD_COMMAND)) {
            return new AddCommand(command, commandDetails, indent);
        } else {
            return new InvalidCommand(command, commandDetails, indent);
        }
    }
}
