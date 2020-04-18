package srpfacadelab;

import java.util.List;
import java.util.ArrayList;


public class RpgPlayer {
    public static final int MAX_CARRYING_CAPACITY = 1000;

    private final IGameEngine gameEngine;

    private int health;

    private int maxHealth;

    private int armour;

    private List<Item> inventory;

    private ItemsFacade itemsFacade;

    // How much the player can carry in pounds
    private int carryingCapacity;

    public RpgPlayer(IGameEngine gameEngine) {
        this.gameEngine = gameEngine;
        inventory = new ArrayList<Item>();
        carryingCapacity = MAX_CARRYING_CAPACITY;
        itemsFacade = new ItemsFacade();
    }

    public void useItem(Item item) {
        if (item.getName().equals("Stink Bomb"))
        {
            List<IEnemy> enemies = gameEngine.getEnemiesNear(this);

            for (IEnemy enemy: enemies){
                enemy.takeDamage(100);
            }
        }
    }

    public boolean pickUpItem(Item item) {
        int weight = itemsFacade.calculateInventoryWeight(inventory);
        if (weight + item.getWeight() > carryingCapacity)
            return false;

        if (item.isUnique() && itemsFacade.checkIfItemExistsInInventory(item, inventory))
            return false;

        // Don't pick up items that give health, just consume them.
        if (item.getHeal() > 0) {
            health += item.getHeal();

            if (health > maxHealth)
                health = maxHealth;

            if (item.getHeal() > 500) {
                gameEngine.playSpecialEffect("green_swirly");
            }

            return true;
        }

        if (item.isRare())
            gameEngine.playSpecialEffect("cool_swirly_particles");

        //Feature: Super rare items look more awesome
        if(item.isRare() && item.isUnique()){
            gameEngine.playSpecialEffect("blue_swirly");
        }

        inventory.add(item);

        itemsFacade.calculateStats(inventory, armour);

        return true;
    }



    public void takeDamage(int damage) {

        if (damage < armour) {
            gameEngine.playSpecialEffect("parry");
        }
        int damageToDeal = damage - armour;
        //Feature: Un-encumbered players can parry more successfully
        if(itemsFacade.calculateInventoryWeight(inventory) < (carryingCapacity / 2)){
            damageToDeal = (damageToDeal * 75)/100;
        }
        health -= damageToDeal;
        gameEngine.playSpecialEffect("lots_of_gore");
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getArmour() {
        return armour;
    }

    private void setArmour(int armour) {
        this.armour = armour;
    }

    public int getCarryingCapacity() {
        return carryingCapacity;
    }

    private void setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }
}
