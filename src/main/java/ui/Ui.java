package ui;

import task.Task;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;
    /**
     * Decorative prefixes to format program's output more neatly/cleaner.
     */
    public static final String CONSOLE_LINE_PREFIX = "____________________________________________________________";
    public static final String SPACE_PREFIX = " ";
    public static final String LINE_BREAK = "\n";

    public Ui() {
        this(System.in);
    }

    public Ui(InputStream in) {
        this.sc = new Scanner(in);
    }

    /**
     * Prints the program logo.
     */
    public static void printLogo() {
        /**
         * ASCII Art Logo generated using
         * https://patorjk.com/software/taag/#p=display&f=Dancing%20Font&t=Duke
         */
        String logo = "  ____     _   _    _  __  U _____ u" + LINE_BREAK
                + " |  _\"\\ U |\"|u| |  |\"|/ /  \\| ___\"|/" + LINE_BREAK
                + "/| | | | \\| |\\| |  | ' /    |  _|\"" + LINE_BREAK
                + "U| |_| |\\ | |_| |U/| . \\u   | |___" + LINE_BREAK
                + " |____/ u<<\\___/   |_|\\_\\   |_____|" + LINE_BREAK
                + "  |||_  (__) )(  ,-,>> \\\\,-.<<   >>" + LINE_BREAK
                + " (__)_)     (__)  \\.)   (_/(__) (__)";
        System.out.println(logo);
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

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Print added to Tasks message.
     *
     * @param taskName The task's name
     */
    public static void printAddedTaskMessage(String taskName) {
        System.out.println(CONSOLE_LINE_PREFIX + LINE_BREAK
                + SPACE_PREFIX + "Here you go..." + LINE_BREAK + " Added to stuff you would definitely forget to do (*facepalm*): "
                + taskName
                + LINE_BREAK
                + CONSOLE_LINE_PREFIX);
    }

    public static void printDeletedTaskMessage(Task deletedTaskName, int taskCounter) {
        System.out.println(CONSOLE_LINE_PREFIX + LINE_BREAK
                + SPACE_PREFIX + "Roger that! I am Thanos and I have snapped away : " + LINE_BREAK
                + SPACE_PREFIX + deletedTaskName
                + LINE_BREAK
                + SPACE_PREFIX + "Now you have " + taskCounter + " tasks left!"
                + LINE_BREAK
                + CONSOLE_LINE_PREFIX);
    }

    public static void printMarkedTaskDoneMessage(Task doneTask) {
        System.out.println(SPACE_PREFIX + "Great! You didn't forget to do it! I have marked it as done!" + LINE_BREAK
                + SPACE_PREFIX + doneTask + LINE_BREAK
                + CONSOLE_LINE_PREFIX);
    }

    /**
     * Prints all the Tasks.
     */
    public static void printTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println(CONSOLE_LINE_PREFIX + LINE_BREAK + SPACE_PREFIX + "Hi there! You have no dates! LITERALLY"
                    + LINE_BREAK + CONSOLE_LINE_PREFIX);
        } else {
            System.out.println(SPACE_PREFIX + "EEEEEOOOOOO~ ALL RIGHT~ Oops was jamming away in my virtual garage, here's your PLAN/S...");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(SPACE_PREFIX + (i + 1) + "." + SPACE_PREFIX + tasks.get(i));
            }
            System.out.println(CONSOLE_LINE_PREFIX);
        }
    }
}
