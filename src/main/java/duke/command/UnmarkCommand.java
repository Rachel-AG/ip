package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.markTaskN(index, false);
        ui.printUnmarkTaskReply(taskList.getTaskN(index).toString());
        storage.overwriteFile(taskList.toStorageString());

    }
}