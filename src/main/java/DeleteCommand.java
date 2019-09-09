/**
 * Handles deletion of tasks upon user input eg. "delete 2"
 */
public class DeleteCommand extends Command {
    /**
     * Integer indicating task to be deleted.
     */
    private int listPointer;

    /**
     * Constructor that takes in keywords and indentation for execution of delete function.
     * @param command First keyword entered by user determining command type
     * @param commandDetails Following integer indicating list index of task to be deleted
     * @param indent Constant indentation from start of line (formatting)
     */
    public DeleteCommand(String command, String commandDetails, String indent) {
        super(command, commandDetails, indent);
    }

    /**
     * Handles deletion of task from list.
     * @param tasks Contains task list and operations to delete from list
     * @param ui Handles user interaction
     * @param storage Updates new task list to file
     * @throws DukeException Custom exception
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            listPointer = Integer.parseInt(commandDetails);
            Task deletedTask = tasks.getList().get(listPointer - 1);
            tasks.deleteTask(listPointer - 1);
            String response = ui.printResponse("Noted. I've removed this task: \n"
                    + indent + "   " + deletedTask + "\n" + indent
                    + "Now you have " + tasks.getList().size() + " tasks in the list.");
            storage.updateTodoFile(tasks.getListString());
            return response;
        } catch(NumberFormatException err) {
            throw new DukeException("Please input integer index of task.");
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("Item " + listPointer + " does not exist. Please try again.");
        } catch (Exception err) {
            throw new DukeException(err.getMessage());
        }
    }
}
