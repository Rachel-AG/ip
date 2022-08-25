import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String taskDescription, LocalDate by) {
        super(taskDescription);
        this.by = by;
    }

    public static Deadline DeadlineFromFile(String taskFromFile, boolean isDone) throws DukeException {
        String[] deadlineDesc = taskFromFile.split("\\|", 2);
        if (deadlineDesc.length != 2 || deadlineDesc[0].equals("") || deadlineDesc[1].equals("")) {
            throw new DukeException("OOPS! A deadline must contain a description and a due date.");
        }
        Deadline result = new Deadline(deadlineDesc[0], LocalDate.parse(deadlineDesc[1]));
        result.isDoneSetter(isDone);
        return result;
    }


    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toFile() {
        return "D" + "|" + super.toFile() + "|" + by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by:" + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
