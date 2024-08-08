package herbivores;

import main.Herbivore;

public class Deer extends Herbivore {

    public Deer(int x, int y) {
        super(x, y);

        WEIGHT = 300;
        MAX_NUM_OF_THIS_SPECIES_PER_LOCATION = 20;
        SPEED = 4;
        KG_OF_FOOD_TO_FEED = 50;
    }

    @Override
    public Deer newInstance() {
        return new Deer(location_x, location_y);
    }
}
