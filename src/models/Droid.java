package models;

public class Droid {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int damage;

    public Droid(String name, int health, int damage,int maxHealth) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.maxHealth=maxHealth;
    }
    public void attack(Droid target) {
        target.receiveDamage(this.damage);
    }
    public boolean isAlive() {
        return this.health > 0;
    }
    public void resetHealth() {
        this.health = this.maxHealth;
    }
    public void setName(String name){
        this.name=name;
    }
    public void receiveDamage(int incomingDamage) {
        this.health = Math.max(0, this.health - incomingDamage);
    }
    public void receiveHeal(int amount) {
        this.health = Math.min(this.maxHealth, this.health + amount);
    }
    public void setHealth(int health){
        this.health=health;
    }
    public void setMaxHealth(int maxHealth){
        this.maxHealth=maxHealth;
    }
    public void setDamage(int damage){
        this.damage=damage;
    }
    public String getName() {
        return this.name;
    }
    public int getDamage() {
        return this.damage;
    }
    public int getHealth(){
        return this.health;
    }

    public int getMaxHealth(){
        return this.maxHealth;
    }
}
