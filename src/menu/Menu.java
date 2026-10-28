package menu;

import battle.BattleArena;
import battle.Team;
import models.Droid;
import models.Sniper;
import models.Support;
import models.Tank;
import storage.BattleRecorder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private List<Droid> droids = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private List<String> lastBattleLog = new ArrayList<>();
    private final String FILE_NAME = "battle_log.txt";

    public void start() {
        while (true) {
            System.out.println("\nГОЛОВНЕ МЕНЮ\n");
            System.out.println("1. Керування дроїдами");
            System.out.println("2. Бій");
            System.out.println("3. Робота з файлом");
            System.out.println("0. Вихід з програми\n");
            System.out.print("Ваш вибір: ");

            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    droidSubMenu();
                    break;
                case 2:
                    fightSubMenu();
                    break;
                case 3:
                    FileSubMenu();
                    break;
                case 0:
                    System.out.println("Завершення програми. До зустрічі!\n");
                    return;
                default:
                    System.out.println("Невірний пункт!\n");
                    break;
            }
        }
    }

    void droidSubMenu() {
        int choice;
        while (true) {
            System.out.println("\nОПЕРАЦІЇ З ДРОЇДАМИ\n");
            System.out.println("1. Створити нового дроїда");
            System.out.println("2. Показати всіх дроїдів");
            System.out.println("0. Повернутись назад\n");
            System.out.print("Ваш вибір: ");

            choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    createDroid();
                    break;
                case 2:
                    showAllDroids();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Невірний пункт!\n");
                    break;
            }
        }
    }

    void fightSubMenu() {
        int choice;
        while (true) {
            System.out.println("\nРЕЖИМ БИТВИ\n");
            System.out.println("1. Бій 1 на 1");
            System.out.println("2. Бій команда на команду");
            System.out.println("0. Повернення назад\n");
            System.out.print("Ваш вибір: ");

            choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    fightOnetoOne();
                    break;
                case 2:
                    fightTeamToTeam();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Невірний пункт!\n");
                    break;
            }
        }
    }

    void FileSubMenu() {
        int choice;
        while (true) {
            System.out.println("\nРОБОТА З ФАЙЛАМИ\n");
            System.out.println("1. Записати останній бій у файл");
            System.out.println("2. Відтворити бій з файлу");
            System.out.println("0. Повернутись назад\n");
            System.out.print("Ваш вибір: ");

            choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    saveFight();
                    break;
                case 2:
                    replayFight();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Невірний пункт!\n");
                    break;
            }
        }
    }

    void createDroid() {
        System.out.println("\nСТВОРЕННЯ ДРОЇДА\n");
        System.out.println("1. Support (Медик)");
        System.out.println("2. Tank (Броньований)");
        System.out.println("3. Sniper (Критичний урон)\n");
        System.out.print("Оберіть тип: ");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введіть ім'я дроїда: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            name = "Droid_" + (droids.size() + 1);
        }

        Droid created = null;
        switch (type) {
            case 1:
                created = new Support(name, 90, 15, 90, 25);
                break;
            case 2:
                created = new Tank(name, 140, 20, 140, 8);
                break;
            case 3:
                created = new Sniper(name, 80, 30, 80, 0.35);
                break;
            default:
                System.out.println("\nНевідомий тип!\n");
                return;
        }

        droids.add(created);
        System.out.println("\nДроїда " + created.getName() + " успішно створено!\n");
    }

    void showAllDroids() {
        if (droids.isEmpty()) {
            System.out.println("\nСписок порожній, спочатку створіть дроїда!\n");
            return;
        }

        System.out.println("\nСПИСОК ВСІХ ДРОЇДІВ\n");
        for (int i = 0; i < droids.size(); i++) {
            Droid d = droids.get(i);
            System.out.println((i + 1) + ". " + d.getName() + " [HP: " + d.getMaxHealth() + ", DMG: " + d.getDamage() + "]");
        }
        System.out.println();
    }

    void fightOnetoOne() {
        if (droids.size() < 2) {
            System.out.println("\nПотрібно мінімум 2 дроїди для поєдинку!\n");
            return;
        }

        showAllDroids();
        System.out.print("Введіть порядковий номер 1-го бійця: ");
        int idx1 = scanner.nextInt() - 1;
        System.out.print("Введіть порядковий номер 2-го бійця: ");
        int idx2 = scanner.nextInt() - 1;

        if (idx1 < 0 || idx1 >= droids.size() || idx2 < 0 || idx2 >= droids.size() || idx1 == idx2) {
            System.out.println("\nНекоректний вибір бійців!\n");
            return;
        }

        Droid d1 = droids.get(idx1);
        Droid d2 = droids.get(idx2);
        d1.resetHealth();
        d2.resetHealth();

        Team team1 = new Team(d1.getName());
        team1.addDroid(d1);

        Team team2 = new Team(d2.getName());
        team2.addDroid(d2);

        BattleArena arena = new BattleArena("Дуель 1 на 1", team1, team2);
        lastBattleLog = arena.startFight();
    }

    void fightTeamToTeam() {
        if (droids.size() < 2) {
            System.out.println("\nПотрібно мінімум 2 дроїди для бою!\n");
            return;
        }

        Team team1 = new Team("Команда 1");
        Team team2 = new Team("Команда 2");

        System.out.println("\nФормуємо Команду 1:\n");
        fillTeam(team1);

        System.out.println("\nФормуємо Команду 2:\n");
        fillTeam(team2);

        if (team1.getDroids().isEmpty() || team2.getDroids().isEmpty()) {
            System.out.println("\nОбидві команди повинні мати хоча б одного бійця!\n");
            return;
        }

        for (int i = 0; i < team1.getDroids().size(); i++) {
            team1.getDroids().get(i).resetHealth();
        }
        for (int i = 0; i < team2.getDroids().size(); i++) {
            team2.getDroids().get(i).resetHealth();
        }

        BattleArena arena = new BattleArena("Командна битва", team1, team2);
        lastBattleLog = arena.startFight();
    }

    void fillTeam(Team team) {
        showAllDroids();
        System.out.print("Скільки дроїдів додати у " + team.getName() + "? ");
        int count = scanner.nextInt();

        for (int i = 0; i < count; i++) {
            System.out.print("Введіть номер дроїда #" + (i + 1) + ": ");
            int idx = scanner.nextInt() - 1;
            if (idx >= 0 && idx < droids.size()) {
                team.addDroid(droids.get(idx));
            } else {
                System.out.println("Невірний номер, пропуск.");
            }
        }
        System.out.println();
    }

    void saveFight() {
        BattleRecorder.saveToFile(FILE_NAME, lastBattleLog);
    }

    void replayFight() {
        BattleRecorder.replayFromFile(FILE_NAME);
    }
}