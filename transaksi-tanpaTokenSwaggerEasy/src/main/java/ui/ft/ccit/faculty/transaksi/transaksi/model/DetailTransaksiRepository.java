package ui.ft.ccit.faculty.transaksi.transaksi.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailTransaksiRepository extends JpaRepository<DetailTransaksi, DetailTransaksiId> {
}
