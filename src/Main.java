import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите первое число: ");
        int numberFirst = scanner.nextInt();
        System.out.println("Введите второе число: ");
        int numberSecond = scanner.nextInt();

        System.out.println("Сложение: " + numberFirst + " + " + numberSecond + " = " + (numberFirst + numberSecond));
        System.out.println("Вычитание: " + numberFirst + " - " + numberSecond + " = " + (numberFirst - numberSecond));
        System.out.println("Умножение: " + numberFirst + " * " + numberSecond + " = " + (numberFirst * numberSecond));
        System.out.println("Деление: " + numberFirst + " / " + numberSecond + " = " + (double) (numberFirst / numberSecond));
    }
}
