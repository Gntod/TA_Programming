import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Scanner;
import java.util.Vector;

public class dbconnect extends Menu{
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultset;
    public static ResultSetMetaData resultsetmd;
    public static String sql = "";
    public static Scanner scanner = new Scanner(System.in);


    public static void menu(){
        System.out.println("1. insert");
        System.out.println("2. show");
        System.out.println("3. update");
        System.out.println("4. delete");
        System.out.println("masukkan pilihan");
        String input = scanner.next();

        if (input.equals("2")){
          //  list();
        }
        else if (input.equals("1")){
            insertlist();
        }
        else if (input.equals("4")){
            deletelist();
        }
        else if (input.equals("3")){
            updatelist();
        }
    }

    public void list() {
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

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        }

    public static void insertlist(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ta_programming", "root", "");
            statement = connection.createStatement();
            System.out.print("masukkan nama barang = ");
            String nama = scanner.next();
            System.out.print("masukkan jumlah barang = ");
            String jumlah = scanner.next();
            System.out.print("masukkan harga barang = ");
            String harga = scanner.next();
            sql = "insert into barang (nama_barang,jumlah_barang,harga_barang) values('"+nama+"','"+jumlah+"','"+harga+"')";
            statement.executeUpdate(sql);
            System.out.print("Success!\n");
            menu();
        }catch (Exception e){
            e.printStackTrace();
        }
        menu();
    }

    public static void deletelist(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ta_programming", "root", "");
            statement = connection.createStatement();
            sql = "select * from barang";
            resultset = statement.executeQuery(sql);
            while (resultset.next()){
                String namabarang = resultset.getString("nama_barang");
                System.out.println(namabarang);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ta_programming", "root", "");
            statement = connection.createStatement();
            System.out.println("masukkan nama barang yang akan di delete");
            String nama = scanner.next();
            sql = "DELETE from barang where nama_barang = '"+nama+"'";
            statement.executeUpdate(sql);
            System.out.println("Success Delete!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updatelist(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ta_programming", "root", "");
            statement = connection.createStatement();
            sql = "select * from barang";
            resultset = statement.executeQuery(sql);
            while (resultset.next()){
                String namabarang = resultset.getString("nama_barang");
                System.out.println(namabarang);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Pilih nama barang");
        String pilihnama = scanner.next();
        System.out.println("Pilih apa yang ingin anda update");
        System.out.println("1. Nama Barang");
        System.out.println("2. Jumlah Barang");
        System.out.println("3. Harga Barang");
        System.out.println("4. Keluar");
        String pilihupdate = scanner.next();

        if (pilihupdate.equals("1")){
            try {
                System.out.println("Nama baru = ");
                String namabaru = scanner.next();
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ta_programming", "root", "");
                statement = connection.createStatement();
                sql = "UPDATE barang set nama_barang = '"+namabaru+"' WHERE nama_barang = '"+pilihnama+"'";
                statement.executeUpdate(sql);
                System.out.println("Update Berhasil!");
            }catch (Exception e){
                e.printStackTrace();
            }
            menu();
        }
        else if (pilihupdate.equals("2")){
            try {
                System.out.println("Jumlah baru = ");
                String jumlahbaru = scanner.next();
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ta_programming", "root", "");
                statement = connection.createStatement();
                sql = "UPDATE barang set jumlah_barang = '"+jumlahbaru+"' WHERE nama_barang = '"+pilihnama+"'";
                statement.executeUpdate(sql);
                System.out.println("Update Berhasil!");
            }catch (Exception e){
                e.printStackTrace();
            }
            menu();
        }
        else if (pilihupdate.equals("3")){
            try {
                System.out.println("Harga baru = ");
                String hargabaru = scanner.next();
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ta_programming", "root", "");
                statement = connection.createStatement();
                sql = "UPDATE barang set harga_barang = '"+hargabaru+"' WHERE nama_barang = '"+pilihnama+"'";
                statement.executeUpdate(sql);
                System.out.println("Update Berhasil!");
            }catch (Exception e){
                e.printStackTrace();
            }
            menu();
        }
        else if (pilihupdate.equals("4")){
            System.exit(0);
        }
    }
    public void getRowCount(){

    }
}
