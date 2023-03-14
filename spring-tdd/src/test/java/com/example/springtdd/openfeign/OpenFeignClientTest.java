package com.example.springtdd.openfeign;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.http.Body;
import feign.RetryableException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureWireMock(port = 0)
@TestPropertySource(properties = {
        "open-api.base.url=http://localhost:${wiremock.server.port}"
})
@SpringBootTest
class OpenFeignClientTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @SpyBean
    OpenFeignClient openFeignClient;

    @DisplayName("응답상태가 429일시 retry 후 실패 시 RetryableException이 발생한다.")
    @Test
    void failedGetRandomValueWith429Error() throws JsonProcessingException {
        //given
        CustomErrorDecoder.ErrorResponse errorResponse = new CustomErrorDecoder.ErrorResponse();
        errorResponse.setErrorCode("ABC_123");
        String returnMessage = "잘못된 요청입니다. 다시 시도해주세요.";
        errorResponse.setReason(returnMessage);

        stubFor(get(urlEqualTo("/random"))
                .willReturn(aResponse()
                        .withResponseBody(Body.fromJsonBytes(objectMapper.writeValueAsBytes(errorResponse)))
                        .withStatus(429)));
        //when
        Throwable throwable = catchThrowable(() -> openFeignClient.getRandomValue());

        //then
        assertThat(throwable)
                .isInstanceOf(RetryableException.class)
                .hasMessage(returnMessage);
    }

    @DisplayName("응답상태가 400일시 IllegalArgumentException이 발생한다.")
    @Test
    void failedGetRandomValueWith400Error() throws JsonProcessingException {
        //given
        String returnMessage = "잘못된 파라미터입니다.";
        CustomErrorDecoder.ErrorResponse errorResponse = new CustomErrorDecoder.ErrorResponse();
        errorResponse.setErrorCode("ABC_345");
        errorResponse.setReason(returnMessage);

        stubFor(get(urlEqualTo("/random"))
                .willReturn(aResponse()
                        .withResponseBody(Body.fromJsonBytes(objectMapper.writeValueAsBytes(errorResponse)))
                        .withStatus(400)));
        //when
        Throwable throwable = catchThrowable(() -> openFeignClient.getRandomValue());

        //then
        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(returnMessage);
    }

    @DisplayName("random-api 호출 성공")
    @Test
    void getRandomValue() {
        //given
        String responseBody =
                "{\n" +
                "  \"count\": 1,\n" +
                "  \"entries\": [\n" +
                "    {\n" +
                "      \"API\": \"Tenders in Hungary\",\n" +
                "      \"Description\": \"Get data for procurements in Hungary in JSON format\",\n" +
                "      \"Auth\": \"\",\n" +
                "      \"HTTPS\": true,\n" +
                "      \"Cors\": \"unknown\",\n" +
                "      \"Link\": \"https://tenders.guru/hu/api\",\n" +
                "      \"Category\": \"Business\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        stubFor(get(urlEqualTo("/random"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseBody)
                        .withStatus(200)));
        //when
        Map<String, Object> randomValue = openFeignClient.getRandomValue();

        //then
        assertThat(randomValue.get("count")).isEqualTo(1);
        assertThat((List<?>) randomValue.get("entries")).hasSize(1);
    }
}