import javax.swing.*;

public class Attendence_page extends JFrame{
    private JPanel panel1;
    private JFormattedTextField formattedTextField1;
    private JComboBox ctype;
    private JComboBox cid;
    private JComboBox department;
    private JComboBox comboBox5;
    private JLabel a_type;
    private JLabel date;
    private JTextField sid;
    private JComboBox semester;
    private JButton InsertBtn;
    private JButton resetBtn;

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
