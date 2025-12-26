package ui.ft.ccit.faculty.transaksi.karyawan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ui.ft.ccit.faculty.transaksi.JwtUtil;
import ui.ft.ccit.faculty.transaksi.karyawan.model.Karyawan;
import ui.ft.ccit.faculty.transaksi.karyawan.view.KaryawanService;

import java.util.List;

@RestController
@RequestMapping("/api/karyawan")
@Tag(name = "Karyawan", description = "API untuk mengelola data karyawan")
public class KaryawanController {

    private final KaryawanService service;
    private final JwtUtil jwtUtil;

    public KaryawanController(KaryawanService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    // GET list semua karyawan
    @GetMapping
    @Operation(summary = "Mengambil daftar semua karyawan", description = "Mengambil seluruh data karyawan yang tersedia di sistem.")
    public List<Karyawan> list() {
        return service.getAll();
    }

    // GET satu karyawan by id
    @GetMapping("/{id}")
    @Operation(summary = "Mengambil detail satu karyawan", description = "Mengambil detail satu karyawan berdasarkan ID.")
    public Karyawan get(@PathVariable String id) {
        return service.getById(id);
    }

    // SEARCH by nama
    @GetMapping("/search")
    @Operation(summary = "Mencari karyawan berdasarkan nama", description = "Mencari karyawan berdasarkan kata kunci pada nama.")
    public List<Karyawan> search(@RequestParam String q) {
        return service.searchByNama(q);
    }

    // POST - create karyawan baru
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Membuat karyawan baru", description = "Membuat satu data karyawan baru ke dalam sistem.")
    public Karyawan create(@RequestBody Karyawan karyawan) {
        return service.save(karyawan);
    }

    // PUT - edit/update karyawan
    @PutMapping("/{id}")
    @Operation(summary = "Memperbarui data karyawan", description = "Memperbarui data karyawan berdasarkan ID.")
    public Karyawan update(@PathVariable String id, @RequestBody Karyawan karyawan) {
        return service.update(id, karyawan);
    }

    // DELETE - hapus karyawan
    @DeleteMapping("/{id}")
    @Operation(summary = "Menghapus karyawan", description = "Menghapus satu karyawan berdasarkan ID.")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    // POST - login
    @PostMapping("/login")
    @Operation(summary = "Login karyawan", description = "Login menggunakan gmail dan password.")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        Karyawan karyawan = service.login(loginRequest.getGmail(), loginRequest.getPassword());
        String token = jwtUtil.generateToken(karyawan.getGmail());
        return new LoginResponse(token, karyawan);
    }

    public static class LoginRequest {
        private String gmail;
        private String password;

        public String getGmail() {
            return gmail;
        }

        public void setGmail(String gmail) {
            this.gmail = gmail;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class LoginResponse {
        private String token;
        private Karyawan karyawan;

        public LoginResponse(String token, Karyawan karyawan) {
            this.token = token;
            this.karyawan = karyawan;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Karyawan getKaryawan() {
            return karyawan;
        }

        public void setKaryawan(Karyawan karyawan) {
            this.karyawan = karyawan;
        }
    }
}
