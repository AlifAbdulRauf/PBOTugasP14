
import java.util.Scanner;
import com.mysql.cj.protocol.Resultset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Program {

    static Connection conn;

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        String pilihan1;
        Integer kondisi = 0;

        String url = "jdbc:mysql://localhost:3306/tugasp14";
        try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,"root","");
			System.out.println("Class Driver ditemukan"); 

            SQL sql = new SQL();
            
			while (kondisi==0) {
				System.out.println("\n------------------");
				System.out.println("Database Pegawai");
				System.out.println("------------------");
				System.out.println("1. Lihat Data Pegawai");
				System.out.println("2. Tambah Data Pegawai");
				System.out.println("3. Ubah Data Pegawai");
				System.out.println("4. Hapus Data Pegawai");
				System.out.println("5. Cari Data Pegawai");
				
				System.out.print("\nPilihan anda (1/2/3/4/5): ");
				pilihan1 = input.next();
				
				switch (pilihan1) {
				case "1":
					sql.viewData();
					break;
				case "2":
					sql.insertData();
					break;
				case "3":
					sql.updateData();
					break;
				case "4":
					sql.deleteData();
					break;
				case "5":
					sql.seacrhData();
					break;
				default:
					System.err.println("\nInput anda tidak ditemukan\nSilakan pilih [1-5]");
				}
                System.out.print("\nApakah Anda ingin melanjutkan [y/n]? ");
				pilihan1 = input.next();
				if(pilihan1.equalsIgnoreCase("y")){
                    kondisi = 0;
                }
                else if(pilihan1.equalsIgnoreCase("n")){
                    kondisi = 1;
                }
            }
        }
		catch(ClassNotFoundException ex) {
			System.err.println("Driver Error");
			System.exit(0);
        }
        catch(SQLException e){
            System.err.println("Tidak berhasil koneksi");
        }
    }
}
