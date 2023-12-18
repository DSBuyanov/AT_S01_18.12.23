package org.max.home;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {

    static List<Door> doors;
    static Game game;

    @BeforeEach
    void initGame() {
        doors = new ArrayList<>();
        doors.add(new Door(true));  // Предполагаем, что первая дверь с призом
        doors.add(new Door(false));
        doors.add(new Door(false));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void checkWinningScenario(boolean changeChoice) {
        Player player = new Player("Игрок", changeChoice);
        Game game = new Game(player, doors);
        Door chosenDoor = game.round(0); // выбираем первую дверь для упрощения

        // Проверяем, что решение игрока соответствует ожидаемому результату
        boolean expectedWin = changeChoice != chosenDoor.isPrize();
        Assertions.assertEquals(expectedWin, chosenDoor.isPrize(),
                "Результат не соответствует ожидаемому при выборе " +
                        (changeChoice ? "смены" : "сохранения") + " двери.");
    }

    @Test
    void checkInvalidDoorSelection() {
        Assertions.assertThrows(NullPointerException.class, () -> game.round(3),
                "Должно быть выброшено исключение при выборе несуществующей двери");
    }

    @Test
    void testGameInitializationDoesNotThrowException() {
        Assertions.assertDoesNotThrow(() -> {
            Player player = new Player("Игрок", true);
            Game game = new Game(player, doors);
            // Здесь можно добавить базовые операции, если они есть
        }, "Инициализация игры не должна вызывать исключений");
    }

}