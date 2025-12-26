package ui.ft.ccit.faculty.transaksi.transaksi.view;

import ui.ft.ccit.faculty.transaksi.DataNotFoundException;
import ui.ft.ccit.faculty.transaksi.barang.model.Barang;
import ui.ft.ccit.faculty.transaksi.barang.view.BarangService;
import ui.ft.ccit.faculty.transaksi.transaksi.model.DetailTransaksi;
import ui.ft.ccit.faculty.transaksi.transaksi.model.DetailTransaksiId;
import ui.ft.ccit.faculty.transaksi.transaksi.model.Transaksi;
import ui.ft.ccit.faculty.transaksi.transaksi.model.TransaksiRepository;
import ui.ft.ccit.faculty.transaksi.transaksi.model.DetailTransaksiRepository;
import ui.ft.ccit.faculty.transaksi.transaksi.model.TransaksiRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TransaksiService {

    private final TransaksiRepository transaksiRepository;
    private final DetailTransaksiRepository detailTransaksiRepository;
    private final BarangService barangService;

    public TransaksiService(TransaksiRepository transaksiRepository,
                           DetailTransaksiRepository detailTransaksiRepository,
                           BarangService barangService) {
        this.transaksiRepository = transaksiRepository;
        this.detailTransaksiRepository = detailTransaksiRepository;
        this.barangService = barangService;
    }

    @Transactional
    public Transaksi createTransaksi(String idPelanggan, List<TransaksiRequest> items) {
        // Generate kode_transaksi (simple increment, in real app use proper sequence)
        String kodeTransaksi = generateKodeTransaksi();

        // For simplicity, use a fixed karyawan ID (K001)
        String idKaryawan = "K001";

        // Check stock for all items first
        for (TransaksiRequest item : items) {
            Barang barang = barangService.getById(item.getIdBarang());
            if (barang.getStok() < item.getJumlah()) {
                throw new IllegalArgumentException("Stok tidak cukup untuk barang " + item.getIdBarang());
            }
        }

        // Create transaksi
        Transaksi transaksi = new Transaksi(kodeTransaksi, LocalDateTime.now(), idPelanggan, idKaryawan);
        transaksi = transaksiRepository.save(transaksi);

        // Create detail transaksi and decrease stock
        for (TransaksiRequest item : items) {
            DetailTransaksiId detailId = new DetailTransaksiId(kodeTransaksi, item.getIdBarang());
            DetailTransaksi detail = new DetailTransaksi(detailId, item.getJumlah());
            detailTransaksiRepository.save(detail);

            // Decrease stock
            Barang barang = barangService.getById(item.getIdBarang());
            barang.setStok((short) (barang.getStok() - item.getJumlah()));
            barangService.save(barang);
        }

        return transaksi;
    }

    private String generateKodeTransaksi() {
        // Simple implementation - in real app, use database sequence or UUID
        return "J" + String.format("%03d", (int) (Math.random() * 1000));
    }
}
