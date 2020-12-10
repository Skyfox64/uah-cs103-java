import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    static Scanner read = new Scanner(System.in);

    enum Grades {
        A(4), B(3), C(2), D(1), F(0);

        private int gradeIndex;

        private Grades(int gradeIndex) {
            this.gradeIndex = gradeIndex;
        }

        public static int getGPA(App.Grades gradeEnum) {
            for (Grades g : Grades.values()) {
                if (g == gradeEnum)
                    return g.gradeIndex;
            }

            throw new IllegalArgumentException("Grade not found");
        }
    }

    public static void main(String[] args) throws Exception {

        System.out.println("Programming Assignment 1");
        System.out.print("1 Basic Calculator");
        System.out.print("2 Loan Calculator");
        System.out.print("3 GPA and Grade");
        System.out.print("4 Student Demographics");
        System.out.print("5 Credit Card Validator");

        System.out.print("Enter the number for the question that you want: ");

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
            case 4:
                question4();
                break;
            case 5:
                question5();
                break;
            default:
                System.out.println("Not an option");
                break;
        }
    }

    private static void question5() {
        System.out.println("Optional: Check Credit Card");

        long ccNum;
        System.out.print("Enter a 16-digit Credit Card Number: ");
        ccNum = read.nextLong();

        String str = Long.toString(ccNum);

        if (str.length() != 16)
        {
            System.out.print("Please, enter a 16-digit Credit Card Number");
            return;
        }
        
        int[] ints = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            ints[i] = Integer.parseInt(str.substring(i, i + 1));
        }
        for (int i = ints.length - 2; i >= 0; i = i - 2) {
            int j = ints[i];
            j = j * 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            ints[i] = j;
        }
        int sum = 0;
        for (int i = 0; i < ints.length; i++) {
            sum += ints[i];
        }
        if (sum % 10 == 0) {
            System.out.println(str + " is a valid credit card number");
        } else {
            System.out.println(str + " is an invalid credit card number");
        }

    }

    private static void question4() {
        System.out.println("Question4 - Students");

        String name;
        String gender;
        String major;
        int age;

        System.out.println("Please enter your name");
        name = read.next();

        System.out.println("Please enter your age");
        age = read.nextInt();

        System.out.println("Please enter your major");
        major = read.next();

        System.out.println("Please enter your gender");
        gender = read.next();

        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append("\n");
        sb.append(age);
        sb.append("\n");
        sb.append(major);
        sb.append("\n");
        sb.append(gender);
        sb.append("\n");

        // Creating the directory
        File studentsFolder = new File("Students");
        studentsFolder.mkdir(); // Make Folder
        boolean bool = studentsFolder.exists();
        if (bool) {
            System.out.println("Directory created successfully");

            // Creating a File object
            File studentFile = new File(studentsFolder.getAbsolutePath() + "\\" + name + ".txt");
            try (FileWriter writer = new FileWriter(studentFile)) {
                writer.write(sb.toString());
                writer.flush();
            } catch (IOException ex) {
                System.out.println(ex);
            }

        } else {
            System.out.println("Sorry couldn’t create specified directory");
        }
    }

    private static void question3() {
        System.out.println("Question3 - Display Grade and GPA");

        System.out.print("Enter the class grade 0-100 (i.e 96, 32): ");
        double grade = read.nextDouble();
        // double grade = 84; //hardcoded for display example
        Grades gradeEnum;
        if (grade >= 90) {
            gradeEnum = Grades.A;
        } else if (grade >= 80) {
            gradeEnum = Grades.B;
        } else if (grade >= 70) {
            gradeEnum = Grades.C;
        } else if (grade >= 60) {
            gradeEnum = Grades.D;
        } else {
            gradeEnum = Grades.F;
        }

        System.out.println("Grade = " + grade);
        System.out.println("GPA = " + ((double) Grades.getGPA(gradeEnum)));
    }

    private static void question2() {
        System.out.println("Question2 - Monthly Payment");

        System.out.println("Enter the Interest Rate (enter 3% as 0.03)");
        double interestRate = read.nextDouble();
        double monthlyInterestRate = div(interestRate, 12);

        System.out.println("Enter the years of the loan");
        double numberOfYears = read.nextDouble();
        double numberOfMonths = mult(numberOfYears, 12);

        System.out.println("Enter the loan amount");
        double loanAmount = read.nextDouble();

        double dividend = mult(loanAmount, monthlyInterestRate);
        double divisor = sub(1, div(1, exp(add(1, monthlyInterestRate), numberOfMonths)));

        double monthlyPayment = div(dividend, divisor);

        System.out.println("Monthly Payment: " + monthlyPayment);
        System.out.println("Total Payment: " + mult(monthlyPayment, numberOfMonths));
    }

    private static void question1() {

        double num1, num2;
        System.out.println("Calculator Program");
        System.out.println("1 for Addition");
        System.out.println("2 for Substraction");
        System.out.println("3 for Multiplication");
        System.out.println("4 for Division");
        System.out.println("5 for Modulo");
        System.out.println("6 for Exponent");
        System.out.println("7 for Sine");
        System.out.println("8 for Cosine");
        System.out.println("9 for Tangent");
        System.out.println("10 for Square Root");
        System.out.print("Enter your selection: ");

        ArrayList<Integer> inputs = new ArrayList<Integer>();
        double output = 0;
        int choice = read.nextInt();
        switch (choice) {
            case 1: // Addition
                inputs = twoInputs();
                num1 = inputs.get(0);
                num2 = inputs.get(1);
                output = add(num1, num2);
                break;
            case 2: // Subtraction
                inputs = twoInputs();
                num1 = inputs.get(0);
                num2 = inputs.get(1);
                output = sub(num1, num2);
                break;
            case 3: // Multiplication
                inputs = twoInputs();
                num1 = inputs.get(0);
                num2 = inputs.get(1);
                output = mult(num1, num2);
                break;
            case 4: // Division
                inputs = twoInputs();
                num1 = inputs.get(0);
                num2 = inputs.get(1);
                output = div(num1, num2);
                break;
            case 5: // Modulo
                inputs = twoInputs();
                num1 = inputs.get(0);
                num2 = inputs.get(1);
                output = mod(num1, num2);
                break;
            case 6: // Exponent
                inputs = twoInputs();
                num1 = inputs.get(0);
                num2 = inputs.get(1);
                output = exp(num1, num2);
                break;
            case 7: // Sine
                inputs = oneInput();
                num1 = inputs.get(0);
                output = sin(num1);
                break;
            case 8: // Cosine
                inputs = oneInput();
                num1 = inputs.get(0);
                output = cos(num1);
                break;
            case 9: // Tangent
                inputs = oneInput();
                num1 = inputs.get(0);
                output = tan(num1);
                break;
            case 10: // Square Root
                inputs = oneInput();
                num1 = inputs.get(0);
                output = sqrt(num1);
                break;
            default:
                System.out.println("Illegal Operation");
        }

        System.out.println("Answer: " + output);
    }

    private static double sqrt(double num1) {
        return Math.sqrt(num1);
    }

    private static double tan(double num1) {
        return Math.tan(num1);
    }

    private static double cos(double num1) {
        return Math.cos(num1);
    }

    private static double sin(double num1) {
        return Math.sin(num1);
    }

    private static double exp(double num1, double num2) {
        return Math.pow(num1, num2);
    }

    private static double mod(double num1, double num2) {
        return num1 % num2;
    }

    private static double add(double x, double y) {
        return x + y;
    }

    private static double sub(double x, double y) {
        return x - y;
    }

    private static double mult(double x, double y) {
        return x * y;
    }

    private static double div(double x, double y) {
        return x / y;
    }

    private static ArrayList<Integer> oneInput() {
        ArrayList<Integer> oneInput = new ArrayList<Integer>();
        System.out.println("Enter number:");
        oneInput.add(read.nextInt());
        return oneInput;
    }

    private static ArrayList<Integer> twoInputs() {
        ArrayList<Integer> twoInputs = new ArrayList<Integer>();
        System.out.println("Enter first and second number:");
        twoInputs.add(read.nextInt());
        twoInputs.add(read.nextInt());
        return twoInputs;
    }

}
