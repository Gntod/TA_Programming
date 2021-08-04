import javax.swing.*;


public class main {

  public static JFrame login = new JFrame("Login");
    public static void main(String[] args) {
        Menu menuke = new Menu();
        menuke.menuPanel();
        //keLogin();
      }

    public static void keLogin(){
        login.setContentPane(new Login().panelLogin);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.pack();
        login.setSize(400,500);
        login.setLayout(null);
        login.setVisible(true);
    }

}
