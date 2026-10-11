package texteditor;

import java.util.Scanner;

public class TextEditorWithUndo {

    private static final Scanner input = new Scanner(System.in);
    private static final TextLinkedList document = new TextLinkedList();
    private static final ActionStack undoStack = new ActionStack();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== TEXT EDITOR WITH UNDO =====");
            System.out.println("1. Add a line");
            System.out.println("2. Display all lines");
            System.out.println("3. Delete a line");
            System.out.println("4. Search text");
            System.out.println("5. Sort lines alphabetically");
            System.out.println("6. Undo last action");
            System.out.println("7. Show last action");
            System.out.println("8. Document statistics");
            System.out.println("9. Clear document");
            System.out.println("0. Exit");
            choice = readInteger("Enter choice: ");

            switch (choice) {
                case 1:
                    System.out.print("Enter text: ");
                    String text = input.nextLine().trim();
                    if (text.isEmpty()) {
                        System.out.println("Text cannot be empty.");
                    } else {
                        saveUndo("Add line");
                        document.insert(text);
                        System.out.println("Line added successfully.");
                    }
                    break;
                case 2:
                    document.display();
                    break;
                case 3:
                    if (document.isEmpty()) {
                        System.out.println("Document is empty.");
                        break;
                    }
                    document.display();
                    int position = readInteger("Enter line number: ");
                    if (position < 1 || position > document.size()) {
                        System.out.println("Invalid line number.");
                    } else {
                        saveUndo("Delete line");
                        document.delete(position);
                        System.out.println("Line deleted successfully.");
                    }
                    break;
                case 4:
                    System.out.print("Enter text to search: ");
                    String target = input.nextLine().trim();
                    if (target.isEmpty()) {
                        System.out.println("Search text cannot be empty.");
                    } else if (!document.search(target)) {
                        System.out.println("Text not found.");
                    }
                    break;
                case 5:
                    if (document.size() < 2) {
                        System.out.println("Add at least two lines first.");
                    } else {
                        saveUndo("Sort lines");
                        document.bubbleSort();
                        System.out.println("Lines sorted successfully.");
                        document.display();
                    }
                    break;
                case 6:
                    if (undoStack.isEmpty()) {
                        System.out.println("Nothing to undo.");
                    } else {
                        ActionStack.Action action = undoStack.pop();
                        document.restore(action.lines);
                        System.out.println(
                            "Undo successful: " + action.description
                        );
                        document.display();
                    }
                    break;
                case 7:
                    if (undoStack.isEmpty()) {
                        System.out.println("No previous action available.");
                    } else {
                        System.out.println(
                            "Last action: "
                            + undoStack.peek().description
                        );
                    }
                    break;
                case 8:
                    System.out.println("Total lines: " + document.size());
                    System.out.println(
                        "Document empty: " + document.isEmpty()
                    );
                    System.out.println(
                        "Undo actions: " + undoStack.size()
                    );
                    break;
                case 9:
                    if (document.isEmpty()) {
                        System.out.println("Document is already empty.");
                    } else {
                        System.out.print("Clear document? (yes/no): ");
                        String answer = input.nextLine().trim();
                        if (answer.equalsIgnoreCase("yes")) {
                            saveUndo("Clear document");
                            document.clear();
                            System.out.println("Document cleared.");
                        } else {
                            System.out.println("Operation cancelled.");
                        }
                    }
                    break;
                case 0:
                    System.out.println("Thank you for using Text Editor!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
        input.close();
    }

    private static int readInteger(String message) {
        while (true) {
            System.out.print(message);
            String value = input.nextLine().trim();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static void saveUndo(String description) {
        undoStack.push(document.toArray(), description);
    }
}
