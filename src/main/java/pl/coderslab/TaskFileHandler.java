package pl.coderslab;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskFileHandler {
    private static final String CSV_DELIMITER = ",";

    public static String[][] readTasksFromFile(String filePath) throws IOException {

        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        Scanner scanFile = new Scanner(fileReader);
        
        int linesCount = 0;
        while (scanFile.hasNextLine()) {
            scanFile.nextLine();
            linesCount++;
        }
        scanFile.close();
        
        String[][] tasks = new String[linesCount][3];
        
        fileReader = new FileReader(file);
        scanFile = new Scanner(fileReader);
        
        int lineIndex = 0;
        
        while (scanFile.hasNextLine()) {
            String line = scanFile.nextLine();
            String[] taskData = line.split(", ");
            tasks[lineIndex] = taskData;
            lineIndex++;
        }
        fileReader.close();
        scanFile.nextLine();
        scanFile.close();


        return tasks;
    }

    public static void writeTasksToFile(String filePath) throws IOException {

        Scanner scan = new Scanner(System.in);
        FileWriter writer = new FileWriter(filePath);
        String ifContinue;

        do {
            System.out.println(ConsoleColors.YELLOW + "Please add task description");
            String description = scan.nextLine();

            System.out.println(ConsoleColors.YELLOW + "Please add task due date");
            String dueDate = scan.nextLine();

            System.out.println(ConsoleColors.YELLOW + "Is your task is important: true/false");
            String isimportant = scan.nextLine();

            String taskLine = String.format("%s, %s, %s\n", description, dueDate, isimportant);
            writer.write(taskLine);

            System.out.println(ConsoleColors.YELLOW + "Do you want to add more tasks? (yes/no)");
            ifContinue = scan.next();
            scan.nextLine();
        } while (ifContinue.equals("yes"));

        writer.close();

        System.out.println(ConsoleColors.GREEN + "Tasks have been written to the file");

        scan.nextLine();
        scan.close();


    }

    public static void removeTasksFromFile(String filePath) throws IOException {

        String[][] tasks = readTasksFromFile(filePath);

        System.out.println(ConsoleColors.PURPLE + "Tasks list: ");
        for (int i = 0; i < tasks.length; i++) {
            String[] task = tasks[i];
            System.out.printf("%d. %s, %s, %s\n", i, task[0], task[1], task[2]);
        }
        Scanner scan = new Scanner(System.in);
        System.out.println(ConsoleColors.PURPLE + "Insert number of tasks to remove");
        int taskIndex;

        try {
            taskIndex = scan.nextInt();
            scan.nextLine();
        } catch (InputMismatchException e) {
            System.out.println(ConsoleColors.RED + " Invalid Input. Expect a number");
            scan.close();
            return;
        }

        if (taskIndex < 0 || taskIndex >= tasks.length) {
            System.out.println(ConsoleColors.RED + "Wrong number of task!");
            scan.close();
            return;
        }
        String[][] updatedTasks = new String[tasks.length - 1][3];
        int index = 0;
        for (int i = 0; i < tasks.length; i++) {
            if (i != taskIndex) {
                updatedTasks[index] = tasks[i];
                index++;
            }
        }
        writeTasksToFile(filePath);

        System.out.println(ConsoleColors.PURPLE + "Task has been removed");

        System.out.println(ConsoleColors.PURPLE + "Task has been removed");

        System.out.println(ConsoleColors.PURPLE + "Task list after removal");
        System.out.println();
        displayTasks((updatedTasks));

        System.out.println("Press 'm' to return the menu");

        scan.nextLine();
        scan.close();
    }

    public static void displayTasks(String[][] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            String[] task = tasks[i];
            System.out.printf("%d. %s, %s, %s\n", i, task[0], task[1], task[2]);
        }

    }
}
