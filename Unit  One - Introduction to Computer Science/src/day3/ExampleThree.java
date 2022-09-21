package day3;

public class ExampleThree {
    public static void main(String[] args) {
        
        int number = 51638;

        int digit1 = number % 10;
        int digit2 = number % 100 / 10;
        int digit3 = number % 1000 / 100;
        int digit4 = number % 10000 / 1000;
        int digit5 = number / 10000;

        System.out.println("the number is " + digit5+digit4+digit3+digit2+digit1);
    }
}
