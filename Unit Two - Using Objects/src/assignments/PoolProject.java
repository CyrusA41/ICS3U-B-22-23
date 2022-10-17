package assignments;

import java.util.Scanner;

public class PoolProject{
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);

        //prompts
        
        System.out.println("Please enter a value, in metres, for the pool length: ");
        double poolLength = scan.nextDouble();
        
        System.out.println("Please enter a value, in metres, for the pool width: ");
        double poolWidth = scan.nextDouble();
        
        System.out.println("Please enter a value, in metres, for the shallow end length: ");
        double shallowLength = scan.nextDouble();
        
        System.out.println("Please enter a value, in metres, for the shallow end depth: ");
        double shallowHeight = scan.nextDouble();
        
        System.out.println("Please enter a value, in metres, for the length of the slope connecting the shallow and deep ends: ");
        double transition = scan.nextDouble();
        
        System.out.println("Please enter a value, in metres, for the deep end depth: ");
        double deepHeight = scan.nextDouble(); 
        
        System.out.println("Please enter a value, in metres, for the price of pool liner per square metre: ");
        double linerRate = scan.nextDouble();

        //extra calculations

        double heightDifference = deepHeight-shallowHeight;
        double flatTransition = Math.sqrt((Math.pow(transition, 2))-(Math.pow(heightDifference, 2)));
        double deepLength = poolLength-shallowLength-flatTransition;
        
        //volume

        double waterVolume = ((poolLength*poolWidth*shallowHeight)+(poolWidth*heightDifference*deepLength)+((poolWidth*flatTransition*heightDifference)/2))*0.9;
        System.out.println("The amount of water needed to fill 90% of the pool is "+waterVolume+" litres.");

        //surface area

        double surfaceArea = (2*poolLength*shallowHeight)+(poolWidth*shallowHeight)+(poolWidth*deepHeight)+(poolWidth*deepLength)+(poolWidth*shallowLength)+(transition*poolWidth)+(2*(deepHeight-shallowHeight)*deepLength)+((deepHeight-shallowHeight)*flatTransition);
        System.out.println("The surface area of the pool with the dimensions given is: "+surfaceArea+" square metres.");

        //cost of liner

        String rawPrice = String.valueOf(surfaceArea*linerRate);
        String roundedCost = rawPrice.substring(0,rawPrice.indexOf('.')+3);
        System.out.println("At a rate of $"+linerRate+" per square metre, it will cost $"+roundedCost+" to add liner to the pool.");

        scan.close();
    }
}
