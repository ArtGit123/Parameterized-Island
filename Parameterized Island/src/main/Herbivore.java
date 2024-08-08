package main;

public abstract class Herbivore extends Animal {

    public Herbivore(int x, int y) {
        super(x, y);
        CE_PLANT = 100;
    }
}
