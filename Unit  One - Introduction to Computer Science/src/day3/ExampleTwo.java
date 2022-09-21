package day3;

public class ExampleTwo {
    public static void main(String[] args) {
        int age1 = 16;
        int age2 = 17;
        int age3 = 20;
        
        final int NUMBER_OF_STUDENTS = 3;       //do not declare as a double
        double averageAge;

        averageAge = (double)(age1 + age2 + age3)/ NUMBER_OF_STUDENTS;
        
        System.out.println(averageAge);

        int x = (int) 6.4;
    }
}
