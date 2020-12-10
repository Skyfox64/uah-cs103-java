import java.util.Scanner;

public class Homework3 {
    static Scanner read = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        System.out.println("Homework 3");

        question13();

    }

    private static void question2() {

        int number = 6;
        while (number > 0) {
            number -= 3;
            System.out.print(number + "");

        }
    }

    private static void question3a() {

        double sum = 0;
        for (int i = 1; i < 99; i++) {
            sum = i / (i + 1);
        }
        System.out.println("Sum is " + sum);

    }

    private static void question3b() {

        double sum = 0;
        for (int i = 1; i < 99; i++) {
            sum += i / (i + 1);
        }
        System.out.println("Sum is " + sum);

    }

    private static void question3c() {

        double sum = 0;
        for (int i = 1; i <= 99; i++) {
            sum += 1.0 * i / (i + 1);
        }
        System.out.println("Sum is " + sum);

    }

    private static void question3d() {

        double sum = 0;
        for (int i = 1; i <= 99; i++) {
            sum += i / (i + 1.0);
        }
        System.out.println("Sum is " + sum);

    }

    private static void question3e() {

        double sum = 0;
        for (int i = 1; i < 99; i++) {
            sum += i / (i + 1.0);
        }
        System.out.println("Sum is " + sum);

    }

    private static void question4() {

        int count = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < i; j++) {
                System.out.println(i * j);
                count++;
            }
        }
        System.out.println("Number of times printed: " + count++);
    }

    private static void question5() {

        int balance = 10;

        while (true) {
            if (balance < 9)
                break;
            balance = balance - 9;
        }
    }

    private static void question6() {
        System.out.println(1 / 0);

    }

    private static void question7() {

        String s = "abc";
        System.out.println(s.charAt(3));

    }

    private static void question13() {
        System.out.println(4 + 20/(3-1)*2);

    }

}
