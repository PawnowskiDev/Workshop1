package pl.coderslab;

import java.io.IOException;
import java.util.Scanner;

public class TaskMenu {

    public static void taskMenu() {

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


    public static void switchCaseMenu(String userInput){

        TaskFileHandler fileHandler = new TaskFileHandler()

        switch (userInput){
            case "add":
                try {
                    String[][] tasks = fileHandler.readTasksFromFile("tasks.csv");
                    TaskFileHandler.diplayTasks(tasks);
                } catch (IOException e) {
                    System.out.println(ConsoleColors.RED + "Error to open file " + e.getMessage() );
                }
                break;
            case "remove":
                String[][] tasks = null;
                try {
                    tasks = fileHandler.readTasksFromFile("tasks.csv");
                } catch (IOException e) {
                    System.out.println(ConsoleColors.RED + "Error to open file " + e.getMessage() );
                }
                // todo finish methode of user input to tasks
                // String[] newTask = getTaskFromUser

                break;
            case "list":
                System.out.println("Wybrano list");
                break;
            case "exit":
                System.out.println("Wyjście");
                break;
            default:
                System.out.println("Wrong choice");
                break;
        }

    }

