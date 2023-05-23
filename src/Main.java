import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var count = 0;
        while (true) {
            System.out.println("Введите путь к файлу: ");
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();

            if (isDirectory || !fileExists) {
                System.out.println("Файл не существует или указанный путь является путём к папке!");
            } else {
                count++;
                System.out.println("Путь указан верно.");
                System.out.printf("Это файл номер %d. \n", count);
            }
            continue;
        }
    }
}