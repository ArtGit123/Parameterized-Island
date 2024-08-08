package herbivores;

import main.Herbivore;

public class Buffalo extends Herbivore {

    public Buffalo(int x, int y) {
        super(x, y);

        WEIGHT = 700;
        MAX_NUM_OF_THIS_SPECIES_PER_LOCATION = 10;
        SPEED = 3;
        KG_OF_FOOD_TO_FEED = 100;

        CE_CATERPILLAR = 90;
    }

    @Override
    public Buffalo newInstance() {
        return new Buffalo(location_x, location_y);
    }
}
