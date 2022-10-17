package day5;

public class PracticeOne {
    public static void main(String[] args) {
        double price = 985;
        double tax = 1.055;
        double finalPrice = Math.round(price*tax*100)/100.0;

        System.out.println("The final price is: $" +finalPrice);
    }
}
