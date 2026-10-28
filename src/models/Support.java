package models;

import java.util.List;
import java.util.Scanner;

public class Support extends Droid {
    int amountOfHeal;

    public Support(String name, int health, int damage, int maxHealth, int amountOfHeal) {
        super(name, health, damage, maxHealth);
        this.amountOfHeal = amountOfHeal;
    }
    public void heal(List<Droid> allies) {
        for (int i = 0; i < allies.size(); i++) {
            Droid d = allies.get(i);
            System.out.println("Імя дроіда" + d.getName() + "Поточне здоровя дроіда" + d.getHealth() + "Максимальне" + d.getMaxHealth());
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Введіть імя дроіда для лікування");
        String targetName = sc.nextLine();
        Droid target = null;
        for (int i = 0; i < allies.size(); i++) {
            Droid current = allies.get(i);
            if (current.getName().equalsIgnoreCase(targetName)) {
                target = current;
                break;
            }
        }
        if (target == null) {
            System.out.println("Такий дроід відсутній у вашій команді");
            return;
        }
        if (!target.isAlive() ) {
            System.out.println("На жаль,дроід під назвою" + target.getName() + "загинув");
            return;
        }
        target.receiveHeal(this.amountOfHeal);
    }
}
