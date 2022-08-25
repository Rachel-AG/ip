package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.ToDo;
import duke.Ui;

public class ToDoCommand extends Command {
    private final ToDo todo;

    public ToDoCommand(ToDo todo) {
        super(false);
        this.todo = todo;

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(todo);
        ui.printAddTaskReply(todo.toString(), taskList.getNumOfTask());
        storage.overwriteFile(taskList.toFile());
    }
}
