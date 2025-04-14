import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminPage {
    private JButton Notification;
    private JButton courseUnitsButton;
    private JButton userbutton;
    private JButton timeTableButton;
    private JButton button1;
    private JButton editUserButton;
    private JButton logOutButton;
    private JPanel adminPage;



public adminPage() {
    JFrame frame = new JFrame("Admin Page");
    frame.setContentPane(adminPage);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 400);
    frame.setResizable(false);// or use frame.pack()
    frame.setVisible(true);
}
}





