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

//  Odczytuje dane z pliku CSV i zwraca je jako dwuwymiarową tablicę.
//  @param filePath ścieżka do pliku CSV
//  @return dwuwymiarowa tablica z danymi z pliku
//  @throws IOException w przypadku błędu odczy

//  Inicjalizacja bufora odczytu pliku
        BufferedReader br = null;
        String line;
        String[][] tasks = null;

        try {
//  Utworzenie bufora odczytu dla pliku CSV
            br = new BufferedReader(new FileReader(filePath));

// Zliczanie liczby linii w pliku CSV
            int numRows = 0;
            while (br.readLine() != null) {
                numRows++;
            }
// Utworzenie tablicy o odpowiednich wymiarach
            tasks = new String[numRows][];

// Przejdź przez plik CSV ponownie i odczytaj dane linia po linii
            br = new BufferedReader(new FileReader(filePath));
            int row = 0;
            while ((line = br.readLine()) != null) {
// Podziel linię na pola, korzystając z separatora (np. przecinek)
                String[] fields = line.split(",");
// Przypisz pola do odpowiedniego wiersza w tablicy tasks
                tasks[row] = fields;
                row++;
            }
        } finally {
// Zamknięcie bufora odczytu pliku
            if (br != null) {
                br.close();
            }
        }
        return tasks;
    }

    public static void writeTasksToFile(String filePath, String[][] tasks) throws IOException {

// Otwarcie pliku do zapisu
        FileWriter writer = new FileWriter(filePath);

// zapisanie danych z tablicy tasks do pliku
        for (String[] row : tasks) {
// konwesja wartości w wierszu na format "opis, data, czy ważne"
            String rowString = String.format("%s, %s, %s", row[0], row[1], row[2]);
// zapisanie wiersza do pliku
            writer.write(rowString);
// dodanie znaku nowej lini po każdym wierszu
            writer.write("\n");
        }
// zamknięcie pliku
        writer.close();

    }

    public static void removeTasksFromFile(String filePath) throws IOException {
// odczytuje zawartość pliku do tablicy
        String[][] tasks = readTasksFromFile(filePath);

// wyświetlamy listę zadań
        System.out.println(ConsoleColors.PURPLE + "Tasks list: ");
        for (int i = 0; i < tasks.length; i++) {
            String[] task = tasks[i];
            System.out.printf("%d. %s, %s, %s\n", i, task[0], task[1], task[2]);
        }
// wprowadzanie nr zadania do usuniecia
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
// usuwanie wybranego zadania z tablicy tasks
        String[][] updatedTasks = new String[tasks.length - 1][3];
        int index = 0;
        for (int i = 0; i < tasks.length; i++) {
            if (i != taskIndex) {
                updatedTasks[index] = tasks[i];
                index++;
            }
        }
// aktualizujemy plik z nową tablicą
        writeTasksToFile(filePath, updatedTasks);

        System.out.println(ConsoleColors.PURPLE + "Task has been removed");

        scan.close();
    }

    public static void diplayTasks(String[][] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            String[] task = tasks[i];
            System.out.printf("%d. %s, %s, %s\n", i, task[0], task[1], task[2]);
        }

    }
}
