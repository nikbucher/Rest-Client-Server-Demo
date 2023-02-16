package com.example.client;

import static java.time.ZoneId.SHORT_IDS;

import com.example.demo.DateObject;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Client {
	private static final Logger log = LoggerFactory.getLogger(Client.class);

	private static final Instant DATE = Instant.parse("2023-01-10T10:11:12Z");

	public static void main(String[] args) {
		SpringApplication.run(Client.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		RestTemplate build = builder.build();
		build.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		return build;
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			ZonedDateTime zoned = DATE.atZone(ZoneId.of(SHORT_IDS.get("ECT")));
			restTemplate.postForEntity("http://localhost:9999", new DateObject(DATE, zoned.toOffsetDateTime(), zoned), Void.class);
			DateObject quote = restTemplate.getForObject("http://localhost:9999", DateObject.class);
			log.info("date object = {}", quote);
		};
	}
}
