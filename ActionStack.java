package texteditor;

public class ActionStack {

    // Stores a previous document state.
    static class Action {
        String[] lines;
        String description;

        Action(String[] lines, String description) {
            this.lines = lines;
            this.description = description;
        }
    }

    // Node used to implement the stack.
    private static class StackNode {
        Action data;
        StackNode next;

        StackNode(Action data) {
            this.data = data;
            this.next = null;
        }
    }

    private StackNode top;
    private int count;

    // Push: add a saved state to the top of the stack.
    public void push(String[] lines, String description) {
        Action action = new Action(lines, description);
        StackNode newNode = new StackNode(action);
        newNode.next = top;
        top = newNode;
        count++;
    }

    // Pop: remove and return the most recent saved state.
    public Action pop() {
        if (isEmpty()) {
            return null;
        }
        Action action = top.data;
        top = top.next;
        count--;
        return action;
    }

    // Peek: view the latest saved state without removing it.
    public Action peek() {
        if (isEmpty()) {
            return null;
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return count;
    }
}
