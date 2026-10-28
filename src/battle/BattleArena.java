package battle;

import models.Droid;

import java.util.ArrayList;
import java.util.List;

public class BattleArena {
    String name;
    private Team team1;
    private Team team2;
    private List<String> battleLog;
    public BattleArena(String name, Team team1, Team team2) {
        this.name = name;
        this.team1 = team1;
        this.team2 = team2;
        this.battleLog = new ArrayList<>();
    }
    private void log(String message) {
        System.out.println(message);
        this.battleLog.add(message);
    }



    public List<String> startFight() {
        log("\n");
        log("Бій на арені \"" + this.name + "\": " + team1.getName() + " проти " + team2.getName());
        log("");
        int round = 1;
        while (team1.isAlive() && team2.isAlive()) {
            log("\n Раунд " + round++ + " ---");
            takeTurn(team1, team2);
            if (!team2.isAlive())
                break;
            takeTurn(team2, team1);
        }

        Team winner = team1.isAlive() ? team1 : team2;
        log("\n");
        log("Перемога: команда " + winner.getName() + " здобула перемогу!");
        log("");
        return battleLog;
    }
    private void takeTurn(Team attackers, Team defenders) {
        for (int i = 0; i < attackers.getDroids().size(); i++) {
            Droid attacker = attackers.getDroids().get(i);
            if (!attacker.isAlive() || !defenders.isAlive())
                continue;
            Droid target = defenders.getRandomAliveDroid();
            if (target != null) {
                attacker.attack(target);
                log("   " + target.getName() + " [HP: " + target.getHealth() + "/" + target.getMaxHealth() + "]");
                if (!target.isAlive()) {
                    log("Смерть" + target.getName() + " знищено!");
                }
            }
        }
    }
}