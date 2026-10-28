package models;

public class Tank extends Droid{
    private int armor;
    public Tank(String name, int health, int damage, int maxHealth, int armor){
        super(name,health,damage,maxHealth);
        this.armor=armor;
    }
    @Override
    public void receiveDamage(int incomningDamage){
        int changedDamage=Math.max(1,incomningDamage -this.armor);
        super.receiveDamage(changedDamage);
}
    }
