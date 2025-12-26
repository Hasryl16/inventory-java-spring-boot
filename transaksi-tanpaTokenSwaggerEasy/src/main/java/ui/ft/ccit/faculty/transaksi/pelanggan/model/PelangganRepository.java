package ui.ft.ccit.faculty.transaksi.pelanggan.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PelangganRepository extends JpaRepository<Pelanggan, String> {

    
    List<Pelanggan> findByNamaContainingIgnoreCase(String keyword);

    
    Optional<Pelanggan> findByGmail(String gmail);

   
    long countByIdPelangganIn(List<String> idPelangganList);
}
