import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public Parser() {}

    public Command parse(String input) throws DukeException {
        String[] arg = input.split(" ", 2);
        String command = arg[0];
        String commandArg = arg.length == 2 ? arg[1].trim() : "";
            switch (command) {
                case "list":
                    if (!commandArg.equals("")) {
                        throw new DukeException("list must not have an argument.");
                    }
                    return new ListCommand();
                case "mark":
                    return new MarkCommand(parseIntegerArgument(commandArg));
                case "unmark":
                    return new UnmarkCommand(parseIntegerArgument(commandArg));
                case "todo":
                    if (commandArg.equals("")) {
                        throw new DukeException("todo must contain a description.");
                    }
                    return new ToDoCommand(new ToDo(commandArg));
                case "deadline" :
                    String[] deadlineArgs = parseDeadlineArguments(commandArg);
                    return new DeadlineCommand(new Deadline(deadlineArgs[0], parseDateArg(deadlineArgs[1])));
                case "event":
                    String[] eventArgs = parseEventArguments(commandArg);
                    return new EventCommand(new Event(eventArgs[0], parseDateArg(eventArgs[1])));
                case "delete":
                    return new DeleteCommand(parseIntegerArgument(commandArg));
                case "bye":
                    if (!commandArg.equals("")) {
                        throw new DukeException("bye must not have any argument.");
                    }
                    return new ByeCommand();
                default:
                    throw new DukeException("Sorry, I don't understand what that means :(");
            }
    }

    private String[] parseDeadlineArguments(String desc) throws DukeException {
        String[] res = desc.split("/by");
        for (int i = 0; i < res.length; i++) {
            res[i] = res[i].trim();
        }
        if (res.length != 2 || res[0].equals("") || res[1].equals("")) {
            throw new DukeException("A deadline must contain a description and a due date.");
        }
        return res;
    }

    private String[] parseEventArguments(String desc) throws DukeException {
        String[] res = desc.split("/at");
        for (int i = 0; i < res.length; i++) {
            res[i] = res[i].trim();
        }
        if (res.length != 2 || res[0].equals("") || res[1].equals("")) {
            throw new DukeException("An event must contain a description and a date.");
        }
        return res;
    }

    private int parseIntegerArgument(String index) throws DukeException {
        try {
            return Integer.parseInt(index);
        }  catch (NumberFormatException e) {
            throw new DukeException("You must enter the index of the task.");
        }
    }

    private LocalDate parseDateArg(String date) throws DukeException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("You have entered an invalid date format.");
        }
    }

}
