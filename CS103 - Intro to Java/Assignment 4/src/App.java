import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Homework Assignment 4
 * 
 */
public class App {
    static Scanner read = new Scanner(System.in);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    enum LetterGrade {
        A(4), B(3), C(2), D(1), F(0);

        private int gradeIndex;

        private LetterGrade(int gradeIndex) {
            this.gradeIndex = gradeIndex;
        }

        public static int getGPA(App.LetterGrade gradeEnum) {
            for (LetterGrade g : LetterGrade.values()) {
                if (g == gradeEnum)
                    return g.gradeIndex;
            }

            throw new IllegalArgumentException("Grade not found");
        }

        public static LetterGrade getGrade(int grade) {
            LetterGrade gradeEnum;
            if (grade >= 90) {
                gradeEnum = LetterGrade.A;
            } else if (grade >= 80) {
                gradeEnum = LetterGrade.B;
            } else if (grade >= 70) {
                gradeEnum = LetterGrade.C;
            } else if (grade >= 60) {
                gradeEnum = LetterGrade.D;
            } else {
                gradeEnum = LetterGrade.F;
            }
            return gradeEnum;
        }
    }

    // class TempClass {
    //     int i;
    //     public void TempClass(int j) {
    //         int i = j;
    //     }
    // }

    public static void main(String[] args) throws Exception {

        String s = "University";
        String b = s.replace("i", "ABC");
        System.out.println(s);
        System.out.println(b);
        // System.out.println("Programming Assignment 2");
        // System.out.println("1 Display Frequency of words that make up a sentence");
        // System.out.println("2 Calculate GPA for 4 classes");
        // System.out.println("3 Display maximum and minimum numbers of array of doubles");
        // System.out.println("4 Bonus Read in text file and display Frequency of words that make up a sentence");

        // System.out.print("Enter the number for the question that you want: ");

        // int questionNumber = read.nextInt();

        // TempClass temp = new TempClass(2);
        // question9();

        // switch (questionNumber) {
        //     case 1:
        //         question1();
        //         break;
        //     case 2:
        //         question2();
        //         break;
        //     case 3:
        //         question3();
        //         break;
        //     case 4:
        //         bonusQuestion4();
        //         break;
        //     default:
        //         System.out.println("Not an option");
        //         break;
        // }

        // Foo f1 = new Foo();
        // System.out.println("f1.i is " + f1.i + " f1.s is " + f1.s);
        // Foo f2 = new Foo();
        // System.out.println("f1.i is " + f2.i + " f1.s is " + f2.s);
        // Foo f3 = new Foo();
        // System.out.println("f1.i is " + f3.i + " f1.s is " + f3.s);        
    }

    public static class Foo {
        int i;
        static int s;

        public Foo() {
            i++;
            s++;
        }
    }

    //This is question 11 below
    // Convert UML to code
    public class Toyota {
        private String model;
        private String color;
        private int MPG;
        public int vin;
        public boolean start;

        public Toyota(int vin, int mpg) {
            this.vin = vin;
            this.MPG = mpg;
        }

        public void setModel(String m){
            this.model = m;
        }

        public String getModel() {
            return model;
        }

        public void setColor(String c) {
            this.color = c;
        }

        public String getColor() {
            return color;
        }

        public boolean canStart() {
            return start;
        }

        public void setStart(boolean s) {
            this.start = s;
        }
    }

    private static void bonusQuestion4() throws IOException {
        System.out
                .println("Question 4 - Bonus Read in text file and display Frequency of words that make up a sentence");
        System.out.print("Enter a file path: ");
        String filePath = br.readLine();

        try (BufferedReader buffRead = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line = buffRead.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = buffRead.readLine();
            }
            String everything = sb.toString();

            Stream<String> stream = Stream.of(everything.toLowerCase().split("\\W+")).parallel();
            Map<String, Long> wordFreq = stream.collect(Collectors.groupingBy(String::toString, Collectors.counting()));
            System.out.print("The frequency of each unique word: ");
            System.out.println(wordFreq);

        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
    }

    private static void question3() {
        System.out.println("Question 3 - Display maximum and minimum numbers");
        System.out.print("Enter the size of the array: ");
        int arraySize = read.nextInt();

        Double num[] = new Double[arraySize];

        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        for (int i = 0; i < num.length; i++) {
            System.out.print("Enter a number: ");
            num[i] = read.nextDouble();
            if (num[i] < min) {
                min = num[i];
            } else if (num[i] > max) {
                max = num[i];
            }
        }

        if (num.length == 0) {
            System.out.println(Arrays.toString(num));
            System.out.println("Minimum and Maximum N/A");
        } else if (num.length == 1) {
            List<?> list = Arrays.asList(num);

            max = min;
            min = max;

            System.out.println(Arrays.toString(num));
            System.out.println("Minimum = " + fmt(min) + " at index " + list.indexOf(min));
            System.out.println("Maximum = " + fmt(max) + " at index " + list.indexOf(max));
        } else {
            List<?> list = Arrays.asList(num);

            System.out.println(Arrays.toString(num));
            System.out.println("Minimum = " + fmt(min) + " at index " + list.indexOf(min));
            System.out.println("Maximum = " + fmt(max) + " at index " + list.indexOf(max));
        }
    }

    public static String fmt(double d) {
        if (d == (long) d)
            return String.format("%d", (long) d);
        else
            return String.format("%s", d);
    }

    private static void question2() {
        System.out.println("Question 2 - Calculate Semester GPA for 4 classes");
        System.out.println("Enter information for 4 classes");

        double courseCreditHourSum = 0.0;
        double courseWeightedGPASum = 0.0;

        for (int i = 1; i < 5; i++) {
            System.out.println("Enter information for class " + i);

            System.out.print("Enter the course code: ");
            String courseCode = read.next();

            System.out.print("Enter the course credit hour: ");
            int courseCreditHour = read.nextInt();
            courseCreditHourSum += courseCreditHour;

            System.out.print("Enter the course total grade score (0-100; i.e 96, 32): ");
            double grade = read.nextDouble();
            LetterGrade courseGrade = LetterGrade.getGrade((int) grade);
            double courseGPA = LetterGrade.getGPA(courseGrade);
            courseWeightedGPASum += courseGPA * courseCreditHour;

            System.out.println(
                    String.format("Course Code:%s | hours: %d | GPA: %.1f", courseCode, courseCreditHour, courseGPA));
        }

        double semesterGPA = courseWeightedGPASum / courseCreditHourSum;
        System.out.println(String.format("Semester GPA = %.2f", semesterGPA));
    }

    private static void question4() throws IOException {
        // System.out.println("Question 1 - Display Frequency of words that make up a
        // sentence");

        // System.out.print("Enter a sentence or group of words, then press enter: ");
        // String text = br.readLine();
        // Stream<String> stream =
        // Stream.of(text.toLowerCase().split("\\W+")).parallel();
        // Map<String, Long> wordFreq =
        // stream.collect(Collectors.groupingBy(String::toString,
        // Collectors.counting()));

        // System.out.print("The frequency of each unique word: ");
        // System.out.println(wordFreq);

        int[][] values = { { 3, 4, 5, 1 }, { 33, 6, 1, 2 } };

        int v = values[0][0];

        for (int row = 0; row < values.length; row++)
            for (int column = 0; column < values[row].length; column++)
                if (v < values[row][column])
                    v = values[row][column];

        System.out.print(v);
    }

    private static void question5() throws IOException {
        int[][][] data = {{{1, 2}, {3, 4}}, {{5, 6}, {7, 8}}};

        System.out.print(data[1][0][0]);
    }

    private static void question9() throws IOException {
 
        // int[] a = new int[2];
        // int a[] = new int[2];
        // int[] a = new int(2);
        // int a = new int[2];
        // int a() = new int[2];

        // String[] list = new String{"red","yellow","green"};
        // String[] list = new String[]{"red","yellow","green"};
        // String[] list = {"red","yellow","green"};
        // String list = {"red","yellow","green"};
        // String list = new String{"red","yellow","green"};

        // System.out.println(factorial(5));
        //System.out.println(m(2));
    }

    //public static long factorial(int n)
    {
       // if (n==0) //base case
        {
       //     return 1;
        }
       // else
        {
            // return factorial(n - 1) * n;
           // return n * factorial(n - 1);
        }
    }

   // public static int m(int num)
    {
       // return num;
    }

   // public static void m(int num)
    {
       // System.out.println(num);
    }

}


