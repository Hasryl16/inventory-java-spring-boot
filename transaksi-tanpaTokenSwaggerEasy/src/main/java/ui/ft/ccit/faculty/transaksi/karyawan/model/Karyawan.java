package ui.ft.ccit.faculty.transaksi.karyawan.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "karyawan")
public class Karyawan {

    @Id
    @Column(name = "id_karyawan", length = 4)
    private String idKaryawan;

    @Column(name = "nama", length = 20)
    private String nama;

    @Column(name = "jenis_kelamin", length = 1)
    private String jenisKelamin;

    @Column(name = "alamat", length = 50)
    private String alamat;

    @Column(name = "telepon", length = 15)
    private String telepon;

    @Column(name = "tgl_lahir")
    @Temporal(TemporalType.DATE)
    private Date tglLahir;

    @Column(name = "gaji")
    private Double gaji;

    @Column(name = "gmail", length = 50)
    private String gmail;

    @Column(name = "password", length = 255)
    private String password;

    protected Karyawan() {
        // for JPA
    }

    public Karyawan(String idKaryawan, String nama, String jenisKelamin, String alamat,
            String telepon, Date tglLahir, Double gaji, String gmail, String password) {
        this.idKaryawan = idKaryawan;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
        this.telepon = telepon;
        this.tglLahir = tglLahir;
        this.gaji = gaji;
        this.gmail = gmail;
        this.password = password;
    }

    // getters & setters

    public String getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(String idKaryawan) {
        this.idKaryawan = idKaryawan;
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

    public Date getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(Date tglLahir) {
        this.tglLahir = tglLahir;
    }

    public Double getGaji() {
        return gaji;
    }

    public void setGaji(Double gaji) {
        this.gaji = gaji;
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
