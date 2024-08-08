package herbivores;

import main.Herbivore;

public class Horse extends Herbivore {

    public Horse(int x, int y) {
        super(x, y);

        WEIGHT = 400;
        MAX_NUM_OF_THIS_SPECIES_PER_LOCATION = 20;
        SPEED = 4;
        KG_OF_FOOD_TO_FEED = 60;
    }

    @Override
    public Horse newInstance() {
        return new Horse(location_x, location_y);
    }
}
