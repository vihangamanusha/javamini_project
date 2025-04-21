import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMnagementPage {
    private JPanel UserManagemetPage;
    private JTextField ID;
    private JTextField Username;
    private JTextField firstname;
    private JTextField lastname;
    private JTextField password;
    private JTextField phoneno;
    private JTextField email;
    private JComboBox role;
    private JTextField gender;
    private JComboBox level;
    private JButton INSERTButton;
    private JButton RESETButton;
    private JButton UPDATEButton;
    private JButton REMOVEButton;
    private JTextField textField9;
    private JButton SEARCHButton;


    public UserMnagementPage() {
        JFrame frame = new JFrame("User Management Page");
        frame.setContentPane(UserManagemetPage);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(1000, 500);
        frame.setResizable(false);
        frame.setVisible(true);

        RESETButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ID.setText("");
                Username.setText("");
                firstname.setText("");
                lastname.setText("");
                password.setText("");
                phoneno.setText("");
                email.setText("");
                role.setSelectedIndex(0);
                gender.setText("");
                level.setSelectedIndex(0);
                textField9.setText("");

            }
        });
    }
}
