package com.example.demo.envet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 어노테이션 @Order를 사용해 순서를 정할수 있다.
 */
@Component
@Slf4j
public class MailEventHandler {

    @Order(1)
    @EventListener
    public void sendSignUpMail(MailSendEvent mailSendEvent) {
        log.info("{}님에게 회원가입 환영 메일을 보냈습니다.", mailSendEvent.getUsername());
    }

    @Order(2)
    @EventListener
    public void sendNewbieCouponMail(MailSendEvent mailSendEvent) {
        log.info("뉴비인 {}님에게 쿠폰북 메일을 보냈습니다.", mailSendEvent.getUsername());
    }
}
