package day5;

import java.util.Scanner;

public class ExampleTwo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Please enter a decimal number: ");
        double number = scan.nextDouble();

        number = Math.round(number * 100)/100.0;
        System.out.println(number);

        System.out.print("Please enter a number greater than 100: ");
        int iNumber = scan.nextInt();
        iNumber = (int) Math.round(iNumber/100.0)*100;
        System.out.println(iNumber);

        scan.close();
    }
}
