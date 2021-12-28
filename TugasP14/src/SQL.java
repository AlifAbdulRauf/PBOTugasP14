import java.util.InputMismatchException;
import java.util.Scanner;
import com.mysql.cj.protocol.Resultset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class SQL {
    String jabatanpegawai;
    int nopegawai;
    String namapegawai;
    int gajipokok = 0;
    int harimasuk;
    int potongann;
    int totalgaji;

    static Connection conn;
    String url = "jdbc:mysql://localhost:3306/tugasp14";
    Scanner input = new Scanner(System.in);

    public void viewData() throws SQLException{
        String text1 = "\n===Daftar Seluruh Data Pegawai===";
		System.out.println(text1.toUpperCase());
						
		String sql ="SELECT * FROM pegawai";
        conn = DriverManager.getConnection(url,"root","");
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		
		while(result.next()){
			System.out.print("\nNo. Pegawai\t: ");
            System.out.print(result.getInt("nopegawai"));
            System.out.print("\nNama\t: ");
            System.out.print(result.getString("namapegawai"));
            System.out.print("\nJabatan\t: ");
            System.out.print(result.getString("jabatanpegawai"));
            System.out.print("\nGaji Pokok\t: ");
            System.out.print(result.getInt("gajipokok"));
            System.out.print("\nJumlah Hari Masuk \t: ");
            System.out.print(result.getInt("harimasuk"));
            System.out.print("\nPotongan Gaji\t: ");
            System.out.print(result.getInt("potongann"));
            System.out.print("\nTotal Gaji\t: ");
            System.out.print(result.getInt("totalgaji"));
            System.out.print("\n");
		}
    }

    public void insertData() throws SQLException{
		String text2 = "\n===Tambah Data Pegawai===";
		System.out.println(text2.toUpperCase());

		try {
		
		System.out.print("\nNo. Pegawai\t: ");
		nopegawai = input.nextInt();
		System.out.print("Nama\t: ");
		namapegawai = input.next();
        int j=0;

        while (j==0){
            System.out.print("Jabatan Pegawai Restoran(Koki/Asisten_Koki/Pelayan/Kasir) : ");
            jabatanpegawai = input.next(); 
            if (jabatanpegawai.equalsIgnoreCase("koki") || jabatanpegawai.equalsIgnoreCase("pelayan") || jabatanpegawai.equalsIgnoreCase("asisten_koki")
                || jabatanpegawai.equalsIgnoreCase("kasir") ){
                    j=1;
            }
            else{
                System.out.println("Tidak ada karyawan dengan jabatan tersebut");
            }
        }


        if (jabatanpegawai.equalsIgnoreCase("koki")){
            gajipokok = 15000000;
        }
        else if(jabatanpegawai.equalsIgnoreCase("asisten_koki")){
            gajipokok = 8000000;
        }
        else if (jabatanpegawai.equalsIgnoreCase("pelayan")){
            gajipokok = 5000000;
        }
        else if(jabatanpegawai.equalsIgnoreCase("kasir")){
            gajipokok = 3500000;
        }
        System.out.print("Gaji Pokok\t: ");
        System.out.println(gajipokok);

        int i = 0;

        while (i==0) {
            System.out.print("Jumlah Hari Masuk Pegawai(0-30): ");
            harimasuk = input.nextInt();
            if(harimasuk >=31 || harimasuk <0){
                System.out.println("Rentang hari dari 0 sampai 30");
            }
            else{
                i = 1;
            }
        }

        int hariAbsen = 30 - harimasuk;
        potongann = hariAbsen*gajipokok/30; 
        System.out.print("Potongan Gaji\t: ");
        System.out.print(potongann);

        totalgaji = gajipokok - potongann;
        System.out.print("\nTotal Gaji\t: ");
        System.out.print(totalgaji);

        System.out.print("\n");		
		
		String sql = "INSERT INTO pegawai (nopegawai, namapegawai, jabatanpegawai, gajipokok, harimasuk, potongann, totalgaji) VALUES ('"+nopegawai+"','"+namapegawai+"','"+jabatanpegawai+"','"+gajipokok+"','"+harimasuk+"','"+potongann+"','"+totalgaji+"')";
		conn = DriverManager.getConnection(url,"root","");			
        Statement statement = conn.createStatement();
        statement.execute(sql);
        System.out.println("Berhasil input data");
	
	    } catch (SQLException e) {
	        System.err.println("Terjadi kesalahan input data");
	    } catch (InputMismatchException e) {
	    	System.err.println("Inputlah dengan angka saja");
	   	}
    }

    public void updateData() throws SQLException{
        String text3 = "\n===Ubah Data Pegawai===";
		System.out.println(text3.toUpperCase());
		
		try {
            viewData();
            System.out.print("Masukkan No. Pegawai yang akan diubah : ");
            Integer nopegawai = Integer.parseInt(input.nextLine());
            
            String sql = "SELECT * FROM pegawai WHERE nopegawai = " +nopegawai;
            conn = DriverManager.getConnection(url,"root","");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            if(result.next()){
                
                System.out.print("Nama ["+result.getString("namapegawai")+"]\t: ");
                String namapegawai = input.nextLine();
                
                System.out.print("Jabatan ["+result.getString("jabatanpegawai")+"]\t: ");
                String jabatanpegawai = input.nextLine();

                System.out.print("Gaji Pokok ["+result.getString("gajipokok")+"]\t: ");
                int gajipokok = input.nextInt();
                
                System.out.print("Hari Masuk ["+result.getString("harimasuk")+"]\t: ");
                int harimasuk = input.nextInt();

                System.out.print("Potongan Gaji ["+result.getString("potongann")+"]\t: ");
                int potongann = input.nextInt();
                
                System.out.print("Total gaji ["+result.getString("totalgaji")+"]\t: ");
                int totalgaji = input.nextInt();
                   
                sql = "UPDATE pegawai SET namapegawai='"+namapegawai+"',jabatanpegawai= '"+jabatanpegawai+"',gajipokok= '"+gajipokok+"',harimasuk= '"+harimasuk+"',potongann= '"+potongann+"',totalgaji= '"+totalgaji+"' WHERE nopegawai='"+nopegawai+"'";
                //System.out.println(sql);

                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Berhasil memperbaharui data pegawai (No. Pegawai "+nopegawai+")");
                }
            }
            statement.close();        
        } catch (SQLException e) {
            System.err.println("Terjadi kesalahan dalam mengedit data");
            System.err.println(e.getMessage());
        }
    }

    public void deleteData(){
		String text4 = "\n===Hapus Data Pegawai===";
		System.out.println(text4.toUpperCase());
		
		Scanner input = new Scanner (System.in);
		
		try{
	        viewData();
	        System.out.print("Ketik No. Pegawai yang akan Anda Hapus : ");
	        Integer nopegawai= Integer.parseInt(input.nextLine());
	        
	        String sql = "DELETE FROM pegawai WHERE nopegawai = "+ nopegawai;
	        conn = DriverManager.getConnection(url,"root","");
            Statement statement = conn.createStatement();
	        //ResultSet result = statement.executeQuery(sql);
	        
	        if(statement.executeUpdate(sql) > 0){
	            System.out.println("Berhasil menghapus data pegawai (No. Pegawai "+nopegawai+")");
	        }
	   }catch(SQLException e){
	        System.out.println("Terjadi kesalahan dalam menghapus data pegawai");
	        }
    }
    
    public void seacrhData() throws SQLException{
		String text5 = "\n===Cari Data Pegawai===";
		System.out.println(text5.toUpperCase());
		
		Scanner Input = new Scanner (System.in);
				
		System.out.print("Masukkan Nama Pegawai : ");
        
		String keyword = input.nextLine();
        Statement statement = conn.createStatement();
        String sql = "SELECT * FROM pegawai WHERE namapegawai LIKE '%"+keyword+"%'";
        conn = DriverManager.getConnection(url,"root","");
        ResultSet result = statement.executeQuery(sql); 
                
        while(result.next()){
			System.out.print("\nNo. Pegawai\t: ");
            System.out.print(result.getInt("nopegawai"));
            System.out.print("\nNama\t: ");
            System.out.print(result.getString("namapegawai"));
            System.out.print("\nJabatan\t: ");
            System.out.print(result.getString("jabatanpegawai"));
            System.out.print("\nGaji Pokok\t: ");
            System.out.print(result.getInt("gajipokok"));
            System.out.print("\nJumlah Hari Masuk \t: ");
            System.out.print(result.getInt("harimasuk"));
            System.out.print("\nPotongan Gaji\t: ");
            System.out.print(result.getInt("potongann"));
            System.out.print("\nTotal Gaji\t: ");
            System.out.print(result.getInt("totalgaji"));
            System.out.print("\n");
        }
	}
    


}
