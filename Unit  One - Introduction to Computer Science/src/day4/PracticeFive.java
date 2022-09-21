package day4;

public class PracticeFive {

    public static void main(String[] args) {
    
        double pennyValue = 0.01;
        double nickelValue = 0.05;
        double dimeValue = 0.1;
        double quarterValue = 0.25;
        double loonieValue = 1;
        double toonieValue = 2;

        int pennies = 4;
        int nickels = 25;
        int dimes = 10;
        int quarters = 17;
        int loonies = 6;
        int toonies = 9;

        double value = pennies*pennyValue+nickels*nickelValue+dimes*dimeValue+quarters*quarterValue+loonies*loonieValue+toonies*toonieValue;

        System.out.println("The value of all the coins is: "+value);
    }
}
