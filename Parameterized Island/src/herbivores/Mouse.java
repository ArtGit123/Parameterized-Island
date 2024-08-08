package herbivores;

import main.Herbivore;

public class Mouse extends Herbivore {

    public Mouse(int x, int y) {
        super(x, y);

        WEIGHT = 0.05;
        MAX_NUM_OF_THIS_SPECIES_PER_LOCATION = 500;
        SPEED = 1;
        KG_OF_FOOD_TO_FEED = 0.01;

        CE_CATERPILLAR = 90;
    }

    @Override
    public Mouse newInstance() {
        return new Mouse(location_x, location_y);
    }
}
