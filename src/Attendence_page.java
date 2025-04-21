import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Attendence_page extends JFrame {
    // Original form components to match your .form file
    private JPanel panel1;
    private JFormattedTextField formattedTextField1; // Date field
    private JComboBox ctype; // Session Type
    private JComboBox cid; // Course ID
    private JComboBox department;
    private JComboBox comboBox5; // Status
    private JLabel a_type;
    private JLabel date;
    private JTextField sid; // Student ID
    private JComboBox semester;
    private JButton InsertBtn;
    private JButton resetBtn;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Tecmis";
    private static final String USER = "root";
    private static final int PASS = 1234; // Update with your password if needed

    // Additional component for technical officer selection
    private JComboBox toIdCombo;

    public Attendence_page() {
        // Setup frame properties
        setContentPane(panel1);
        setTitle("Insert Attendance");
        setSize(1000, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize the technical officer combo box - This will need to be added to your form
        toIdCombo = new JComboBox();

        // Set default date format for the date field
        formattedTextField1.setValue(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        // Initialize combo boxes
        initializeComboBoxes();

        // Load data from database
        loadCourseIds();
        loadTechnicalOfficers();

        // Add action listeners
        InsertBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertAttendance();
            }
        });

        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        setVisible(true);
    }

    // Initialize combo boxes with default values
    private void initializeComboBoxes() {
        // Initialize session type combo box
        ctype.addItem("Theory");
        ctype.addItem("Practical");

        // Initialize status combo box
        comboBox5.addItem("present");
        comboBox5.addItem("absent");

        // Initialize department combo box
        department.addItem("ICT");
        department.addItem("ET");
        department.addItem("BST");

        // Initialize semester combo box
        semester.addItem("L1S1");
        semester.addItem("L1S2");
        semester.addItem("L2S1");
        semester.addItem("L2S2");
        semester.addItem("L3S1");
        semester.addItem("L3S2");
        semester.addItem("L4S1");
        semester.addItem("L4S2");

    }

    // Method to load course IDs from database
    private void loadCourseIds() {
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, String.valueOf(PASS));

            // Execute a query
            String sql = "SELECT Course_code FROM Course_unit";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Clear existing items
            cid.removeAllItems();

            // Add course codes to combo box
            while (rs.next()) {
                cid.addItem(rs.getString("Course_code"));
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Handle SQL errors
            JOptionPane.showMessageDialog(this, "Database error: " + se.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            se.printStackTrace();
        } catch (Exception e) {
            // Handle other errors
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Method to load technical officers from database
    private void loadTechnicalOfficers() {
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, String.valueOf(PASS));

            // Execute a query
            String sql = "SELECT TO_id FROM Technical_officer";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Clear existing items
            toIdCombo.removeAllItems();

            // Add technical officer IDs to combo box
            while (rs.next()) {
                toIdCombo.addItem(rs.getString("TO_id"));
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Handle SQL errors
            JOptionPane.showMessageDialog(this, "Database error: " + se.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            se.printStackTrace();
        } catch (Exception e) {
            // Handle other errors
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Method to insert attendance into database
    private void insertAttendance() {
        try {
            // Get values from form
            String courseCode = (String) cid.getSelectedItem();
            String sessionTypeValue = (String) ctype.getSelectedItem();
            String date = formattedTextField1.getText();
            String status = (String) comboBox5.getSelectedItem();
            String studentId = sid.getText();
            String toId = (String) toIdCombo.getSelectedItem();

            // Validate inputs
            if (courseCode == null || sessionTypeValue == null || date.isEmpty() ||
                    status == null || studentId.isEmpty() || toId == null) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, String.valueOf(PASS));

            // Validate student ID exists in database
            if (!isStudentIdValid(studentId, conn)) {
                JOptionPane.showMessageDialog(this, "Student ID not found in database",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if student is enrolled in the course
            if (!isStudentEnrolledInCourse(studentId, courseCode, conn)) {
                JOptionPane.showMessageDialog(this, "Student is not enrolled in this course",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Prepare SQL statement for insertion
            String sql = "INSERT INTO Attendance (Course_code, Session_Type, Session_date, Status, Student_id, TO_id, Medical_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, NULL)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, courseCode);
            stmt.setString(2, sessionTypeValue);
            stmt.setString(3, date);
            stmt.setString(4, status);
            stmt.setString(5, studentId);
            stmt.setString(6, toId);

            // Execute update
            int rowsAffected = stmt.executeUpdate();

            // Check if insertion was successful
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Attendance insertion successful!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                resetForm();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to insert attendance",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Close resources
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Handle SQL errors
            JOptionPane.showMessageDialog(this, "Database error: " + se.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            se.printStackTrace();
        } catch (Exception e) {
            // Handle other errors
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Method to check if student ID exists in database
    private boolean isStudentIdValid(String studentId, Connection conn) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Student WHERE Student_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, studentId);
        ResultSet rs = stmt.executeQuery();

        boolean isValid = false;
        if (rs.next()) {
            isValid = rs.getInt(1) > 0;
        }

        rs.close();
        stmt.close();
        return isValid;
    }

    // Method to check if student is enrolled in course
    private boolean isStudentEnrolledInCourse(String studentId, String courseCode, Connection conn) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Student_course_unit WHERE Student_id = ? AND Course_Code = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, studentId);
        stmt.setString(2, courseCode);
        ResultSet rs = stmt.executeQuery();

        boolean isEnrolled = false;
        if (rs.next()) {
            isEnrolled = rs.getInt(1) > 0;
        }

        rs.close();
        stmt.close();
        return isEnrolled;
    }

    // Method to reset form fields
    private void resetForm() {
        if (cid.getItemCount() > 0) cid.setSelectedIndex(0);
        if (ctype.getItemCount() > 0) ctype.setSelectedIndex(0);
        formattedTextField1.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        sid.setText("");
        if (comboBox5.getItemCount() > 0) comboBox5.setSelectedIndex(0);
        if (toIdCombo.getItemCount() > 0) toIdCombo.setSelectedIndex(0);
        if (department.getItemCount() > 0) department.setSelectedIndex(0);
        if (semester.getItemCount() > 0) semester.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        try {
            // Set look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Create and display application
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new Attendence_page();
                }
            });
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException |
                 IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}