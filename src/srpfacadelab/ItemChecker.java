package srpfacadelab;

import java.util.List;

public class ItemChecker {
    //Class responsible for checking whether a particular item object is present in the inventory of an RPGPlayer Object.
    public ItemChecker(){

    }

    public boolean checkIfItemExistsInInventory(Item item, List<Item> inventory) {
        for (Item i: inventory) {
            if (i.getId() == item.getId())
                return true;
        }
        return false;
    }
}
