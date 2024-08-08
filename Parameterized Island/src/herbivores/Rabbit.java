package herbivores;

import main.Herbivore;

public class Rabbit extends Herbivore {

    public Rabbit(int x, int y) {
        super(x, y);

        WEIGHT = 2;
        MAX_NUM_OF_THIS_SPECIES_PER_LOCATION = 150;
        SPEED = 2;
        KG_OF_FOOD_TO_FEED = 0.45;
    }

    @Override
    public Rabbit newInstance() {
        return new Rabbit(location_x, location_y);
    }
}
