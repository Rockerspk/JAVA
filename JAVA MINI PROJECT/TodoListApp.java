import javax.swing.SwingUtilities;

public class TodoListApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdvancedTodoGUI().createAndShowGUI());
    }
}
