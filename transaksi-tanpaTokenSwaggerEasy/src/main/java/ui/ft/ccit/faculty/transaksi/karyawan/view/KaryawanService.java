package ui.ft.ccit.faculty.transaksi.karyawan.view;

import ui.ft.ccit.faculty.transaksi.DataAlreadyExistsException;
import ui.ft.ccit.faculty.transaksi.DataNotFoundException;
import ui.ft.ccit.faculty.transaksi.karyawan.model.Karyawan;
import ui.ft.ccit.faculty.transaksi.karyawan.model.KaryawanRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KaryawanService {

    private final KaryawanRepository karyawanRepository;
    private final PasswordEncoder passwordEncoder;

    public KaryawanService(KaryawanRepository karyawanRepository, PasswordEncoder passwordEncoder) {
        this.karyawanRepository = karyawanRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Karyawan> getAll() {
        return karyawanRepository.findAll();
    }

    public Karyawan getById(String id) {
        return karyawanRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Karyawan", id));
    }

    public Optional<Karyawan> findByGmail(String gmail) {
        return karyawanRepository.findByGmail(gmail);
    }

    public List<Karyawan> searchByNama(String keyword) {
        return karyawanRepository.findByNamaContainingIgnoreCase(keyword);
    }

    // CREATE
    public Karyawan save(Karyawan karyawan) {
        if (karyawan.getIdKaryawan() == null || karyawan.getIdKaryawan().isBlank()) {
            throw new IllegalArgumentException("idKaryawan wajib diisi");
        }

        if (karyawanRepository.existsById(karyawan.getIdKaryawan())) {
            throw new DataAlreadyExistsException("Karyawan", karyawan.getIdKaryawan());
        }

        return karyawanRepository.save(karyawan);
    }

    // UPDATE
    public Karyawan update(String id, Karyawan updated) {
        Karyawan existing = getById(id); // akan lempar DataNotFoundException

        existing.setNama(updated.getNama());
        existing.setJenisKelamin(updated.getJenisKelamin());
        existing.setAlamat(updated.getAlamat());
        existing.setTelepon(updated.getTelepon());
        existing.setTglLahir(updated.getTglLahir());
        existing.setGaji(updated.getGaji());
        existing.setGmail(updated.getGmail());
        existing.setPassword(updated.getPassword());

        return karyawanRepository.save(existing);
    }

    // DELETE
    public void delete(String id) {
        if (!karyawanRepository.existsById(id)) {
            throw new DataNotFoundException("Karyawan", id);
        }
        karyawanRepository.deleteById(id);
    }

    // LOGIN
    public Karyawan login(String gmail, String password) {
        Optional<Karyawan> karyawanOpt = karyawanRepository.findByGmail(gmail);
        if (karyawanOpt.isPresent()) {
            Karyawan karyawan = karyawanOpt.get();
            if (passwordEncoder.matches(password, karyawan.getPassword())) {
                return karyawan;
            }
        }
        throw new IllegalArgumentException("Login failed for gmail: " + gmail);
    }
}
