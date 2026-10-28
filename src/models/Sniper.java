package models;

import java.util.Random;

public class Sniper extends Droid {
    double criticalChance;

    public Sniper(String name, int health, int damage, int maxHealth, double criticalChance) {
        super(name, health, damage, maxHealth);
        this.criticalChance = criticalChance;
    }
    @Override
    public void attack(Droid target) {
        Random rand =new Random();
        double roll = rand.nextDouble();
        int finalDamage = this.damage;
        if (roll >= this.criticalChance) {
            double bonus = this.damage * this.criticalChance;
            finalDamage = (int) Math.round(this.damage + bonus);
            System.out.println("Крит (roll: " + String.format("%.2f", roll) + " >= " + this.criticalChance + ")");
        }
        target.receiveDamage(finalDamage);
        System.out.println(this.name + " завдає " + finalDamage + " урону по " + target.getName() + "!");
    }
}
