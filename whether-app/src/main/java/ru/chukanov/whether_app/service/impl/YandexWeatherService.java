package ru.chukanov.whether_app.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.chukanov.whether_app.integration.YandexWeatherClient;
import ru.chukanov.whether_app.service.WeatherService;


@Service
@RequiredArgsConstructor
public class YandexWeatherService implements WeatherService {

    private final YandexWeatherClient yandexWeatherClient;

    @Value("${integration.weather.yandex.key}")
    private String apiKey;

    @Override
    public JsonNode getWeatherWithCoordinates(double latitude, double longitude, int limit) {
        return yandexWeatherClient.getWeatherWithCoordinates(
                apiKey, latitude, longitude, limit
        );
    }
}
