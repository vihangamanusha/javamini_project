import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class edit_user {
    private JPanel edituserPage;
    private JTextField Fname;
    private JTextField Lname;
    private JTextField Email;
    private JTextField Phoneno;
    private JTextField password;
    private JTextField photo;
    private JButton uploadButton;
    private JButton UPDATEButton;
    private JButton RESETButton;
    private JButton button1;

    public edit_user() {
        JFrame frame = new JFrame("Edit User");
        frame.setContentPane(edituserPage);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        //frame.setResizable(false);
        frame.setVisible(true);


        RESETButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fname.setText("");
                Lname.setText("");
                Email.setText("");
                Phoneno.setText("");
                password.setText("");
                photo.setText("");

            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new adminPage();
            }
        });
    }
}
