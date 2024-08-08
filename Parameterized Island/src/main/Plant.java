package main;

import main.Animal.DIRECTION;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Plant {

    public int location_x;
    public int location_y;

    // STATISTICS

    public static final int WEIGHT = 1;
    public static final int NUM_OF_THIS_SPECIES_PER_LOCATION = 200;
    private final int spreadAmount = 5;
    public int growth = 0;

    public Plant(int x , int y) {
        location_x = x;
        location_y = y;
    }

    public void grow(ArrayList<Plant> list) {
        growth += 10;
        if (growth > 100) {
            growth = 100;
        }

        if (growth > 50) {
            spread(list);
        }
    }

    private void spread(ArrayList<Plant> list) {
        DIRECTION result;

        boolean upImpossible = false;
        boolean downImpossible = false;
        boolean leftImpossible = false;
        boolean rightImpossible = false;
        boolean hereImpossible = false;
        boolean spread = false;

        for (int i = 0; i < spreadAmount; i++) {
            while ((!upImpossible || !downImpossible || !leftImpossible || !rightImpossible) && !spread) {
                result = switch (ThreadLocalRandom.current().nextInt(5)) {
                    case 0 -> DIRECTION.UP;
                    case 1 -> DIRECTION.DOWN;
                    case 2 -> DIRECTION.LEFT;
                    case 3 -> DIRECTION.RIGHT;
                    default -> DIRECTION.NONE;
                };

                switch (result) {
                    case UP -> {
                        if (location_y != 0 && NUM_OF_THIS_SPECIES_PER_LOCATION > Collections.frequency(Main.locations[location_y - 1][location_x].plants, this)) {
                            Main.locations[location_y - 1][location_x].plants.add(new Plant(location_x, location_y - 1));
                            spread = true;
                        } else {upImpossible = true;}
                    }
                    case DOWN -> {
                        if (location_y != Main.HEIGHT - 1 && NUM_OF_THIS_SPECIES_PER_LOCATION > Collections.frequency(Main.locations[location_y + 1][location_x].plants, this)) {
                            Main.locations[location_y + 1][location_x].plants.add(new Plant(location_x, location_y + 1));
                            spread = true;
                        } else {downImpossible = true;}
                    }
                    case LEFT -> {
                        if (location_x != 0 && NUM_OF_THIS_SPECIES_PER_LOCATION > Collections.frequency(Main.locations[location_y][location_x - 1].plants, this)) {
                            Main.locations[location_y][location_x - 1].plants.add(new Plant(location_x - 1, location_y));
                            spread = true;
                        } else {leftImpossible = true;}
                    }
                    case RIGHT -> {
                        if (location_x != Main.WIDTH - 1 && NUM_OF_THIS_SPECIES_PER_LOCATION > Collections.frequency(Main.locations[location_y][location_x + 1].plants, this)) {
                            Main.locations[location_y][location_x + 1].plants.add(new Plant(location_x + 1, location_y));
                            spread = true;
                        } else {rightImpossible = true;}
                    }
                    default -> {
                        if (NUM_OF_THIS_SPECIES_PER_LOCATION > Collections.frequency(list, this)) {
                            list.add(new Plant(location_x, location_y));
                            spread = true;
                        } else {hereImpossible = true;}
                    }
                }
            }
            if (upImpossible && downImpossible && leftImpossible && rightImpossible && hereImpossible) {
                break;
            }
        }
    }

    public Plant newInstance() {
        return new Plant(location_x, location_y);
    }
}