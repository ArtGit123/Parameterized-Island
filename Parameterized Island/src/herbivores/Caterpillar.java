package herbivores;

import main.Herbivore;

public class Caterpillar extends Herbivore {

    public Caterpillar(int x, int y) {
        super(x, y);

        WEIGHT = 0.01;
        MAX_NUM_OF_THIS_SPECIES_PER_LOCATION = 1000;
        SPEED = 0;
        KG_OF_FOOD_TO_FEED = 0;
    }

    @Override
    public Caterpillar newInstance() {
        return new Caterpillar(location_x, location_y);
    }
}
