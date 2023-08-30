package com.example.feign.springfeign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;
import java.io.IOException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeignCustomLogger extends Logger {

    private final ObjectMapper mapper = new ObjectMapper();

    public FeignCustomLogger() {
    }

    @Override
    protected void log(String configKey, String format, Object... args) {

    }

    // ex) [요청] GET http://localhost:8081/api/v1/test
    @SneakyThrows
    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        String bodyData = request.body() != null ? mapper.writeValueAsString(request.body()) : "";

        log.info(String.format("[요청] %s %s %s", request.httpMethod(), request.url(),
            bodyData));

        super.logRequest(configKey, logLevel, request);
    }

    // ex) [응답] 200 {"status" : "success"}
    @SneakyThrows
    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response,
        long elapsedTime) {
        byte[] bodyData = Util.toByteArray(response.body().asInputStream());

        log.info(String.format("[응답] %d %s", response.status(), new String(bodyData)));

        return response.toBuilder().body(bodyData).build();
    }

    // ex) [요청실패] reason....
    @Override
    protected IOException logIOException(String configKey, Level logLevel, IOException ioe,
        long elapsedTime) {
        log.error("[요청실패] {}", ioe.getMessage(), ioe);

        return super.logIOException(configKey, logLevel, ioe, elapsedTime);
    }
}
