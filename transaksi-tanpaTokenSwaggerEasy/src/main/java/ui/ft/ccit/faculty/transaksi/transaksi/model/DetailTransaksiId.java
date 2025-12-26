package ui.ft.ccit.faculty.transaksi.transaksi.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DetailTransaksiId implements Serializable {

    private String kodeTransaksi;
    private String idBarang;

    protected DetailTransaksiId() {
        // for JPA
    }

    public DetailTransaksiId(String kodeTransaksi, String idBarang) {
        this.kodeTransaksi = kodeTransaksi;
        this.idBarang = idBarang;
    }

    // getters and setters

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailTransaksiId that = (DetailTransaksiId) o;
        return Objects.equals(kodeTransaksi, that.kodeTransaksi) &&
               Objects.equals(idBarang, that.idBarang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kodeTransaksi, idBarang);
    }
}
