import javax.swing.*;
import java.awt.*;
import com.github.lgooddatepicker.components.DatePicker;

public class Medical_form extends JFrame {
    //private JPanel datePanel;
    private JPanel panel2;
    private DatePicker datePicker;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JPanel datePickerPanel;

    public Medical_form() {
        // Initialize panels
        panel2 = new JPanel(new BorderLayout());
        datePickerPanel = new JPanel();

        // Initialize components
        datePicker = new DatePicker();
        comboBox1 = new JComboBox();
        comboBox2 = new JComboBox();

        // Add datePicker to datePickerPanel
        datePickerPanel.add(datePicker);

        // Add some example items to comboboxes
        comboBox1.addItem("Option 1");
        comboBox1.addItem("Option 2");
        comboBox2.addItem("Choice A");
        comboBox2.addItem("Choice B");

        // Create a panel for combo boxes
        JPanel comboPanel = new JPanel(new FlowLayout());
        comboPanel.add(new JLabel("Selection 1:"));
        comboPanel.add(comboBox1);
        comboPanel.add(new JLabel("Selection 2:"));
        comboPanel.add(comboBox2);

        // Add panels to panel2
        panel2.add(datePickerPanel, BorderLayout.NORTH);
        panel2.add(comboPanel, BorderLayout.CENTER);

        // Set content pane
        setContentPane(panel2);

        // Set up frame
        setTitle("Insert Your Medical");
        setSize(1000, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure thread safety
        SwingUtilities.invokeLater(() -> {
            Medical_form medicalForm = new Medical_form();
            medicalForm.setVisible(true);
        });
    }
}