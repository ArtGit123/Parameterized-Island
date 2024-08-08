package carnivores;

import main.Carnivore;

public class Boa extends Carnivore {
    public Boa(int x, int y) {
        super(x, y);

        WEIGHT = 15;
        MAX_NUM_OF_THIS_SPECIES_PER_LOCATION = 30;
        SPEED = 1;
        KG_OF_FOOD_TO_FEED = 3;

        CE_FOX = 15;
        CE_RABBIT = 20;
        CE_MOUSE = 40;
        CE_DUCK = 10;
    }

    @Override
    public Boa newInstance() {
        return new Boa(location_x, location_y);
    }
}
