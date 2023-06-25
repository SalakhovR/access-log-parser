import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите путь к файлу: ");
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();
            var countSearchFile = 0;

            if (isDirectory || !fileExists) {
                System.out.println("Файл не существует или указанный путь является путём к папке!");
            } else {
                countSearchFile++;
                System.out.println("Путь указан верно.");
                System.out.printf("Это файл номер %d. \n", countSearchFile);

                var countLines = 0;
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;

                try {
                    FileReader fileReader = new FileReader(path);
                    BufferedReader reader =
                            new BufferedReader(fileReader);
                    String line;
                    while ((line = reader.readLine()) != null) {
                        countLines++;
                        int length = line.length();
                        if (length >= 1024) {
                            throw new RuntimeException("Длина одной из строки превышает или равна 1024 символам");
                        } else if (length > max) {
                            max = length;
                        } else if (length < min) {
                            min = length;
                        }
                    }
                    reader.close();
                    fileReader.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("Общее количество строк в файле: " + countLines);
                System.out.println("Длина самой длинной строки в файле: " + max);
                System.out.println("Длина самой короткой строки в файле: " + min);
            }
        }
    }
}