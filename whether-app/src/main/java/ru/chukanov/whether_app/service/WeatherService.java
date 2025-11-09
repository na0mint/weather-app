package ru.chukanov.whether_app.service;


import com.fasterxml.jackson.databind.JsonNode;

/**
 * Сервис для работы с погодой
 *
 * @author Nikolay Chukanov
 */
public interface WeatherService {
    /**
     * Получение погоды по координатам
     *
     * @param latitude широта
     * @param longitude долгота
     * @param limit срок прогноза в днях
     * @return ответ сервиса погоды
     */
    JsonNode getWeatherWithCoordinates(double latitude, double longitude, int limit);
}
