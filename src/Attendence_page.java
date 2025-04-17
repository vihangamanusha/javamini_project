import javax.swing.*;

public class Attendence_page extends JFrame{
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField labelTextField;
    private JTextField textField4;
    private JComboBox comboBox1;

    public Attendence_page() {
        setContentPane(panel1);
        setTitle("Insert Attedance");
        setSize(1000,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {

        Attendence_page insertAttendance=new Attendence_page();
    }



}
