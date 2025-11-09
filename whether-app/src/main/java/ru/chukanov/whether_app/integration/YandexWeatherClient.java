package ru.chukanov.whether_app.integration;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Клиент для интеграции API Яндекс Погода
 *
 * @author Nikolay Chukanov
 */
@FeignClient(url = "${integration.weather.yandex.url}", name = "yandexWhetherClient")
public interface YandexWeatherClient {

    /**
     * Получить погоду по координатам по API
     *
     * @param apiKey    ключ доступа к api
     * @param latitude  широта
     * @param longitude долгота
     * @param limit     срок прогноза
     * @return ответ API
     */
    @GetMapping
    JsonNode getWeatherWithCoordinates(
            @RequestHeader("X-Yandex-API-Key") String apiKey,
            @RequestParam("lat") double latitude,
            @RequestParam("lon") double longitude,
            @RequestParam("limit") int limit
    );
}
