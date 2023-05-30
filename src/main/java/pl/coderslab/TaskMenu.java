package pl.coderslab;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class TaskMenu {

    public static void taskMenu() throws IOException {

        Scanner scan = new Scanner(System.in);
        String userInput;

        do {
            displayMenu();
            userInput = scan.nextLine();
            switchCaseMenu(userInput);
        } while (!userInput.equalsIgnoreCase("exit"));

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

            Scanner scanner = new Scanner(System.in);

        while (true) {

            userInput = scanner.nextLine();

        switch (userInput) {
            case "add":
                TaskFileHandler.addTask();
                break;

            case "remove":
                TaskFileHandler.removeTask();
                break;

            case "list":
                List<String[]> tasks = null;
                try {
                    tasks = TaskFileHandler.readFromFile(TaskFileHandler.FILE_NAME);
                    for (String[] task : tasks) {
                        for (int i = 0; i < task.length; i++) {
                            System.out.print(task[i] + ", ");
                        }
                        System.out.println();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "exit":
                System.out.println(ConsoleColors.PURPLE + "Exit");
                return;

            default:
                System.out.println(ConsoleColors.PURPLE + "Please select a correct option.");
                continue;
        }

        }
    }
}

