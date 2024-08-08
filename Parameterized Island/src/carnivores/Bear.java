package carnivores;

import main.Carnivore;

public class Bear extends Carnivore {

    public Bear(int x, int y) {
        super(x, y);

        WEIGHT = 500;
        MAX_NUM_OF_THIS_SPECIES_PER_LOCATION = 5;
        SPEED = 2;
        KG_OF_FOOD_TO_FEED = 80;

        CE_BOA = 80;
        CE_HORSE = 40;
        CE_DEER = 80;
        CE_RABBIT = 80;
        CE_MOUSE = 90;
        CE_GOAT = 70;
        CE_SHEEP = 70;
        CE_BOAR = 50;
        CE_BUFFALO = 20;
        CE_DUCK = 10;
    }

    @Override
    public Bear newInstance() {
        return new Bear(location_x, location_y);
    }
}
