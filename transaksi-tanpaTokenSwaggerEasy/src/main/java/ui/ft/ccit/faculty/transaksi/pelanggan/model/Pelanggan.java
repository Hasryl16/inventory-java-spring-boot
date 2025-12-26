package ui.ft.ccit.faculty.transaksi.pelanggan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pelanggan")
public class Pelanggan {

    @Id
    private String idPelanggan;
    private String nama;
    private String jenisKelamin;
    private String alamat;
    private String telepon;
    private String tglLahir;
    private String jenisPelanggan;
    private String gmail;
    private String password;

    // Constructors
    public Pelanggan() {}

    public Pelanggan(String idPelanggan, String nama, String jenisKelamin, String alamat, String telepon, String tglLahir, String jenisPelanggan, String gmail, String password) {
        this.idPelanggan = idPelanggan;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
        this.telepon = telepon;
        this.tglLahir = tglLahir;
        this.jenisPelanggan = jenisPelanggan;
        this.gmail = gmail;
        this.password = password;
    }

    // Getters and Setters
    public String getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(String idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getJenisPelanggan() {
        return jenisPelanggan;
    }

    public void setJenisPelanggan(String jenisPelanggan) {
        this.jenisPelanggan = jenisPelanggan;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
