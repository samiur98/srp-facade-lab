package srpfacadelab;

import java.util.List;

public class ItemStatsCalculator {
    //Class responsible for calculating the stats of items in the inventory of an RPGPlayer Object.
    public void calculateStats(List<Item> inventory, int armour) {
        for (Item i: inventory) {
            armour += i.getArmour();
        }
    }
}
