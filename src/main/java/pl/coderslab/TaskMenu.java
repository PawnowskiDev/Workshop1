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

        switch (userInput) {
            case "add":
                TaskFileHandler.writeTasksToFile("tasks.csv");
                break;
            case "list":
                try {
                    String[][] tasks = fileHandler.readTasksFromFile("tasks.csv");
                    // Wyświetl zadania
                    TaskFileHandler.displayTasks(tasks);
                } catch (IOException e) {
                    System.out.println("Błąd odczytu pliku");
                }
                break;
            case "exit":
                System.out.println("Wyjście");
                break;
            default:
                System.out.println("Wrong choice");
                break;
        }

    }

}

