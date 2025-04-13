import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class loginPage {
    private JTextField username;
    private JPasswordField passwordField1;
    private JButton login;
    private JButton RESET;
    private JPanel loginpage;

    public loginPage() {
        RESET.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username.setText("");
                passwordField1.setText("");
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = username.getText();
                String pass = new String(passwordField1.getPassword());

                if (user.isEmpty() || pass.isEmpty()) {
                    JOptionPane.showMessageDialog(loginpage, "Please enter a valid username/password");
                    return;
                }

                try {
                    Connection conn = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/vihang", "root", "801@Vihanga");

                    String sql = "SELECT * FROM data WHERE username = ? AND password = ?";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setString(1, user);
                    pst.setString(2, pass);

                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Login successful!");
                        conn.close();

                        // Open next page
                        openNextPage();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password.");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
                }
            }
        });
    }

    private void openNextPage() {
        JFrame frame = new JFrame("Welcome Page");
        JLabel label = new JLabel("Welcome, you are logged in!", SwingConstants.CENTER);
        frame.add(label);
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Page");
        frame.setContentPane(new loginPage().loginpage);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
