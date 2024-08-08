package herbivores;

import main.Herbivore;

public class Sheep extends Herbivore {
    public Sheep(int x, int y) {
        super(x, y);

        WEIGHT = 70;
        MAX_NUM_OF_THIS_SPECIES_PER_LOCATION = 140;
        SPEED = 3;
        KG_OF_FOOD_TO_FEED = 15;
    }

    @Override
    public Sheep newInstance() {
        return new Sheep(location_x, location_y);
    }
}
