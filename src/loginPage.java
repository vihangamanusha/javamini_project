import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginPage {
    private JTextField username;
    private JTextField password;
    private JButton login;
    private JButton RESET;
    private JPanel loginpage;

    public loginPage() {
        RESET.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username.setText("");
                password.setText("");

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("loginPage");
        frame.setContentPane(new loginPage().loginpage);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
