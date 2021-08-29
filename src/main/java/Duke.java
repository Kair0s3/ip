import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    /**
     * Decorative prefixes to format program's output more neatly/cleaner.
     */
    private static final String CONSOLE_LINE_PREFIX = "____________________________________________________________";
    private static final String SPACE_PREFIX = " ";
    private static final String LINE_BREAK = "\n";

    /**
     * ASCII Art Logo generated using
     * https://patorjk.com/software/taag/#p=display&f=Dancing%20Font&t=Duke
     */
    private static final String LOGO = "  ____     _   _    _  __  U _____ u" + LINE_BREAK
            + " |  _\"\\ U |\"|u| |  |\"|/ /  \\| ___\"|/" + LINE_BREAK
            + "/| | | | \\| |\\| |  | ' /    |  _|\"" + LINE_BREAK
            + "U| |_| |\\ | |_| |U/| . \\u   | |___" + LINE_BREAK
            + " |____/ u<<\\___/   |_|\\_\\   |_____|" + LINE_BREAK
            + "  |||_  (__) )(  ,-,>> \\\\,-.<<   >>" + LINE_BREAK
            + " (__)_)     (__)  \\.)   (_/(__) (__)";

    /**
     * Program Messages
     */
    private static final String INVALID_TASK_MESSAGE = CONSOLE_LINE_PREFIX + LINE_BREAK
            + SPACE_PREFIX + "That is invalid... Please use the syntax - "
            + "[taskType]" + SPACE_PREFIX + "[taskName] ([/by dateTime] or [/at dateTime] depending on taskType)"
            + LINE_BREAK + CONSOLE_LINE_PREFIX;

    // Command Prefixes for checking type of command
    private static final String COMMAND_BYE = "Bye";
    private static final String COMMAND_DONE = "Done";
    private static final String COMMAND_LIST = "List";

    // Prefixes meant for processing data parameters for Task
    private static final String BY_WHEN_PREFIX = "/by";
    private static final String AT_WHEN_PREFIX = "/at";
    private static final String TASK_TODO_PREFIX = "Todo";
    private static final String TASK_DEADLINE_PREFIX = "Deadline";
    private static final String TASK_EVENT_PREFIX = "Event";

    /**
     * Initializing Scanner variable to allow for reading User Inputs.
     */
    private static final Scanner SC = new Scanner(System.in);

    /**
     * Maximum number of Tasks for a user.
     */
    private static final int MAX_TASKS = 100;


    /**
     * These variables are responsible for the management of Tasks
     */
    private static Task[] tasks;
    private static int taskCounter;

    /**
     * Initializes the list of Tasks and Task Counter
     */
    public static void initTasks() {
        tasks = new Task[MAX_TASKS];
        taskCounter = 0;
    }

    /**
     * Prints the program logo.
     */
    public static void printLogo() {
        System.out.println(LOGO);
    }

    /**
     * Prints out the program greeting message.
     */
    public static void printGreeting() {
        String greeting = CONSOLE_LINE_PREFIX + LINE_BREAK
                + SPACE_PREFIX + "Hello! You probably know that Iron Man has the best AI-assistant called Jarvis" + LINE_BREAK
                + SPACE_PREFIX + "and Spiderman has hmmm, maybe his tingly spidey senses?" + LINE_BREAK
                + SPACE_PREFIX + "But don't worry! You have me, Duke! I am your personal SIDEKICK that does \"something\"!" + LINE_BREAK
                + SPACE_PREFIX + "What is \"something\" you want me to do?" + LINE_BREAK
                + CONSOLE_LINE_PREFIX;
        System.out.println(greeting);
    }

    /**
     * Prints out the program farewell (exit) message.
     */
    public static void printFarewell() {
        String farewell = CONSOLE_LINE_PREFIX + LINE_BREAK
                + SPACE_PREFIX + "Bye, have a nice day! From your friendly neighbourhood assistant, Duke~" + LINE_BREAK
                + SPACE_PREFIX + "(NICE, I can finally binge watch Rick and Morty~)" + LINE_BREAK
                + CONSOLE_LINE_PREFIX;
        System.out.println(farewell);
    }

    /**
     * Print added to Tasks message.
     *
     * @param taskName The task's name
     */
    public static void printAddedToTaskMessage(String taskName) {
        System.out.println(CONSOLE_LINE_PREFIX + LINE_BREAK
                + SPACE_PREFIX + "Here you go..." + LINE_BREAK + " Added to stuff you would definitely forget to do (*facepalm*): "
                + taskName
                + LINE_BREAK
                + CONSOLE_LINE_PREFIX);
    }

    public static String getUserInput() {
        String userInput;
        System.out.print(SPACE_PREFIX + "What's your plans/command for today (No... I am not hitting on you) : ");
        userInput = SC.nextLine();
        return userInput;
    }

    /**
     * Creates a TodoObject from task name given by the user
     * and returns it to be added to Tasks
     *
     * @param taskName name of the Todo_Task to be created
     * @return TodoObject
     */
    public static Todo createNewToDo(String taskName) {
        return new Todo(taskName);
    }

    /**
     * Processes the unprocessed task name given by user, to get the actual task name
     * and the date to be completed ("byWhen").
     * Lastly, creates a Deadline Object before returning it to be added to Tasks
     *
     * @param unprocessedTaskName task name given by user before removing non-taskName relevant info
     * @return DeadlineObject
     */
    public static Deadline createNewDeadline(String unprocessedTaskName) {
        String byWhen = unprocessedTaskName.split(BY_WHEN_PREFIX)[1].trim();
        String actualTaskName = unprocessedTaskName.replace(BY_WHEN_PREFIX, "").replace(byWhen, "");
        actualTaskName = actualTaskName.trim();
        return new Deadline(actualTaskName, byWhen);
    }

    /**
     * Processes the unprocessed task name given by user, to get the actual task name
     * and the date of the event ("atWhen").
     * Lastly, creates an Event Object before returning it to be added to Tasks
     *
     * @param unprocessedTaskName task name given by user before removing non-taskName relevant info
     * @return EventObject
     */
    public static Event createNewEvent(String unprocessedTaskName) {
        String atWhen = unprocessedTaskName.split(AT_WHEN_PREFIX)[1].trim();
        String actualTaskName = unprocessedTaskName.replace(AT_WHEN_PREFIX, "").replace(atWhen, "");
        actualTaskName = actualTaskName.trim();
        return new Event(actualTaskName, atWhen);
    }

    /**
     * Adds a new Task to list of Tasks.
     *
     * @param taskType type of task to be added
     * @param taskName task name as of user input (Not processed for Deadline and Event)
     */
    public static void addToTasks(String taskType, String taskName) {
        Task newTask;
        if (taskType.equalsIgnoreCase(TASK_TODO_PREFIX)) {
            newTask = createNewToDo(taskName);
        } else if (taskType.equalsIgnoreCase(TASK_DEADLINE_PREFIX)) {
            if (!taskName.contains(BY_WHEN_PREFIX)) {
                System.out.println(INVALID_TASK_MESSAGE);
                return;
            }
            newTask = createNewDeadline(taskName);
        } else if (taskType.equalsIgnoreCase(TASK_EVENT_PREFIX)) {
            if (!taskName.contains(AT_WHEN_PREFIX)) {
                System.out.println(INVALID_TASK_MESSAGE);
                return;
            }
            newTask = createNewEvent(taskName);
        } else {
            System.out.println(INVALID_TASK_MESSAGE);
            return;
        }
        tasks[taskCounter] = newTask;
        taskCounter++;
        printAddedToTaskMessage(newTask.getTaskName());
    }

    /**
     * Prints all the Tasks.
     */
    public static void printTasks() {
        // validTasks contains only Tasks that are not NULL
        Task[] validTasks = Arrays.copyOf(tasks, taskCounter);
        if (taskCounter == 0) {
            System.out.println(CONSOLE_LINE_PREFIX + LINE_BREAK + SPACE_PREFIX + "Hi there! You have no dates! LITERALLY"
                    + LINE_BREAK + CONSOLE_LINE_PREFIX);
        } else {
            System.out.println(SPACE_PREFIX + "EEEEEOOOOOO~ ALL RIGHT~ Oops was jamming away in my virtual garage, here's your PLAN/S...");
            for (int i = 0; i < taskCounter; i++) {
                System.out.println(SPACE_PREFIX + (i + 1) + "." + SPACE_PREFIX + validTasks[i].toString());
            }
            System.out.println(CONSOLE_LINE_PREFIX);
        }
    }

    /**
     * Mark the task as done and print out marked as done message.
     *
     * @param index task index of task that user wants to mark as done in the list
     */
    public static void markTaskAsDone(int index) {
        Task task = tasks[index];
        task.setDone();
        System.out.println(SPACE_PREFIX + "Great! You didn't forget to do it! I have marked it as done!" + LINE_BREAK
                + SPACE_PREFIX + task + LINE_BREAK
                + CONSOLE_LINE_PREFIX);
    }

    public static void main(String[] args) {
        initTasks();
        printLogo();
        printGreeting();
        String userInput;
        while (true) {
            userInput = getUserInput();
            if (userInput.equalsIgnoreCase(COMMAND_BYE)) {
                break;
            }
            if (userInput.equalsIgnoreCase(COMMAND_LIST) || userInput.equals("")) {
                printTasks();
            } else {
                String[] userParams = userInput.split(SPACE_PREFIX);
                if (userParams[0].equalsIgnoreCase(COMMAND_DONE)) {
                    int index = Integer.parseInt(userParams[1]);
                    markTaskAsDone(index - 1);
                } else {
                    String taskType = userParams[0];
                    // Remove the Type of Task from the user input
                    String taskName = userInput.replace(taskType, "").trim();
                    addToTasks(taskType, taskName);
                }
            }
        }
        SC.close();
        printFarewell();
    }
}
