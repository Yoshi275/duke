/**
 * Handles addition of tasks upon user input eg. "todo complete CS2103"
 */
public class AddCommand extends Command {
    /**
     * Constructor that takes in keywords and indentation for execution of addition function.
     * @param command First keyword entered by user determining task type
     * @param commandDetails Following integer indicating task name and date (if relevant)
     * @param indent Constant indentation from start of line (formatting)
     */
    public AddCommand(String command, String commandDetails, String indent) {
        super(command, commandDetails, indent);
    }

    /**
     * Handles addition of task from list.
     * @param tasks Contains task list and operations to add from list
     * @param ui Handles user interaction
     * @param storage Updates new task list to file
     * @throws DukeException Custom exception
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String response = "";
            if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                String befTaskAddMessage = "Got it. I've added this task: \n" + indent + "   ";
                String aftTaskAddMessage = "Now you have " + (tasks.getList().size() + 1) + " tasks in the list.";
                if (!(commandDetails.equals(" ") || commandDetails.equals(""))) {
                    if (command.equals("todo")) {
                        tasks.addTask(new Task(commandDetails, "todo", false));
                        assert tasks.getList().get(tasks.getList().size() -1).getTaskType().equals("[T]")
                                : "To-do task not added";
                        response = ui.printResponse(befTaskAddMessage
                                + tasks.getList().get(tasks.getList().size() - 1) + "\n " + indent
                                + aftTaskAddMessage);
                        storage.updateTodoFile(tasks.getListString());
                    } else if (command.equals("deadline")) {
                        try {
                            tasks.addDateTask(commandDetails.split(" /by ")[0],
                                    commandDetails.split(" /by ")[1], "deadline");
                            assert tasks.getList().get(tasks.getList().size() -1).getTaskType().equals("[D]")
                                    : "Deadline task not added";
                            response = ui.printResponse(befTaskAddMessage
                                    + tasks.getList().get(tasks.getList().size() - 1) + "\n " + indent
                                    + aftTaskAddMessage);
                            storage.updateTodoFile(tasks.getListString());
                        } catch (Exception ex) {
                            response = ui.printError("☹ OOPS!!! Deadlines require a specific datetime "
                                    + "after /by, in format 'dd/MM/yyyy HHmm'");
                        }
                    } else if (command.equals("event")) {
                        try {
                            tasks.addDateTask(commandDetails.split(" /at ")[0],
                                    commandDetails.split(" /at ")[1], "event");
                            assert tasks.getList().get(tasks.getList().size() - 1).getTaskType().equals("[E]")
                                    : "Event task not added";
                            response = ui.printResponse(befTaskAddMessage
                                    + tasks.getList().get(tasks.getList().size() - 1) + "\n " + indent
                                    + aftTaskAddMessage);
                            storage.updateTodoFile(tasks.getListString());
                        } catch (Exception ex) {
                            response = ui.printError("OOPS!!! Events require a specific datetime after /at, "
                                    + "in format 'dd/MM/yyyy HHmm'");
                        }
                    }
                } else {
                    response = ui.printError("OOPS!!! The description of a " + command + " cannot be empty.");
                }
            } else {
                response = ui.printError("OOPS!!! I'm sorry, but I don't know what that means :-(\n "
                        + indent + "Try todo, event, deadline, list, delete or done");
            }
            return response;
        } catch (Exception err) {
            throw new DukeException(err.getMessage());
        }
    }
}
