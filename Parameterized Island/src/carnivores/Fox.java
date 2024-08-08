package carnivores;

import main.Carnivore;

public class Fox extends Carnivore {
    public Fox(int x, int y) {
        super(x, y);

        WEIGHT = 8;
        MAX_NUM_OF_THIS_SPECIES_PER_LOCATION = 30;
        SPEED = 2;
        KG_OF_FOOD_TO_FEED = 2;

        CE_RABBIT = 70;
        CE_MOUSE = 90;
        CE_DUCK = 60;
        CE_CATERPILLAR = 40;
    }

    @Override
    public Fox newInstance() {
        return new Fox(location_x, location_y);
    }
}
