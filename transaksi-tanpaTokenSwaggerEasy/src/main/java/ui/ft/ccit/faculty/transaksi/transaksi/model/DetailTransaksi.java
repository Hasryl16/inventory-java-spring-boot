package ui.ft.ccit.faculty.transaksi.transaksi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detail_transaksi")
public class DetailTransaksi {

    @EmbeddedId
    private DetailTransaksiId id;

    @Column(name = "jumlah", nullable = false)
    private Short jumlah;

    protected DetailTransaksi() {
        // for JPA
    }

    public DetailTransaksi(DetailTransaksiId id, Short jumlah) {
        this.id = id;
        this.jumlah = jumlah;
    }

    // getters and setters

    public DetailTransaksiId getId() {
        return id;
    }

    public void setId(DetailTransaksiId id) {
        this.id = id;
    }

    public Short getJumlah() {
        return jumlah;
    }

    public void setJumlah(Short jumlah) {
        this.jumlah = jumlah;
    }
}
