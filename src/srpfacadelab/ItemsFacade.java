package srpfacadelab;

import java.util.List;

public class ItemsFacade {
    //Facade class that acts as an interface and provides utility methods for item objects.
    private ItemStatsCalculator statsCalculator;
    private ItemWeightCalculator weightCalculator;
    private ItemChecker itemChecker;
    public ItemsFacade(){
        this.statsCalculator = new ItemStatsCalculator();
        this.weightCalculator = new ItemWeightCalculator();
        this.itemChecker = new ItemChecker();
    }

    public int calculateInventoryWeight(List<Item> inventory){
        return this.weightCalculator.calculateInventoryWeight(inventory);
    }

    public void calculateStats(List<Item> inventory, int armour) {
        this.statsCalculator.calculateStats(inventory, armour);
    }

    public boolean checkIfItemExistsInInventory(Item item, List<Item> inventory) {
       return this.itemChecker.checkIfItemExistsInInventory(item, inventory);
    }

}
