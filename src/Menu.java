import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;


public class Menu extends Login {
    JFrame frame,frame2;
    JLabel label1,label2;
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultset;
    public static ResultSetMetaData resultsetmd;
    public static String sql = "";
    JPanel panel = new JPanel();
    JPanel panel2 = new JPanel();
    JButton button = new JButton();



    public void menuPanel(){
        frame = new JFrame();
        frame.setTitle("Keranjang Belanja");
        //label atas
        label1 = new JLabel("Selamat Datang di Keranjang Belanja\n\n");
        label2 = new JLabel("List Barang yang ada\n\n");
        button = new JButton("Add Belanjaan");
        //panel
        panel.setSize(200,100);
        panel.add(label1);

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

            //buat tabel
            String[] columnNames = { "ID Barang", "Nama Barang",  "Harga Barang","Jumlah Barang"};
            DefaultTableModel tabel1 = new DefaultTableModel(columnNames,0);

            while (resultset.next()){
                    String idbarang = resultset.getString("id_barang");
                    String namabarang = resultset.getString("nama_barang");
                    String jumlahbarang = "0";
                    String hargabarang = resultset.getString("harga_barang");

                    String[] data = {idbarang, namabarang,  hargabarang, jumlahbarang,};
                    tabel1.addRow(data);
            }
                //add ke panel
                tabeljadi = new JTable(tabel1);
                tabeljadi.setSize(200,100);
                //tabeljadi.setBounds(30, 40, 200, 100);
                JScrollPane sp = new JScrollPane(tabeljadi);
                sp.setSize(200,100);
                panel.add(sp);
                panel.add(button);

            //button buat menunjukkan selesainya pemilihan barang
                button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null,"Apakah Anda yakin belanja barang tersebut?");
                    int hitungrow = tabeljadi.getRowCount();


                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }


        //tambahkan panel ke frame
        frame.add(panel);
        frame.setSize(500, 900);
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
                keLogin();
                frame.setVisible(false);
            }
    }

    //disini interface receipt
    public void receipt() {
        frame2 = new JFrame();
        frame2.setTitle("Rincian Pembelian");
        frame2.setContentPane(new Menu().panel2);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setSize(400,500);
        frame2.setLayout(null);
        frame2.setVisible(true);
    }

    public void updatepembelian(){

    }
}
