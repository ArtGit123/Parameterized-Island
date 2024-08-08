package herbivores;

import main.Herbivore;

public class Duck extends Herbivore {

    public Duck(int x, int y) {
        super(x, y);

        WEIGHT = 1;
        MAX_NUM_OF_THIS_SPECIES_PER_LOCATION = 200;
        SPEED = 4;
        KG_OF_FOOD_TO_FEED = 0.15;

        CE_CATERPILLAR = 90;
    }

    @Override
    public Duck newInstance() {
        return new Duck(location_x, location_y);
    }
}
