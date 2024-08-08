package herbivores;

import main.Herbivore;


public class Boar extends Herbivore {

    public Boar(int x, int y) {
        super(x, y);

        WEIGHT = 400;
        MAX_NUM_OF_THIS_SPECIES_PER_LOCATION = 50;
        SPEED = 2;
        KG_OF_FOOD_TO_FEED = 50;

        CE_MOUSE = 50;
        CE_CATERPILLAR = 90;
    }

    @Override
    public Boar newInstance() {
        return new Boar(location_x, location_y);
    }
}
