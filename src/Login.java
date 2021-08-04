import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends main{
    private JButton loginButton;
    private JTextField textNamalogin;
    public JPanel panelLogin;
    public static String custName;

    public Login() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textNamalogin.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Mohon Diisi!","ALERT",JOptionPane.WARNING_MESSAGE);
                }else{
                    setNamecust(textNamalogin.getText());
                    login.setVisible(false);

                    Menu keMenu = new Menu();
                    keMenu.alertKemenu();


                }

            }
        });
    }

    public void setNamecust(String custName){
        this.custName = custName;
    }
    public String getTextcust(){
        return this.custName;
    }
}
