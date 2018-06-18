package uk.co.maclon.claimantService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ClaimantApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClaimantApplication.class, args);
	}
}
