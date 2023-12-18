package org.max.home;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс для реализации игры
 */
public class Game {

    private Player player;
    private List<Door> doors = new ArrayList<>();

    public Game(Player player, List<Door> doors) {
        this.player = player;
        this.doors = doors;
    }

    public Door round(int door) {
        if (!player.getRisk()) {
            return doors.get(door);
        } else {
            doors.remove(doors.get(door));
            if (doors.size() > 1) {
                return doors.get(0).isPrize() ? doors.get(0) : doors.get(1);
            } else {
                return doors.get(0); // Если осталась только одна дверь
            }
        }
    }


}
