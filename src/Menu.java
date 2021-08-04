import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Menu extends Login {
    JFrame frame;
    JTable tabel1;
    JLabel label1;
    JPanel panel;

    public void menuPanel(){frame = new JFrame();

        frame.setTitle("Keranjang Belanja");

        //label atas
        label1 = new JLabel("Selamat Datang di Keranjang Belanja");

        // Initializing the JTable
        dbconnect dbhook = new dbconnect();
        dbhook.list();


        //panel dan scrollpanel
        JPanel panel = new JPanel();
        JScrollPane sp = new JScrollPane(tabel1);

        //add to panel
        panel.add(label1);
        panel.add(sp);

        // adding all item to JScrollPane

        //sp.add(table1);
       // sp.add(label1);

        // add to frame
        frame.add(panel);
        frame.setSize(500, 200);
        frame.setVisible(true);
    }

    public void alertKemenu() {
        Login hookmenu = new Login();
        String a = hookmenu.getTextcust();
        int opsi1 = JOptionPane.showConfirmDialog(null,"apakah nama sudah benar? "+a,"Pilih Opsi",JOptionPane.YES_NO_OPTION);
            if (opsi1==JOptionPane.YES_OPTION){
                menuPanel();
                JOptionPane.showMessageDialog(null,"Selamat Datang "+a);
            }
            else if (opsi1==JOptionPane.NO_OPTION){
                frame.setVisible(false);
                keLogin();
            }
    }



}
