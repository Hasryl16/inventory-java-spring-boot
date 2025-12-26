package ui.ft.ccit.faculty.transaksi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ui.ft.ccit.faculty.transaksi")
public class TransaksiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransaksiApplication.class, args);
	}

}
