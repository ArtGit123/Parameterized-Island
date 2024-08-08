package main;

import carnivores.*;
import herbivores.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {

    public int location_x;
    public int location_y;

    // STATISTICS
    public volatile boolean hasBred; // Updates every cycle
    public static double WEIGHT = 20; // KG
    public double KG_OF_FOOD_TO_FEED = 5;

    public double HUNGER = 0; // KG left to feed (+20% every cycle)
    public static int MAX_NUM_OF_THIS_SPECIES_PER_LOCATION = 30;
    public int SPEED = 2;

    // CE = chance eat
    public static int CE_WOLF = 0;
    public static int CE_BOA = 0;
    public static int CE_FOX = 0;
    public static int CE_BEAR = 0;
    public static int CE_EAGLE = 0;
    public static int CE_HORSE = 0;
    public static int CE_DEER = 0;
    public static int CE_RABBIT = 0;
    public static int CE_MOUSE = 0;
    public static int CE_GOAT = 0;
    public static int CE_SHEEP = 0;
    public static int CE_BOAR = 0;
    public static int CE_BUFFALO = 0;
    public static int CE_DUCK = 0;
    public static int CE_CATERPILLAR = 0;
    public static int CE_PLANT = 0;

    public Animal(int x, int y) {
        location_x = x;
        location_y = y;
    }

    public int eat(ArrayList<Animal> list) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            for (Animal i : list) {
                int chance = 0;
                if (i instanceof Boar && !i.equals(this)) {chance = CE_BOAR;}
                else if (i instanceof Buffalo && !i.equals(this)) {chance = CE_BUFFALO;}
                else if (i instanceof Caterpillar && !i.equals(this)) {chance = CE_CATERPILLAR;}
                else if (i instanceof Deer && !i.equals(this)) {chance = CE_DEER;}
                else if (i instanceof Duck && !i.equals(this)) {chance = CE_DUCK;}
                else if (i instanceof Goat && !i.equals(this)) {chance = CE_GOAT;}
                else if (i instanceof Horse && !i.equals(this)) {chance = CE_HORSE;}
                else if (i instanceof Mouse && !i.equals(this)) {chance = CE_MOUSE;}
                else if (i instanceof Rabbit && !i.equals(this)) {chance = CE_RABBIT;}
                else if (i instanceof Sheep && !i.equals(this)) {chance = CE_SHEEP;}
                else if (i instanceof Bear && !i.equals(this)) {chance = CE_BEAR;}
                else if (i instanceof Boa && !i.equals(this)) {chance = CE_BOA;}
                else if (i instanceof Eagle && !i.equals(this)) {chance = CE_EAGLE;}
                else if (i instanceof Fox && !i.equals(this)) {chance = CE_FOX;}
                else if (i instanceof Wolf && !i.equals(this)) {chance = CE_WOLF;}

                if (chance != 0) {
                    if (ThreadLocalRandom.current().nextInt(100) < chance) {
                        HUNGER -= i.WEIGHT;
                        if (HUNGER < 0) {HUNGER = 0;}
                        int index = list.indexOf(i);
                        list.remove(i);
                        return index;
                    }
                    break;
                }
            }
        } else {
            if (CE_PLANT > 0 && ThreadLocalRandom.current().nextInt(100) < CE_PLANT
                    && Main.locations[location_y][location_x].plants.removeIf(i -> i.growth > 70 && HUNGER > 0)) {
                HUNGER--;
                return Integer.MAX_VALUE; // To not trigger index-- in main
            }
        }

        return 0;
    }
    public boolean breed(ArrayList<Animal> list) {
        if (MAX_NUM_OF_THIS_SPECIES_PER_LOCATION > Collections.frequency(list, this)) {
            for (Animal i : list) {
                if (!i.hasBred && i.getClass().isInstance(this)) {
                    Animal offspring = newInstance();
                    offspring.hasBred = true;
                    list.add(offspring);
                    i.hasBred = true;
                    hasBred = true;
                    return true;
                }
            }
        }
        return false;
    }

    public abstract Animal newInstance();

    public enum DIRECTION {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        NONE
    }

    public DIRECTION move() {
        DIRECTION result;

        boolean upImpossible = false;
        boolean downImpossible = false;
        boolean leftImpossible = false;
        boolean rightImpossible = false;

        while (true) {
            result = switch (ThreadLocalRandom.current().nextInt(5)) {
                case 0 -> DIRECTION.UP;
                case 1 -> DIRECTION.DOWN;
                case 2 -> DIRECTION.LEFT;
                case 3 -> DIRECTION.RIGHT;
                default -> DIRECTION.NONE;
            };

            switch (result) {
                case UP -> {
                    if (location_y > 0 && MAX_NUM_OF_THIS_SPECIES_PER_LOCATION > Collections.frequency(Main.locations[location_y-1][location_x].animals, this)) {
                        location_y--;
                        return result;
                    } else {upImpossible = true;}
                }
                case DOWN -> {
                    if (location_y > Main.HEIGHT - 1 && MAX_NUM_OF_THIS_SPECIES_PER_LOCATION > Collections.frequency(Main.locations[location_y+1][location_x].animals, this)) {
                        location_y++;
                        return result;
                    } else {downImpossible = true;}
                }
                case LEFT -> {
                    if (location_x > 0 && MAX_NUM_OF_THIS_SPECIES_PER_LOCATION > Collections.frequency(Main.locations[location_y][location_x-1].animals, this)) {
                        location_x--;
                        return result;
                    } else {leftImpossible = true;}
                }
                case RIGHT -> {
                    if (location_x > Main.WIDTH - 1 && MAX_NUM_OF_THIS_SPECIES_PER_LOCATION > Collections.frequency(Main.locations[location_y][location_x+1].animals, this)) {
                        location_x++;
                        return result;
                    } else {rightImpossible = true;}
                }
                default -> {
                    return DIRECTION.NONE;
                }
            }

            if (upImpossible && downImpossible && leftImpossible && rightImpossible) {
                return DIRECTION.NONE;
            }
        }
    }
}