# Workshop1
first workshop on the basics of java on the CodersLab course

Task Manager
Task Manager is a simple program that allows the user to add and remove tasks and store them in a text file. The program utilizes class division and various Java functionalities, such as loops, conditional statements, and file operations.

Program Features
Adding a task:

The user can enter a task description.
They can also provide a due date for the task and indicate if it's important.
Removing a task:

The user can select the index of the task they want to remove from the list.
If the provided index is valid, the task will be removed.
Storing tasks in a file:

Tasks are stored in a text file using the CSV format (values separated by commas and spaces).
After adding or removing a task, the list is saved to the file.
Used Java Elements
Class and method division for better code organization.
Switch case statement to handle different user selection options.
BufferedReader and BufferedWriter for reading from and writing to a file.
Storing tasks in a list (List<String[]>).
Utilizing for and while loops for iterating over tasks and indices.
File operations handling, such as adding and removing data.
User Guide
Run the program.
Choose one of the available options:
"add" - Add a new task.
"remove" - Remove an existing task.
"list" - Display the list of all tasks.
"exit" - Exit the program.
Follow the instructions provided on the screen.
After exiting the program, all changes will be saved in the text file.
Notes
The text file used for storing tasks is named "text.csv" and should be located in the same directory as the program.
If the text file doesn't exist, the program will automatically create a new file upon the first save.
Make sure you have the appropriate read and write permissions for the program's folder.
