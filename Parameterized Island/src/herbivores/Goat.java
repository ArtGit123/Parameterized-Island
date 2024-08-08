package herbivores;

import main.Herbivore;

public class Goat extends Herbivore {

    public Goat(int x, int y) {
        super(x, y);

        WEIGHT = 60;
        MAX_NUM_OF_THIS_SPECIES_PER_LOCATION = 140;
        SPEED = 3;
        KG_OF_FOOD_TO_FEED = 10;
    }

    @Override
    public Goat newInstance() {
        return new Goat(location_x, location_y);
    }
}
