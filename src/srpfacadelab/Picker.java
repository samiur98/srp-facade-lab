package srpfacadelab;

import java.util.List;

public class Picker {
    public Picker(){

    }

    public boolean pickUpItem(Item item, RpgPlayer player) {
        int weight = calculateInventoryWeight(player.getInventory());
        if (weight + item.getWeight() > player.getCarryingCapacity())
            return false;

        if (item.isUnique() && checkIfItemExistsInInventory(item, player.getInventory()))
            return false;

        // Don't pick up items that give health, just consume them.
        if (item.getHeal() > 0) {
            player.setHealth(player.getHealth() + item.getHeal());

            if (player.getHealth() > player.getMaxHealth())
                player.setHealth(player.getMaxHealth());

            if (item.getHeal() > 500) {
                player.getGameEngine().playSpecialEffect("green_swirly");
            }

            return true;
        }

        if (item.isRare())
            player.getGameEngine().playSpecialEffect("cool_swirly_particles");

        //Feature: Super rare items look more awesome
        if(item.isRare() && item.isUnique()){
            player.getGameEngine().playSpecialEffect("blue_swirly");
        }

        player.getInventory().add(item);

        calculateStats(player);

        return true;
    }

    private int calculateInventoryWeight(List<Item> inventory) {
        //Calcuates weight of items provided
        int sum=0;
        for (Item i: inventory) {
            sum += i.getWeight();
        }
        return sum;
    }

    private boolean checkIfItemExistsInInventory(Item item, List<Item> inventory) {
        for (Item i: inventory) {
            if (i.getId() == item.getId())
                return true;
        }
        return false;
    }

    private void calculateStats(RpgPlayer player) {
        for (Item i: player.getInventory()) {
            player.setArmour(player.getArmour() + i.getArmour());
        }
    }

}
