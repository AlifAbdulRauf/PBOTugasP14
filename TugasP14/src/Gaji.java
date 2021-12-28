import java.util.Scanner;


public class Gaji implements PTABC{
    public String noPegawai;
    public String pegawai;
    public String jabatan;
    public int gajiPokok;
    public int hariMasuk=0;
    public double potongan;
    public double totalGaji;

    Scanner input = new Scanner(System.in);




    @Override
    public void noPegawai() {
        System.out.print("Masukkan Nomor Pegawai: ");
        noPegawai = input.nextLine();
        System.out.println("Nomor Pegawai adalah : " + noPegawai);
    }

    @Override
    public void namaPegawai() {
        System.out.print("Masukkan Nama Pegawai : ");
        pegawai = input.nextLine();
        System.out.println("Nama Pegawai adalah : " + pegawai.toUpperCase());
        System.out.println("Panjang nama pegawai dalam string : "+ pegawai.length() + " Karakter"); 
        
    }
    
    @Override
    public void jabatan() {
        int j=0;
        while (j==0){
            System.out.print("Masukkan Jabatan Pegawai Restoran(Koki/Asisten_Koki/Pelayan/Kasir) : ");
            jabatan = input.next(); 
            if (jabatan.equalsIgnoreCase("koki") || jabatan.equalsIgnoreCase("pelayan") || jabatan.equalsIgnoreCase("asisten_koki")
                || jabatan.equalsIgnoreCase("kasir") ){
                    j=1;
            }
            else{
                System.out.println("Tidak ada karyawan dengan jabatan tersebut");
            }
        }
   
    }

    @Override
    public void gajiPokok() {
        if (jabatan.equalsIgnoreCase("koki")){
            gajiPokok = 15000000;
        }
        else if(jabatan.equalsIgnoreCase("asisten_koki")){
            gajiPokok = 8000000;
        }
        else if (jabatan.equalsIgnoreCase("pelayan")){
            gajiPokok = 5000000;
        }
        else if(jabatan.equalsIgnoreCase("kasir")){
            gajiPokok = 3500000;
        }
        System.out.println("Gaji Pokok " + jabatan + " Berjumlah : " + gajiPokok);
        
    }

    @Override
    public void jumlahHariMasuk() {
        int i = 0;

        while (i==0) {
            System.out.print("Masukkan Jumlah Hari Masuk Pegawai(0-30): ");
            hariMasuk = input.nextInt();
            if(hariMasuk >=31 || hariMasuk <0){
                System.out.println("Rentang hari dari 0 sampai 30");
            }
            else{
                i = 1;
            }
        }
        System.out.println("Jumlah hari Masuk Pegawai berjumlah : " + hariMasuk);   

    }

    @Override
    public void po() {
        int hariAbsen = 30 - hariMasuk;
        potongan = hariAbsen*gajiPokok/30; 
        System.out.println("Potongan Pegawai Berjumlah : " + potongan);
    }

    @Override
    public void totalGaji() {
        totalGaji = gajiPokok - potongan;
        System.out.println("Total Gaji Pegawai Berjumlah : " + totalGaji);
        
    }

}
