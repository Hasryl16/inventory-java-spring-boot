package ui.ft.ccit.faculty.transaksi.pelanggan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import ui.ft.ccit.faculty.transaksi.JwtUtil;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.Pelanggan;
import ui.ft.ccit.faculty.transaksi.pelanggan.view.PelangganService;
import ui.ft.ccit.faculty.transaksi.transaksi.model.Transaksi;
import ui.ft.ccit.faculty.transaksi.transaksi.view.TransaksiService;
import ui.ft.ccit.faculty.transaksi.transaksi.model.TransaksiRequest;

import java.util.List;

@RestController
@RequestMapping("/api/pelanggan")
public class PelangganController {

    private final PelangganService service;
    private final JwtUtil jwtUtil;
    private final TransaksiService transaksiService;

    public PelangganController(PelangganService service, JwtUtil jwtUtil, TransaksiService transaksiService) {
        this.service = service;
        this.jwtUtil = jwtUtil;
        this.transaksiService = transaksiService;
    }

    // GET list semua pelanggan
    @GetMapping
    @Operation(summary = "Mengambil daftar semua pelanggan", description = "Mengambil seluruh data pelanggan yang tersedia di sistem.")
    public List<Pelanggan> list() {
        return service.getAll();
    }

    // GET satu pelanggan by id
    @GetMapping("/{id}")
    @Operation(summary = "Mengambil detail satu pelanggan", description = "Mengambil detail satu pelanggan berdasarkan ID.")
    public Pelanggan get(@PathVariable String id) {
        return service.getById(id);
    }

    // SEARCH by nama
    @GetMapping("/search")
    @Operation(summary = "Mencari pelanggan berdasarkan nama", description = "Mencari pelanggan berdasarkan kata kunci pada nama.")
    public List<Pelanggan> search(@RequestParam String q) {
        return service.searchByNama(q);
    }

    // POST - create pelanggan baru
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Membuat pelanggan baru", description = "Membuat satu data pelanggan baru ke dalam sistem.")
    public Pelanggan create(@RequestBody Pelanggan pelanggan) {
        return service.save(pelanggan);
    }

    // PUT - edit/update pelanggan
    @PutMapping("/{id}")
    @Operation(summary = "Memperbarui data pelanggan", description = "Memperbarui data pelanggan berdasarkan ID.")
    public Pelanggan update(@PathVariable String id, @RequestBody Pelanggan pelanggan) {
        return service.update(id, pelanggan);
    }

    // DELETE - hapus pelanggan
    @DeleteMapping("/{id}")
    @Operation(summary = "Menghapus pelanggan", description = "Menghapus satu pelanggan berdasarkan ID.")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    // POST - create transaksi for pelanggan
    @PostMapping("/{idPelanggan}/transaksi")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Membuat transaksi untuk pelanggan", description = "Membuat transaksi baru untuk pelanggan dengan daftar barang dan jumlah.")
    public Transaksi createTransaksi(@PathVariable String idPelanggan, @RequestBody List<TransaksiRequest> items) {
        return transaksiService.createTransaksi(idPelanggan, items);
    }

    // POST - login
    @PostMapping("/login")
    @Operation(summary = "Login pelanggan", description = "Login menggunakan gmail dan password.")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        Pelanggan pelanggan = service.login(loginRequest.getGmail(), loginRequest.getPassword());
        String token = jwtUtil.generateToken(pelanggan.getGmail());
        return new LoginResponse(token, pelanggan);
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
        private Pelanggan pelanggan;

        public LoginResponse(String token, Pelanggan pelanggan) {
            this.token = token;
            this.pelanggan = pelanggan;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Pelanggan getPelanggan() {
            return pelanggan;
        }

        public void setPelanggan(Pelanggan pelanggan) {
            this.pelanggan = pelanggan;
        }
    }
}
