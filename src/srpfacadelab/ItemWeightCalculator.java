package srpfacadelab;

import java.util.List;

public class ItemWeightCalculator {
    //Class that is responsible for calculating the weight Item Objects in an inventory.
    public int calculateInventoryWeight(List<Item> inventory) {
        //Calcuates weight of items provided
        int sum=0;
        for (Item i: inventory) {
            sum += i.getWeight();
        }
        return sum;
    }
}
