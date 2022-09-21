package day3;

/*
 * ExampleOne: Primitive Data Types
 * Variables
 */

public class ExampleOne {
    public static void main(String[] args) {
        
        //Variables are meant to store and eventually recall data/information

        //The data could be anything (a number, a string of characters, true/false, list of accounts, etc...)

        //Before we use a variable we must declare it. 

        //When we declare a variable, we must state the type of data it will hold

        //Primative types store the value (THEY ARE PRIMITIVE)
        //int -> integer
        //double -> decimal numbers
        //boolean -> true/false

        int numberOfStudents;       //numberOfStudents has been declared as an integer
        numberOfStudents = 14;
        double average = 63.7;      //declared and initialized on the same line (gave it an initial value)
        
        final double PI = 3.14;     //if the variable cannot be modified after it has been initialized then make it final
        //PI = 6.2;                   Syntax error (won't compile) because PI is final
        //Naming convention for constants (final) --> ALL_UPPER_CASE_WITH_UNDERSCORES_BETWEEN_WORDS

        boolean isHeads = false;
        boolean hasToes = true;

        System.out.println("There are " + numberOfStudents + " students in the class.");
        System.out.println("5 + 3 = " + 5 + 3);     //5 + 3 = 53
        System.out.println("5 + 3 = " + (5 + 3));       //5 + 3 = 53
    }
}