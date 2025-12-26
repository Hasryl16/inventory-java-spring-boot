package ui.ft.ccit.faculty.transaksi.karyawan.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KaryawanRepository extends JpaRepository<Karyawan, String> {

    // cari berdasarkan nama mengandung kata tertentu
    List<Karyawan> findByNamaContainingIgnoreCase(String keyword);

    // cari berdasarkan gmail
    Optional<Karyawan> findByGmail(String gmail);

    // hitung berapa banyak karyawan dengan idKaryawan dalam daftar tertentu
    long countByIdKaryawanIn(List<String> idKaryawanList);
}
