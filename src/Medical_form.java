import javax.swing.*;

public class Medical_form extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JPanel panel2;

    public Medical_form(){

        setContentPane(panel2);
        setTitle("Insert Your Medical");
        setSize(1000,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        Medical_form medicalForm=new Medical_form();
    }
}
