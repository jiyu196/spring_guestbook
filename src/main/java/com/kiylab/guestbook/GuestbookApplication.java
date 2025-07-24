package com.kiylab.guestbook;

import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Log4j2
@SpringBootApplication
@EnableJpaAuditing
public class GuestbookApplication {
//	@Value("${spring.applicattion.name}")
//	private String name;

	public static void main(String[] args) {

		SpringApplication.run(GuestbookApplication.class, args);
		log.info("GuestbookAppllication started");
	}

}
