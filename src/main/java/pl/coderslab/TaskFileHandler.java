package pl.coderslab;

import java.io.*;
import java.util.*;

public class TaskFileHandler {

    static final String FILE_NAME = "/home/pawnowdev/IdeaProjects/CodersLab/CodersLabJava/TaskMenager/src/main/resources/data/tasks.csv";


    // metoda która będzie wczytywać oraz przekształcać na List<String> plik tekst.csv

    public static List<String[]> readFromFile(String FILE_NAME) throws IOException {

        List<String[]> taskList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] values = line.split(", ");
                taskList.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    public static void addTask() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add task description:");
        String description = scanner.nextLine();
        System.out.println("Please add task due date:");
        String dueDate = scanner.nextLine();
        System.out.println("Is your task important: true/false");
        String isImportant = scanner.nextLine();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(description + ", " + dueDate + ", " + isImportant);
            bw.newLine();
            System.out.println(ConsoleColors.PURPLE + "Task added successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeTask() throws IOException {

        List<String[]> tasks = TaskFileHandler.readFromFile(FILE_NAME);

        System.out.println(ConsoleColors.PURPLE + "Current tasks: ");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + ". ");
            for (int j = 0; j < tasks.get(i).length; j++) {
                System.out.print(tasks.get(i)[j] + ", ");
            }
            System.out.println();
        }

        System.out.println("Enter index to remove");
        Scanner scan = new Scanner(System.in);
        int index = scan.nextInt();
        scan.nextLine();

        if (index >= 0 && index <= tasks.size()) {
            tasks.remove(index);
            System.out.println(ConsoleColors.PURPLE + "Task removed successfully");

            // zaktualizowanie listy do pliku
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
                for (String[] task : tasks) {
                    bw.write(String.join(", ", task));
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(ConsoleColors.RED + "Invalid index");
        }
    }

}
