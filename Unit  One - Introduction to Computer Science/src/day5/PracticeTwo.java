package day5;

public class PracticeTwo {
    public static void main(String[] args) {
        double length = 4.5;
        double width = 2.3;
        double perim = Math.round((2*length + 2*width)*10)/10.0;
        double area = Math.round((length*width)*10)/10.0;

        System.out.println("The perimiter is: " +perim+ " feet.");
        System.out.print("The area is: " +area+ " sqare feet.");
    }
}
