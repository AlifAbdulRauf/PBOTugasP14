import java.util.Date;

public class TerimaGaji extends Gaji{
    @Override
    public void noPegawai() {
        System.out.print("Masukkan Nomor Pegawai: ");
        noPegawai = input.nextLine();
        System.out.println("Pegawai dengan nomor : " + noPegawai + " Menerima Gaji");
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

    }

    @Override
    public void po() {
        int hariAbsen = 30 - hariMasuk;
        potongan = hariAbsen*gajiPokok/30; 
        System.out.println(pegawai.concat(" Hadir Selama " + hariMasuk + " Hari Dalam Satu Bulan"));
    }

    @Override
    public void totalGaji() {
        totalGaji = gajiPokok - potongan;
        System.out.println("Pegawai Menerima Gaji Sejumlah: " + totalGaji);
        
    }

    public void tanggal(){
        Date tanggal = new Date();
        
        String str = String.format("Tanggal, Waktu : %tc",
        tanggal );
        System.out.println(str);
        }
        

    
}
