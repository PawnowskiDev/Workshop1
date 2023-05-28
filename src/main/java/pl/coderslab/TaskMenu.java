package pl.coderslab;

import java.io.IOException;
import java.util.Scanner;

public class TaskMenu {

    public static void taskMenu() throws IOException {

        Scanner scan = new Scanner(System.in);
        String userInput;

        do {
            displayMenu();
            userInput = scan.nextLine();
            switchCaseMenu(userInput);

        } while (!userInput.equals("exit"));

        scan.close();
    }


    public static void displayMenu() {

        System.out.println(ConsoleColors.BLUE + "PLEASE SELECT AN OPTION :");
        System.out.println(ConsoleColors.YELLOW + "add");
        System.out.println(ConsoleColors.YELLOW + "remove");
        System.out.println(ConsoleColors.YELLOW + "list");
        System.out.println(ConsoleColors.YELLOW + "exit");

    }


    public static void switchCaseMenu(String userInput) throws IOException {

        TaskFileHandler fileHandler = new TaskFileHandler();
        Scanner scan = new Scanner(System.in);



        while (true) {

        switch (userInput) {
            case "add":

                String[][] tasks = null;
                try {
                    TaskFileHandler.writeTasksToFile("tasks.csv");
                } catch (IOException e) {
                    System.out.println(ConsoleColors.RED + "An error occurred while writing tasks to the file" + e.getMessage());
                    e.printStackTrace();
                }
                scan.nextLine();
                switchCaseMenu(userInput);
                break;

            case "remove":
                try {
                    TaskFileHandler.removeTasksFromFile("tasks.csv");
                } catch (IOException e) {
                    System.out.println(ConsoleColors.RED + "An error occurred while writing tasks to the file" + e.getMessage());

                }
                break;
            case "list":
                try {
                    tasks = fileHandler.readTasksFromFile("tasks.csv");
                    TaskFileHandler.displayTasks(tasks);
                } catch (IOException e) {
                    System.out.println(ConsoleColors.RED + "Can't open file" + ConsoleColors.RED + e.getMessage());
                }
                break;
            case "exit":
                System.out.println(ConsoleColors.PURPLE +  "Exit");
                break;
            default:
                System.out.println("Wrong choice");
                break;
        }

                    if(userInput.equalsIgnoreCase("exit")) {
                            break;
            }
        }

    }
}
