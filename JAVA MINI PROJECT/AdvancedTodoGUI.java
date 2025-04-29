import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class AdvancedTodoGUI {
    private JFrame frame;
    private JPanel taskPanel;
    private JTextField taskField;
    private List<TaskItem> tasks = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdvancedTodoGUI().createAndShowGUI());
    }

    public void createAndShowGUI() {
        frame = new JFrame("🟣 Night Tasker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 650);
        frame.setLayout(new BorderLayout());

        Color bgDark = new Color(28, 27, 34);
        Color purple = new Color(138, 43, 226);

        // Header
        JLabel title = new JLabel("Night Tasker 🌙", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(purple);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        frame.add(title, BorderLayout.NORTH);

        // Task panel (center)
        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        taskPanel.setBackground(bgDark);

        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);
        scrollPane.getViewport().setBackground(bgDark);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Input panel (bottom)
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(bgDark);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        taskField = new JTextField();
        taskField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        taskField.setBackground(new Color(50, 50, 60));
        taskField.setForeground(Color.WHITE);
        taskField.setCaretColor(Color.WHITE);
        taskField.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        JButton addButton = new JButton("➕");
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(purple);
        addButton.setFocusPainted(false);
        addButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        addButton.addActionListener(e -> addTask(taskField.getText()));

        inputPanel.add(taskField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);
        frame.add(inputPanel, BorderLayout.SOUTH);

        frame.getContentPane().setBackground(bgDark);
        frame.setVisible(true);
    }

    private void addTask(String text) {
        if (text == null || text.trim().isEmpty()) return;

        TaskItem task = new TaskItem(text);
        tasks.add(task);
        taskPanel.add(task.getPanel());
        taskPanel.revalidate();
        taskPanel.repaint();
        taskField.setText("");
    }

    private class TaskItem {
        private JPanel panel;
        private JCheckBox checkBox;
        private JButton deleteButton;
        private String text;

        public TaskItem(String text) {
            this.text = text;

            panel = new JPanel(new BorderLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
            panel.setBackground(new Color(45, 45, 60));
            panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

            checkBox = new JCheckBox(text);
            checkBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            checkBox.setBackground(new Color(45, 45, 60));
            checkBox.setForeground(Color.WHITE);
            checkBox.setFocusPainted(false);
            checkBox.addActionListener(e -> toggleStrike());

            deleteButton = new JButton("🗑");
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setBackground(new Color(90, 0, 120));
            deleteButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            deleteButton.setFocusPainted(false);
            deleteButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            deleteButton.addActionListener(e -> removeTask());

            panel.add(checkBox, BorderLayout.CENTER);
            panel.add(deleteButton, BorderLayout.EAST);
        }

        public JPanel getPanel() {
            return panel;
        }

        @SuppressWarnings("unchecked")
        private void toggleStrike() {
            Font baseFont = checkBox.getFont();
            Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) baseFont.getAttributes();

            if (checkBox.isSelected()) {
                attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
                checkBox.setForeground(Color.LIGHT_GRAY);
            } else {
                attributes.remove(TextAttribute.STRIKETHROUGH);
                checkBox.setForeground(Color.WHITE);
            }

            checkBox.setFont(baseFont.deriveFont(attributes));
        }

        private void removeTask() {
            int confirm = JOptionPane.showConfirmDialog(frame, "Delete this task?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                taskPanel.remove(panel);
                tasks.remove(this);
                taskPanel.revalidate();
                taskPanel.repaint();
            }
        }
    }
}
