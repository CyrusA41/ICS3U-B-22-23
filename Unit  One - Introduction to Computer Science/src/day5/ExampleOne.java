package day5;

import java.util.Scanner;

public class ExampleOne {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Please enter the radius: ");
        double radius = scan.nextDouble();

        double area = Math.PI*Math.pow(radius,2);

        System.out.println("The area of the circle with radius " +radius+ " is: " +area);

        scan.close();
    }
}
