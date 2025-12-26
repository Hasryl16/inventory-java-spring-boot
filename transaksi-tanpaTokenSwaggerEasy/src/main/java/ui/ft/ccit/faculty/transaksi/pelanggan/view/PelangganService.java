package ui.ft.ccit.faculty.transaksi.pelanggan.view;

import ui.ft.ccit.faculty.transaksi.DataAlreadyExistsException;
import ui.ft.ccit.faculty.transaksi.DataNotFoundException;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.Pelanggan;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.PelangganRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PelangganService {

    private final PelangganRepository pelangganRepository;
    private final PasswordEncoder passwordEncoder;

    public PelangganService(PelangganRepository pelangganRepository, PasswordEncoder passwordEncoder) {
        this.pelangganRepository = pelangganRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Pelanggan> getAll() {
        return pelangganRepository.findAll();
    }

    public Pelanggan getById(String id) {
        return pelangganRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Pelanggan", id));
    }

    public Optional<Pelanggan> findByGmail(String gmail) {
        return pelangganRepository.findByGmail(gmail);
    }

    public List<Pelanggan> searchByNama(String keyword) {
        return pelangganRepository.findByNamaContainingIgnoreCase(keyword);
    }

    // CREATE
    public Pelanggan save(Pelanggan pelanggan) {
        if (pelanggan.getIdPelanggan() == null || pelanggan.getIdPelanggan().isBlank()) {
            throw new IllegalArgumentException("idPelanggan wajib diisi");
        }

        if (pelangganRepository.existsById(pelanggan.getIdPelanggan())) {
            throw new DataAlreadyExistsException("Pelanggan", pelanggan.getIdPelanggan());
        }

        // Hash the password before saving
        pelanggan.setPassword(passwordEncoder.encode(pelanggan.getPassword()));

        return pelangganRepository.save(pelanggan);
    }

    // UPDATE
    public Pelanggan update(String id, Pelanggan updated) {
        Pelanggan existing = getById(id); // akan lempar DataNotFoundException

        existing.setNama(updated.getNama());
        existing.setJenisKelamin(updated.getJenisKelamin());
        existing.setAlamat(updated.getAlamat());
        existing.setTelepon(updated.getTelepon());
        existing.setTglLahir(updated.getTglLahir());
        existing.setJenisPelanggan(updated.getJenisPelanggan());
        existing.setGmail(updated.getGmail());
        existing.setPassword(passwordEncoder.encode(updated.getPassword()));

        return pelangganRepository.save(existing);
    }

    // DELETE
    public void delete(String id) {
        if (!pelangganRepository.existsById(id)) {
            throw new DataNotFoundException("Pelanggan", id);
        }
        pelangganRepository.deleteById(id);
    }

    // LOGIN
    public Pelanggan login(String gmail, String password) {
        Optional<Pelanggan> pelangganOpt = pelangganRepository.findByGmail(gmail);
        if (pelangganOpt.isPresent()) {
            Pelanggan pelanggan = pelangganOpt.get();
            if (passwordEncoder.matches(password, pelanggan.getPassword())) {
                return pelanggan;
            }
        }
        throw new IllegalArgumentException("Login failed for gmail: " + gmail);
    }
}
