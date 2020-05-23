package srpfacadelab;

public class Facade {
    //Facade class for RPGPlayer Objects.
    private Damage damage;
    private Usage usage;
    private Picker picker;

    public Facade(){
        this.damage = new Damage();
        this.usage = new Usage();
        this.picker = new Picker();
    }

    public boolean pickUpItem(Item item, RpgPlayer player){
        return this.picker.pickUpItem(item, player);
    }

    public void takeDamage(int damage, RpgPlayer player) {
        this.damage.takeDamage(damage, player);
    }

    public void useItem(Item item, RpgPlayer player) {
        this.usage.useItem(item, player);
    }

}
