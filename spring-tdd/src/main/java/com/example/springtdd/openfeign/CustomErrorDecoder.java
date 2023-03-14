package com.example.springtdd.openfeign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Request;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {
    private ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {

        ErrorResponse errorResponse;
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            errorResponse = mapper.readValue(bodyIs, ErrorResponse.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }
        switch (response.status()) {
            case 400:
                return new IllegalArgumentException(errorResponse.getReason());
            case 429:
                return new  RetryableException(
                        response.status(),
                        errorResponse.getReason(),
                        Request.HttpMethod.GET,
                        null,
                        response.request());
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }

    @Data @NoArgsConstructor
    public static class ErrorResponse {
        private String reason;
        private String errorCode;
    }
}
