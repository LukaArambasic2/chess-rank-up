package hr.fer.tzk.rankup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RankUpApplication {
//	@Autowired
//	private EmailSenderService emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(RankUpApplication.class, args);
	}

/*
	Testing for mail sending
 */
//	@EventListener(ApplicationReadyEvent.class)
//	public void triggerMail() throws MessagingException, IOException {
//		emailSenderService.sendEmail(
//				"<email@example.com>",
//				"<subject>",
//				"<html-body>",
//				"<logo-path>",
//				"<attachment-path>");
//	}
}
