import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class notificationpage {
    private JTextField title;
    private JTextArea content;
    private JComboBox catogery;
    private JButton RESETButton;
    private JButton INSERTButton;
    private JButton UPDATEButton;
    private JTextField attachment;
    private JPanel notificationpanel;
    private JButton button1;


    public notificationpage() {
        JFrame frame = new JFrame("Notification Page");
        frame.setContentPane(notificationpanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        RESETButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                   title.setText("");
                   content.setText("");
                   catogery.setSelectedIndex(0);
                   attachment.setText("");
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
