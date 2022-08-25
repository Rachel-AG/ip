public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.markTaskN(index, true);
        ui.printMarkTaskReply(taskList.getTaskN(index).toString());
        storage.overwriteFile(taskList.toFile());

    }
}
