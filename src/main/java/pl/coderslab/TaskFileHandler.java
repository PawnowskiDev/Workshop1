package pl.coderslab;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskFileHandler {
    private static final String CSV_DELIMITER = ",";

    public static String[][] readTasksFromFile(String filePath) throws IOException {

        BufferedReader br = null;
        String line;
        String[][] tasks = null;

        try {
            br = new BufferedReader(new FileReader(filePath));
            int numRows = 0;
            while (br.readLine() != null) {
                numRows++;
            }
            tasks = new String[numRows][];

            br = new BufferedReader(new FileReader(filePath));
            int row = 0;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                tasks[row] = fields;
                row++;
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return tasks;
    }

    public static void writeTasksToFile(String filePath) throws IOException {

        Scanner scan = new Scanner(System.in);

        FileWriter writer = new FileWriter(filePath);

        for (int i = 1; i<= 3; i++) {
            System.out.println(ConsoleColors.YELLOW + "Please add task description");
                String description = scan.nextLine();
            System.out.println(ConsoleColors.YELLOW + "Please add task due date");
                String dueDate = scan.nextLine();
            System.out.println(ConsoleColors.YELLOW + "Is your task is important: true/false");
                String isimportant = scan.nextLine();

            String taskLine = String.format("%s, %s, %s\n" , description ,dueDate, isimportant);
            writer.write(taskLine);

        }
        writer.close();
        scan.close();

        System.out.println(ConsoleColors.GREEN + "Tasks have been written to the file");

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

        scan.close();
    }

    public static void displayTasks(String[][] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            String[] task = tasks[i];
            System.out.printf("%d. %s, %s, %s\n", i, task[0], task[1], task[2]);
        }

    }
}
