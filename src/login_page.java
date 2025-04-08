import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login_page {
    private JPanel loginpage;
    private JPanel toppanel;
    private JPanel bottompanel;
    private JTextField TextField1;
    private JButton ADDButton;
    private JButton RESETButton;
    private JButton SUBButton;
    private JTextField TextField2;
    private JTextField TextField3;

    public login_page() {
        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num1 = Integer.parseInt(TextField1.getText());
                int num2 = Integer.parseInt(TextField2.getText());
                int result1 = num1 + num2;

                TextField3.setText(String.valueOf(result1));
            }
        });
        SUBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num1 = Integer.parseInt(TextField1.getText());
                int num2 = Integer.parseInt(TextField2.getText());
                int result1 = num1 - num2;

                TextField3.setText(String.valueOf(result1));
            }
        });
        RESETButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextField1.setText("");
                TextField2.setText("");
                TextField3.setText("");


            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("login_page");
        frame.setResizable(false);
        frame.setContentPane(new login_page().loginpage);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
