package com.example.feign.springfeign;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class FeignConfig {
    /**
     * Feign 로그 정책 설정
     * NONE : 로깅하지 않음(Default)
     * BASIC : Request Method와 URL 그리고 Reponse 상태 코드와 실행 시간을 로깅합니다.
     * HEADER : Request, Response Header 정보와 함께 BASIC 정보를 로깅합니다.
     * FULL : Request와 Response의 Header, Body 그리고 메타데이터를 로깅합니다.
     */
    @Bean
    public Logger.Level loggerLevel(){
        return Logger.Level.FULL;
    }

    @Profile(value = {"dev", "test"})
    @Bean
    public Logger customLogger() {
        return new FeignCustomLogger();
    }
}
