package storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BattleRecorder {

    public static void saveToFile(String filePath, List<String> battleLog) {
        if (battleLog == null || battleLog.isEmpty()) {
            System.out.println("Немає проведеного бою для збереження!");
            return;
        }
        try {
            Files.write(Paths.get(filePath), battleLog);
            System.out.println("Бій збережено у файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Помилка збереження: " + e.getMessage());
        }
    }

    public static void replayFromFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Файл бою не знайдено: " + filePath);
            return;
        }
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            System.out.println("\n Відтворення бою з файлу ");
            for (int i = 0; i < lines.size(); i++) {
                System.out.println(lines.get(i));
                try {
                    Thread.sleep(250);
                } catch (InterruptedException ignored) {}
            }
            System.out.println("Кінець відтворення\n");
        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        }
    }
}