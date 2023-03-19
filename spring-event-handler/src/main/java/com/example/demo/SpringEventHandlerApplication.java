package com.example.demo;

import com.example.demo.envet.MailSendEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringEventHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEventHandlerApplication.class, args);
	}

	@Component
	class AppRunner implements ApplicationRunner {
		@Autowired
		ApplicationEventPublisher eventPublisher;

		@Override
		public void run(ApplicationArguments args) {
			MailSendEvent mailSendEvent = new MailSendEvent();
			mailSendEvent.setUsername("김아무개");
			eventPublisher.publishEvent(mailSendEvent);
		}
	}
}
