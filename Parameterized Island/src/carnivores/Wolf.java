package carnivores;

import main.Carnivore;

public class Wolf extends Carnivore {
    public Wolf(int x, int y) {
        super(x, y);

        WEIGHT = 50;
        MAX_NUM_OF_THIS_SPECIES_PER_LOCATION = 30;
        SPEED = 3;
        KG_OF_FOOD_TO_FEED = 8;

        CE_HORSE = 10;
        CE_DEER = 15;
        CE_RABBIT = 60;
        CE_MOUSE = 80;
        CE_GOAT = 60;
        CE_SHEEP = 70;
        CE_BOAR = 15;
        CE_BUFFALO = 10;
        CE_DUCK = 40;
    }

    @Override
    public Wolf newInstance() {
        return new Wolf(location_x, location_y);
    }
}
