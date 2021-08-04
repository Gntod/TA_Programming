import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Scanner;


public class Menu extends Login {
    JFrame frame;
    JLabel label1;
    JPanel panel;
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultset;
    public static ResultSetMetaData resultsetmd;
    public static String sql = "";
    public static Scanner scanner = new Scanner(System.in);

    public void menuPanel(){
        frame = new JFrame();
        frame.setTitle("Keranjang Belanja");

        //label atas
        label1 = new JLabel("Selamat Datang di Keranjang Belanja");

        //tabel
        JTable tabeljadi;
        try {
            //connect database
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ta_programming", "root", "");
            statement = connection.createStatement();

            // read database
            sql = "select * from barang";
            resultset = statement.executeQuery(sql);
            resultsetmd = resultset.getMetaData();
            int kolom = resultsetmd.getColumnCount();

            //buat tabel
            String[] columnNames = { "ID Barang", "Nama Barang", "Jumlah Barang", "Harga Barang" };
            DefaultTableModel tabel1 = new DefaultTableModel(columnNames,0);
            while (resultset.next()){
                String idbarang = resultset.getString("id_barang");
                String namabarang = resultset.getString("nama_barang");
                String jumlahbarang = resultset.getString("jumlah_barang");
                String hargabarang = resultset.getString("harga_barang");

                String[] data = {idbarang,namabarang,jumlahbarang,hargabarang};
                tabel1.addRow(data);

                tabeljadi = new JTable(tabel1);
                panel.add(tabeljadi);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        JPanel panel = new JPanel();
        panel.add(label1);
        // Initializing the JTable
        dbconnect dbhook = new dbconnect();
        dbhook.list();

        //tambahkan panel ke frame
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
