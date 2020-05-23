package srpfacadelab;

import java.util.List;

public class Damage {
    //Class that is responsible for handling damage taken by an RPGPlayer object.
    public Damage(){

    }
    public void takeDamage(int damage, RpgPlayer player) {
        if (damage < player.getArmour()) {
            player.getGameEngine().playSpecialEffect("parry");
        }
        int damageToDeal = damage - player.getArmour();
        //Feature: Un-encumbered players can parry more successfully
        if(calculateInventoryWeight(player.getInventory()) < (player.getCarryingCapacity() / 2)){
            damageToDeal = (damageToDeal * 75)/100;
        }
        player.setHealth(player.getHealth() - damageToDeal);
        player.getGameEngine().playSpecialEffect("lots_of_gore");
    }

    private int calculateInventoryWeight(List<Item> inventory) {
        //Calcuates weight of items provided
        int sum=0;
        for (Item i: inventory) {
            sum += i.getWeight();
        }
        return sum;
    }
}
