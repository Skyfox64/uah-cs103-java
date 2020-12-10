import java.util.Scanner;

public class App {
    static Scanner read = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        System.out.println("Assignment 1");

        System.out.println("Enter the number for the question that you want");

        int questionNumber = read.nextInt();

        switch (questionNumber) {
            case 1:
                question1();
                break;
            case 2:
                question2();
                break;
            case 3:
                question3();
                break;
            default:
                System.out.println("Not an option");
                break;
        }
    }
    private static void question3() {
        double radius = 7.3; 
        double pi = 3.14;
        // double pi = Math.PI;
        double area = pi * radius * radius;        
        System.out.println("Area of a circle with radius of " + radius + ": " + area);
    }

    private static void question2() {
        String degreePrompt = "Enter a degree in Fahrenheit to convert to Celcius";
        System.out.println(degreePrompt);

        double fahrenheit = read.nextDouble();

        double celcius = 5.0/9.0 * (fahrenheit - 32);

        System.out.println(celcius);
    }


    private static void question1() {
        String name;
        String gender;
        String major;
        int age;

        System.out.println("Question 1");

        System.out.println("Please enter your name");
        name = read.next();
        
        System.out.println("Please enter your gender");
        gender = read.next();

        System.out.println("Please enter your major");
        major = read.next();

        System.out.println("Please enter your age");
        age = read.nextInt();

        System.out.println("Your name is " + name);

        System.out.println("Your gender is " + gender);

        System.out.println("Your major is " + major);

        System.out.println("Your age is " + age);
    }
}
