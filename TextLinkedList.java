package texteditor;

public class TextLinkedList {

    private static class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int count;

    // Insert a new line at the end of the list.
    public void insert(String text) {
        Node newNode = new Node(text);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        count++;
    }

    // Display all lines with line numbers.
    public void display() {
        if (isEmpty()) {
            System.out.println("Document is empty.");
            return;
        }
        Node current = head;
        int lineNumber = 1;
        System.out.println("\n----- Document Content -----");
        while (current != null) {
            System.out.println(lineNumber + ". " + current.data);
            current = current.next;
            lineNumber++;
        }
    }

    // Delete a line by its position.
    public boolean delete(int position) {
        if (position < 1 || position > count) {
            return false;
        }
        if (position == 1) {
            head = head.next;
        } else {
            Node current = head;
            for (int i = 1; i < position - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        count--;
        return true;
    }

    // Search text using linear search.
    public boolean search(String target) {
        Node current = head;
        boolean found = false;
        while (current != null) {
            if (current.data.toLowerCase().contains(
                    target.toLowerCase())) {
                System.out.println("Found: " + current.data);
                found = true;
            }
            current = current.next;
        }
        return found;
    }

    // Sort lines alphabetically using bubble sort.
    public void bubbleSort() {
        if (head == null || head.next == null) {
            return;
        }
        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            while (current.next != null) {
                if (current.data.compareToIgnoreCase(
                        current.next.data) > 0) {
                    String temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }

    // Copy the document into an array for undo.
    public String[] toArray() {
        String[] lines = new String[count];
        Node current = head;
        int index = 0;
        while (current != null) {
            lines[index++] = current.data;
            current = current.next;
        }
        return lines;
    }

    // Restore a previous document state.
    public void restore(String[] lines) {
        clear();
        for (String line : lines) {
            insert(line);
        }
    }

    public void clear() {
        head = null;
        count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return head == null;
    }
}
