import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        System.out.println("Homework 2");
        question24();
    }

    private static void question24() {

        int resident_age = sc.nextInt();

        if (resident_age >= 18)
            System.out.println("Can vote");
        else
            System.out.println("Cannot vote");

        // Convert above to ternary operator expression:
        System.out.println((resident_age >= 18)? "Can vote" : "Cannot vote");

    }

    private static void question23() {

        String dayString;

        int day = sc.nextInt();

        switch (day) {
            case 1:
                dayString = "Monday";
                break;
            case 2:
                dayString = "Tuesday";
                break;
            case 3:
                dayString = "Wednesday";
                break;
            case 4:
                dayString = "Thursday";
                break;
            case 5:
                dayString = "Friday";
                break;
            case 6:
                dayString = "Saturday";
                break;
            case 7:
                dayString = "Sunday";
                break;
            default:
                dayString = "Invalid day";
                break;
        }

        if (day == 1)
            System.out.println("Monday");

        else if (day == 2)
            System.out.println("Tuesday");

        else if (day == 3)
            System.out.println("Wednesday");

        else if (day == 4)
            System.out.println("Thursday");

        else if (day == 5)
            System.out.println("Friday");

        else if (day == 6)
            System.out.println("Saturday");

        else if (day == 7)
            System.out.println("Sunday");

        else
            System.out.println("Invalid Day");

        System.out.println(dayString);
    }

    private static void question22() {

        int season = sc.nextInt();
        switch (season) {
            case 1:
                System.out.println("Spring");
                break;
            case 2:
                System.out.println("Summer");
                break;
            case 3:
                System.out.println("Fall");
                break;
            default:
                System.out.println("Winter");
                break;
        }
    }

    private static void question19() {
        String s1 = "s1";
        String s2 = "s2";

        System.out.println(s1.equals(s2) == s2.equals(s1));
    }

    private static void question15() {

        String x = "Java " + 1 + 2 + 3;
        System.out.println(x);
    }

    private static void question14() {
        System.out.println(Math.floor(3.6));
        System.out.println(Math.ceil(3.6));

    }

    private static void question13() {
        boolean even = false;
        System.out.println((even ? "true" : "false"));
        String output = "";
        if (even == true)
            output = "true";
        else
            output = "false";

        System.out.println(output);

    }

    private static void question11() {
        // int x;
        // double d = 1.5;
        // switch (d) {
        // case 1.0: x = 1;
        // case 1.5: x = 2;
        // case 2.0: x = 3;

    }

    private static void question10() {
        int x = 1;
        int y = x = x + 1;
        System.out.println("y is " + y);

    }

    private static void question9() {

        if (true || true && false) {
            System.out.println("true");

        } else {
            System.out.println("false");

        }

    }

    private static void question8() {
        double num = 0.5;
        double answer = Math.asin(num);
        System.out.println(answer);

    }

    private static void question7() {
        int x = 4;
        if (x != 4) {

        }
        ;
    }

    private static void question6() {
        int age = 16;
        if (age < 16)
            System.out.println("Cannot get a driver's license");
        else if (age >= 16)
            System.out.println("Can get a driver's license");
    }

    private static void question4a() {
        int number = 45;
        boolean even;

        int remainder = number % 2;

        if (remainder == 0)
            even = true;
        else
            even = false;

        System.out.println("even = " + even);

    }

    private static void question4b() {

        int number = 45;
        boolean even = (number % 2 == 0);
        System.out.println("even = " + even);

    }

    private static void question3() {
        boolean isPrime = true;

        if (isPrime == true) {
            System.out.println("stuff");
        }
    }

    private static void question2() {
        int x = 1;
        int y = -1;
        int z = 1;

        if (x > 0)
            if (y > 0)
                System.out.println("X > 0 AND Y > 0");
            else if (z > 0)
                System.out.println("x < 0 and z > 0");
    }

    private static void question1() {
        boolean even = false;
        if (even = true) {
            System.out.println("It is even");
        }

    }
}
