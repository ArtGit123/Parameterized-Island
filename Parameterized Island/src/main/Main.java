package main;

import carnivores.*;
import herbivores.*;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main implements Runnable {

    public static final int HEIGHT = 20;
    public static final int WIDTH = 20;
    public static final int NUM_OF_ANIMAL_CLASSES = 15;
    public static final int NUM_OF_EACH_SPECIES_AT_START_IN_EACH_LOCATION = 1;

    private static int cycles = 0;

    public static Location[][] locations = new Location[HEIGHT][WIDTH];

    public static void main(String[] args) {
        System.out.println("Hello world!");

        fillLocations();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        executorService.scheduleWithFixedDelay(new Main(), 0, 1, TimeUnit.SECONDS);
//        while (true) { // Use for debugging
//            cycles++;
//            update();
//            displayStatistics();
//            try {
//                Thread.sleep(1000);
//            } catch (Exception e) {}
//        }
    }

    private static void fillLocations() {
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[0].length; j++) {
                locations[i][j] = new Location();
            }
        }

        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[0].length; j++) {

                for (int k = 0; k < 3; k++) {
                    locations[i][j].plants.add(new Plant(j, i));
                }

                for (int k = 0; k < NUM_OF_ANIMAL_CLASSES; k++) {
                    for (int l = 0; l < NUM_OF_EACH_SPECIES_AT_START_IN_EACH_LOCATION; l++) {
                        if (ThreadLocalRandom.current().nextInt(4) == 3) {
                            Animal animal = switch (k) {
                                case 0 -> new Boar(j, i);
                                case 1 -> new Buffalo(j, i);
                                case 2 -> new Caterpillar(j, i);
                                case 3 -> new Deer(j, i);
                                case 4 -> new Duck(j, i);
                                case 5 -> new Goat(j, i);
                                case 6 -> new Horse(j, i);
                                case 7 -> new Mouse(j, i);
                                case 8 -> new Rabbit(j, i);
                                case 9 -> new Sheep(j, i);
                                case 10 -> new Bear(j, i);
                                case 11 -> new Boa(j, i);
                                case 12 -> new Eagle(j, i);
                                case 13 -> new Fox(j, i);
                                default -> new Wolf(j,i);
                            };
                            locations[i][j].animals.add(animal);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        update();
        displayStatistics();
        cycles++;
    }

    private static void update() {
        for (int i = 0; i < locations.length; i++) {

            for (int j = 0; j < locations[0].length; j++) {
                int index = 0;

                ArrayList<Animal> animals = new ArrayList<>(locations[i][j].animals);

                for (int k = 0; k < locations[i][j].animals.size(); k++) {

                    Animal animal = animals.get(index);

                    if (animal.KG_OF_FOOD_TO_FEED != 0) {animal.HUNGER += animal.HUNGER/20;}

                    if (animal.HUNGER >= animal.KG_OF_FOOD_TO_FEED) {
                        animals.remove(animal);
                        if (index != 0) {index--;}
                        continue;
                    }

                    animal.hasBred = false;

                    if (animal.SPEED != 0) {
                        int moves = ThreadLocalRandom.current().nextInt(0, animal.SPEED+1);
                        for (int l = 0; l < moves; l++) {
                            if (animal.move() != Animal.DIRECTION.NONE) {
                                locations[animal.location_y][animal.location_x].animals.add(animal);
                                animals.remove(animal);
                            }
                        }
                        if (moves != 0 && index != 0) {
                            index--;
                        }
                    }


                    if (animal.eat(animals) < index && !(animal instanceof Herbivore) && index != 0) {index--;}
                    animal.breed(animals);

                    index++;

                    locations[i][j].animals = new ArrayList<>(animals);
                }

                ArrayList<Plant> plants = new ArrayList<>(locations[i][j].plants);

                for (int k = 0; k < locations[i][j].plants.size()-1; k++) {
                    plants.get(k).grow(plants);
                }
            }
        }
    }
    private static void displayStatistics() {
        int boar = 0;
        int buffalo = 0;
        int caterpillar = 0;
        int deer = 0;
        int duck = 0;
        int goat = 0;
        int horse = 0;
        int mouse = 0;
        int rabbit = 0;
        int sheep = 0;
        int bear = 0;
        int boa = 0;
        int eagle = 0;
        int fox = 0;
        int wolf = 0;
        int plant = 0;

        for (Location[] i : locations) {
            for (Location j : i) {
                for (Animal k : j.animals) {
                    if (k instanceof Boar) {boar++;}
                    else if (k instanceof Buffalo) {buffalo++;}
                    else if (k instanceof Caterpillar) {caterpillar++;}
                    else if (k instanceof Deer) {deer++;}
                    else if (k instanceof Duck) {duck++;}
                    else if (k instanceof Goat) {goat++;}
                    else if (k instanceof Horse) {horse++;}
                    else if (k instanceof Mouse) {mouse++;}
                    else if (k instanceof Rabbit) {rabbit++;}
                    else if (k instanceof Sheep) {sheep++;}
                    else if (k instanceof Bear) {bear++;}
                    else if (k instanceof Boa) {boa++;}
                    else if (k instanceof Eagle) {eagle++;}
                    else if (k instanceof Fox) {fox++;}
                    else if (k instanceof Wolf) {wolf++;}
                }
                plant += j.plants.size();
            }
        }

        System.out.println();
        System.out.println("Cycles elapsed: " + cycles);
        System.out.println();
        System.out.println("\uD83D\uDC17 Boars: " + boar);
        System.out.println("\uD83D\uDC03 Buffalos: " + buffalo);
        System.out.println("\uD83D\uDC1B Caterpillars: " + caterpillar);
        System.out.println("\uD83E\uDD8C Deer: " + deer);
        System.out.println("\uD83E\uDD86 Ducks: " + duck);
        System.out.println("\uD83E\uDD8C Goats: " + goat);
        System.out.println("\uD83D\uDC0E Horses: " + horse);
        System.out.println("\uD83D\uDC01 Mice: " + mouse);
        System.out.println("\uD83D\uDC07 Rabbits: " + rabbit);
        System.out.println("\uD83D\uDC11 Sheep: " + sheep);
        System.out.println("\uD83D\uDC3B Bears: " + bear);
        System.out.println("\uD83D\uDC17 Boas: " + boa);
        System.out.println("\uD83E\uDD85 Eagles: " + eagle);
        System.out.println("\uD83E\uDD8A Foxes: " + fox);
        System.out.println("\uD83D\uDC3A Wolfs: " + wolf);
        System.out.println("\uD83C\uDF3F Plants: " + plant);
        System.out.println();
        System.out.println("Total: " + (boar+buffalo+caterpillar+deer+duck+goat+horse+mouse+rabbit+sheep+bear+boa+eagle+fox+wolf+plant));
        System.out.println();
        System.out.println("===================================");
    }
}