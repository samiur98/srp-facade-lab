package srpfacadelab;

import java.util.List;

public class Usage {
    //Class that is responsible for helping a an RpgPlayer Object use an Item Object.
    public Usage(){

    }

    public void useItem(Item item, RpgPlayer player) {
        if (item.getName().equals("Stink Bomb"))
        {
            List<IEnemy> enemies = player.getGameEngine().getEnemiesNear(player);
            for (IEnemy enemy: enemies){
                enemy.takeDamage(100);
            }
        }
    }
}
