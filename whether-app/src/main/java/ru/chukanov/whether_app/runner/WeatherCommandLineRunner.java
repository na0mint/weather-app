package ru.chukanov.whether_app.runner;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.chukanov.whether_app.service.WeatherService;

import java.util.Scanner;

/**
 * Командная строка для работы с погодой
 *
 * @author Nikolay Chukanov
 */
@Component
@RequiredArgsConstructor
public class WeatherCommandLineRunner implements CommandLineRunner {

    private final WeatherService weatherService;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите широту (lat): ");
        double lat = scanner.nextDouble();

        System.out.print("Введите долготу (lon): ");
        double lon = scanner.nextDouble();

        System.out.print("Введите срок прогноза в днях (limit): ");
        int limit = scanner.nextInt();

        System.out.println("\nПолучаем погоду...");

        try {
            printWeatherInfo(weatherService.getWeatherWithCoordinates(lat, lon, limit));
        } catch (Exception e) {
            System.err.println("Ошибка при запросе: " + e.getMessage());
        }
    }

    private void printWeatherInfo(JsonNode response) {
        System.out.println("\nОтвет Яндекс.Погоды:");
        System.out.println(response.toPrettyString());

        System.out.print("\nТемпература: ");
        System.out.print(response.get("fact").get("temp").asInt());

        JsonNode forecasts = response.get("forecasts");
        if (forecasts != null && forecasts.isArray()) {
            int totalTemp = 0;
            int size = forecasts.size();

            for (JsonNode forecast : forecasts) {
                totalTemp += forecast.get("parts").get("day").get("temp_avg").asInt();
            }

            System.out.println("\nСредняя температура за " + size + " дней: "
                    + (double) totalTemp / size
            );
        }
    }
}
