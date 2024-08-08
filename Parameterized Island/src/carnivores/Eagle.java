package carnivores;

import main.Carnivore;

public class Eagle extends Carnivore {
    public Eagle(int x, int y) {
        super(x, y);

        WEIGHT = 6;
        MAX_NUM_OF_THIS_SPECIES_PER_LOCATION = 20;
        SPEED = 3;
        KG_OF_FOOD_TO_FEED = 1;

        CE_FOX = 10;
        CE_RABBIT = 90;
        CE_MOUSE = 90;
        CE_DUCK = 80;
    }

    @Override
    public Eagle newInstance() {
        return new Eagle(location_x, location_y);
    }
}
