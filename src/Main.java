import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> IP = new ArrayList<>();
        ArrayList<String> dateTime = new ArrayList<>();
        ArrayList<String> method = new ArrayList<>();
        ArrayList<String> statusCode = new ArrayList<>();
        ArrayList<String> size = new ArrayList<>();
        ArrayList<String> pathToPage = new ArrayList<>();
        ArrayList<String> userAgent = new ArrayList<>();
        double countLines = 0;
        double countYandexBot = 0;
        double countGoogleBot = 0;
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

                try {
                    FileReader fileReader = new FileReader(path);
                    BufferedReader reader =
                            new BufferedReader(fileReader);
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.length() > 1024) {
                            throw new RuntimeException("Длина одной из строки превышает или равна 1024 символам");
                        } else {
                            countLines++;
                            IP.add(line.substring(0, line.indexOf(" ")));

                            dateTime.add(line.substring(line
                                    .indexOf("[") + 1, line.indexOf("]")));

                            method.add(line.substring(line
                                    .indexOf("\"") + 1, line
                                    .indexOf("\"", line.indexOf("\"") + 1)));

                            int lastChar = line.indexOf("\"", line.indexOf("\"") + 1) + 2;
                            statusCode.add(line.substring(line.indexOf("\"", line
                                    .indexOf("\"") + 1) + 1, line.indexOf(" ", lastChar)).trim());

                            size.add(line.substring(line.indexOf(" ", lastChar), (line
                                    .indexOf("\"", lastChar))).trim());

                            int nextLastChar = line.indexOf("\"", line.indexOf("\"", lastChar) + 1);
                            pathToPage.add(line.substring(line.indexOf("\"", lastChar) + 1, nextLastChar));

                            userAgent.add(line.substring(nextLastChar + 3, line.length() - 1));
                        }
                    }
                    reader.close();
                    fileReader.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            for (String userBotLine : userAgent) {
                if (userBotLine.contains("(") && userBotLine.contains(")")) {
                    String firstBrackets = userBotLine.substring(userBotLine.indexOf("("), userBotLine.indexOf(")"));
                    String[] parts = firstBrackets.split(";");
                    if (parts.length >= 2 && parts[1].contains("/")) {
                        String fragment = parts[1].trim().substring(0, parts[1].indexOf("/") - 1);
                        if (fragment.equalsIgnoreCase("YandexBot")) {
                            countYandexBot++;
                        } else if (fragment.equalsIgnoreCase("GoogleBot")) {
                            countGoogleBot++;
/*полученное тут число отличается от числа, полученного при поиске через ctrl+f файле, т.к. по условию нужно
искать в первых скобках, а googlebot встречается и в других*/
                        }
                    }
                }
            }

            System.out.println("Общая доля запросов от YandexBot и GoogleBot к веб-сайту относительно общего числа " +
                    "сделанных запросов: " + ((countYandexBot + countGoogleBot) / countLines) * 100 + "%");
            System.out.println("Общая доля запросов от YandexBot к веб-сайту относительно общего числа " +
                    "сделанных запросов: " + (countYandexBot / countLines) * 100 + "%");
            System.out.println("Общая доля запросов от GoogleBot к веб-сайту относительно общего числа " +
                    "сделанных запросов: " + (countGoogleBot / countLines) * 100 + "%");
        }
    }
}