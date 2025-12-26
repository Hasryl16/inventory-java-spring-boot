package ui.ft.ccit.faculty.transaksi;

import ui.ft.ccit.faculty.transaksi.karyawan.model.Karyawan;
import ui.ft.ccit.faculty.transaksi.karyawan.model.KaryawanRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class KaryawanUserDetailsService implements UserDetailsService {

    private final KaryawanRepository karyawanRepository;

    public KaryawanUserDetailsService(KaryawanRepository karyawanRepository) {
        this.karyawanRepository = karyawanRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Karyawan karyawan = karyawanRepository.findByGmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with gmail: " + username));

        return new User(karyawan.getGmail(), karyawan.getPassword(), Collections.emptyList());
    }
}
