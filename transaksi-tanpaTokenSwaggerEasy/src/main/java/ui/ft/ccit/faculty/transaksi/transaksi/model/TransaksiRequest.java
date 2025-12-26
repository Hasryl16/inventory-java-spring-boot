package ui.ft.ccit.faculty.transaksi.transaksi.model;

public class TransaksiRequest {
    private String idBarang;
    private Short jumlah;

    public TransaksiRequest() {}

    public TransaksiRequest(String idBarang, Short jumlah) {
        this.idBarang = idBarang;
        this.jumlah = jumlah;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public Short getJumlah() {
        return jumlah;
    }

    public void setJumlah(Short jumlah) {
        this.jumlah = jumlah;
    }
}
