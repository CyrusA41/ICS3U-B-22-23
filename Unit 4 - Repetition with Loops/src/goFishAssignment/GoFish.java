package goFishAssignment;

import java.util.Scanner;

public class GoFish {

    //variables that will not change throughout the code
    static Scanner in = new Scanner(System.in);
    private static final int NUM_SUITS = 4;
    private static final String HEARTS = "H";
    private static final String SPADES = "S";
    private static final String DIAMONDS = "D";
    private static final String CLUBS = "C";
    private static final int NUM_VALUES = 13;
    private static final String TEN = "X";
    private static final String ACE = "A";
    private static final String JACK = "J";
    private static final String QUEEN = "Q";
    private static final String KING = "K";
    private static final int PLAYER = 0;
    private static final int CPU1 = 1;
    private static final int CPU2 = 2;
    private static final int CPU3 = 3;

    //player and cpu hand and score variables
    static String playerHand = dealHand();
    static String cpu1Hand = dealHand();
    static String cpu2Hand = dealHand();
    static String cpu3Hand = dealHand();

    static int playerScore = 0;
    static int cpu1Score = 0;
    static int cpu2Score = 0;
    static int cpu3Score = 0;
    static boolean winner = false;
    static boolean isOver = false;

    public static void main(String[] args) throws InterruptedException { //starts the game and checks for pairs in player hands
        while(!isOver){
            System.out.println("");
            Thread.sleep(1000);System.out.println("Welcome to Go Fish!");
            Thread.sleep(1000);System.out.println("Try to get as many pairs of cards as you can by asking other players");
            Thread.sleep(1000);System.out.println("Each pair is worth 1 point, first player to 10 points wins!");
            System.out.println("");

            Thread.sleep(1000);displayHand(PLAYER);
            Thread.sleep(500);displayHand(CPU1);
            Thread.sleep(500);displayHand(CPU2);
            Thread.sleep(500);displayHand(CPU3);

            System.out.println("");
            Thread.sleep(1000);System.out.println("Scoring any pairs that the players may have received...");
            System.out.println("");

            depositPairs(playerHand, PLAYER); 
            depositPairs(cpu1Hand, CPU1);
            depositPairs(cpu2Hand, CPU2);
            depositPairs(cpu3Hand, CPU3);

            Thread.sleep(1000);displayHand(PLAYER);
            Thread.sleep(500);displayHand(CPU1);
            Thread.sleep(500);displayHand(CPU2);
            Thread.sleep(500);displayHand(CPU3);

            System.out.println("");
        
            while(!winner){
                playerTurn();
                cpuTurn(CPU1);
                cpuTurn(CPU2);
                cpuTurn(CPU3);
            }
        }
    }

    private static void noCards() throws InterruptedException { //replaces a hand if it reaches 0 cards
        if(playerHand.length()<=1){
            Thread.sleep(1000);System.out.println("You hit 0 cards, drawing 5 new cards...");
            playerHand = dealHand();
            depositPairs(playerHand, PLAYER);
        }if(cpu1Hand.length()<=1){
            Thread.sleep(1000);System.out.println("CPU 1 has ran out of cards. Dealing 5 new cards to CPU 1...");
            cpu1Hand = dealHand();
            depositPairs(cpu1Hand, CPU1);
        }if(cpu2Hand.length()<=1){
            Thread.sleep(1000);System.out.println("CPU 2 has ran out of cards. Dealing 5 new cards to CPU 2...");
            cpu2Hand = dealHand();
            depositPairs(cpu2Hand, CPU2);
        }if(cpu3Hand.length()<=1){
            Thread.sleep(1000);System.out.println("CPU 3 has ran out of cards. Dealing 5 new cards to CPU 3...");
            cpu3Hand = dealHand();
            depositPairs(cpu3Hand, CPU3);
        }
    }

    public static String dealHand(){ //returns a new hand with 5 cards
        return getCard() + " " + getCard() + " " + getCard() + " " + getCard() + " " + getCard();
    }

    private static void cpuTurn(int n) throws InterruptedException { //starts a new cpu turn
        Thread.sleep(1000);System.out.println("\nCPU "+n+"'s turn...");
        int targetPlayer = 0;
        while(!isOver){
            targetPlayer = (int) (Math.random()*4);
            if(targetPlayer==n)
                continue;
            else
                break;
        }
        
        readHand(targetPlayer, n);
        noCards();
        if(cpu1Score==10)
            endGame(CPU1);
        if(cpu2Score==10)
            endGame(CPU2);
        if(cpu3Score==10)
            endGame(CPU3);
    }

    private static void playerTurn() throws InterruptedException { //starts the player turn. Player is asked which of the 3 actions they wish to perform
        while(true){
            Thread.sleep(1000);System.out.println("What would you like to do?");

            int move = playerMove();
            int player = selectPlayer(move);

            if(move==1) {
                readHand(player, PLAYER);
                noCards();
                if(playerScore==10)
                    endGame(PLAYER);

                break;
            }
            if(move==2)
                displayHand(player);
            if(move==3)
                displayScore(player);
        }
    }

    private static void endGame(int p) throws InterruptedException { //if somebody's score reaches 10, the game ends
        if(p==PLAYER){
            System.out.println("\nYou won the game!");
            Thread.sleep(1000);System.out.println("\nThanks for playing!");
            System.out.println("");
            winner = true;
            isOver = true;
        }else if(p==CPU1){
            System.out.println("\nCPU 1 has won the game!");
            Thread.sleep(1000);System.out.println("\nThanks for playing!");
            System.out.println("");
            winner = true;
            isOver = true;
        }else if(p==CPU2){
            System.out.println("\nCPU 2 has won the game!");
            Thread.sleep(1000);System.out.println("\nThanks for playing!");
            System.out.println("");
            winner = true;
            isOver = true;
        }else{
            System.out.println("\nCPU 3 has won the game!");
            Thread.sleep(1000);System.out.println("\nThanks for playing!");
            System.out.println("");
            winner = true;
            isOver = true;
        }
    }

    private static void readHand(int opponent, int asker) throws InterruptedException { //Checks if opponent(person asked) has the card which was requested by the asker(person whos turn it is)
        String card = "";                                                               //If yes, asker receives the card requested and deposits the pair
        if(asker==PLAYER)                                                               //If no, go fish is called, and asker draws a new card
            card = playerAskForCards();
        else
            card = cpuAskForCards(asker);
        
        String hand = card+"C ";
        if(opponent==PLAYER)
            hand+=playerHand;
        if(opponent==CPU1)
            hand+=cpu1Hand;
        if(opponent==CPU2)
            hand+=cpu2Hand;
        if(opponent==CPU3)
            hand+=cpu3Hand;

        boolean asked = false;
        for (int i = 0; i < hand.length()-2; i+=3) {
            String repeat = hand.charAt(i) + "";
            String scan = (hand.substring(hand.indexOf(repeat) + 3)).trim();
            if(scan.contains(repeat)) {
                asked = true;
                String temp = "";
                if (scan.substring(0, scan.indexOf(repeat)).trim().length()>0)
                    temp = scan.substring(0, scan.indexOf(repeat)).trim() + " ";
                hand = (hand.substring(0, i).trim() + " " + temp + scan.substring(scan.indexOf(repeat) + 2).trim()).trim();
                break;
            }
        }
        if(!asked)
            hand = hand.substring(3);
        
        String getName = ""; 
        String oppName = "";

        if(asker == PLAYER) 
            getName = "You";
        if(asker == CPU1) 
            getName = "CPU 1";
        if(asker == CPU2) 
            getName = "CPU 2";
        if(asker == CPU3) 
            getName = "CPU 3";

        if(opponent == PLAYER){
            playerHand = hand;
            oppName = "You";
        }if(opponent == CPU1) {
           cpu1Hand = hand;
           oppName = "CPU 1";
        }if(opponent == CPU2){
            cpu2Hand = hand;
            oppName = "CPU 2";
        }if(opponent == CPU3){
            cpu3Hand = hand;
            oppName = "CPU 3";
        }
        System.out.println("");
        Thread.sleep(1000);System.out.println(getName+" asked "+oppName+" if they have any "+card+"'s...");
        System.out.println("");

        if(asked){
            Thread.sleep(1000);System.out.println("A match was found! Scoring the pair that you received...");
            System.out.println("");

            if(asker==PLAYER)
                depositPairs(playerHand+" "+card+"C", PLAYER);
            if(asker==CPU1)
                depositPairs(cpu1Hand+" "+card+"C", CPU1);
            if(asker==CPU2)
                depositPairs(cpu2Hand+" "+card+"C", CPU2);
            if(asker==CPU3)
                depositPairs(cpu3Hand+" "+card+"C", CPU3);

            Thread.sleep(1000);displayHand(PLAYER);
            Thread.sleep(500);displayHand(CPU1);
            Thread.sleep(500);displayHand(CPU2);
            Thread.sleep(500);displayHand(CPU3);
            System.out.println("");
            Thread.sleep(500);displayScore(PLAYER);
            Thread.sleep(500);displayScore(CPU1);
            Thread.sleep(500);displayScore(CPU2);
            Thread.sleep(500);displayScore(CPU3);
            System.out.println("");

        }else{
            Thread.sleep(1000);System.out.println("Go Fish! Drawing a card from the pile...");
            System.out.println("");

            if(asker==PLAYER){
                playerHand += " "+getCard();
                depositPairs(playerHand, PLAYER);
            }if(asker==CPU1){
                cpu1Hand += " "+getCard();
                depositPairs(cpu1Hand, CPU1);
            }if(asker==CPU2){
                cpu2Hand += " "+getCard();
                depositPairs(cpu2Hand, CPU2);
            }if(asker==CPU3){
                cpu3Hand += " "+getCard();
                depositPairs(cpu3Hand, CPU3);
            }

            Thread.sleep(1000);displayHand(PLAYER);
            Thread.sleep(500);displayHand(CPU1);
            Thread.sleep(500);displayHand(CPU2);
            Thread.sleep(500);displayHand(CPU3);
            System.out.println("");
            Thread.sleep(500);displayScore(PLAYER);
            Thread.sleep(500);displayScore(CPU1);
            Thread.sleep(500);displayScore(CPU2);
            Thread.sleep(500);displayScore(CPU3);
            System.out.println("");

        }
    }

    private static String cpuAskForCards(int player) { //returns a random card for cpu to request from another player
        String hand = "";
        if(player==CPU1)
            hand = cpu1Hand;
        if(player==CPU2)
            hand = cpu2Hand;
        if(player==CPU3)
            hand = cpu3Hand;
        while(true){
            int random = (int) (Math.random()*hand.length());
            if(random%3==0)
                return hand.charAt(random)+"";
        }
    }

    private static String playerAskForCards() { //asks you which card you wish to request
        while(true){
            System.out.println("\nPick a card to request: ");
            String temp = "";
            for(int i=0; i<playerHand.length()-1; i+=3){
                if(i!=0)
                    temp += ", ";
                temp +=playerHand.charAt(i) + " (" + (i/3 + 1) + ")";
            }
            System.out.print(temp+": ");

            try {
                int card = Integer.parseInt(in.nextLine());
                if(card<1||card>playerHand.length()/3+1)
                    System.out.println("\nPlease enter a valid option.");
                else
                    return playerHand.charAt((card-1)*3)+"";
            }catch(NumberFormatException e){
                System.out.println("\nPlease enter a valid option.");
            }
        }
    }

    private static int selectPlayer(int move) { //asks you a question after selecting your action at the start of your turn
        String prompt = "";
        if(move==1)
            prompt = "\nWho would you like to ask for a card?";
        if(move==2)
            prompt = "\nWhich player's hand would you like to check?";
        if(move==3)
            prompt = "\nWhich player's score would you like to check?";

        while(true){
            System.out.print(prompt+" You (0), CPU 1 (1), CPU 2 (2), CPU 3 (3): ");
            try{
                int n = Integer.parseInt(in.nextLine());

                if(move==1&&n==0)
                    System.out.println("\nYou may not ask yourself for a card.");
                else if(n>3||n<0)
                    System.out.println("\nPlease enter a valid option.");
                else
                    return n;
            }catch(NumberFormatException e) {
                System.out.println("\nPlease enter a valid option.");
            }
        }
    }

    private static int playerMove() { //asks you which action you would like to perform at the start of your turn
        while(true){
            System.out.print("Ask for a card (1), Check opponent hand (2), Check scores (3): ");

            try{
                int n = Integer.parseInt(in.nextLine());

                if(n>3||n<1){
                    System.out.println("\nPlease enter a valid option.");
                    System.out.println("");
                }else
                    return n;
            }catch(NumberFormatException e){
                System.out.println("\nPlease enter a valid option.");
                System.out.println("");
            }
        }
    }

    private static void displayScore(int player) { //displays the score of the selected player
        if(player==PLAYER)
            System.out.println(" Your score: "+playerScore);
        else if(player==CPU1)
            System.out.println("CPU 1 score: "+cpu1Score);
        else if(player==CPU2)
            System.out.println("CPU 2 score: "+cpu2Score);
        else
            System.out.println("CPU 3 score: "+cpu3Score);
    }

    private static void depositPairs(String hand, int x) { //if a hand has two cards with the same value, the two cards are removen from the hand and score goes up by 1
        int count = 0;
        for (int i = 0; i < hand.length()-2; i+=3) {
            String repeat = hand.charAt(i) + "";
            String scan = (hand.substring(hand.indexOf(repeat) + 3)).trim();
            if(scan.contains(repeat)) {
                count++;
                String temp = "";
                if (scan.substring(0, scan.indexOf(repeat)).trim().length()>0)
                    temp = scan.substring(0, scan.indexOf(repeat)).trim() + " ";
                hand = (hand.substring(0, i).trim() + " " + temp + scan.substring(scan.indexOf(repeat) + 2).trim()).trim();
                i = -3;
            }
        }
        if(x == PLAYER) {
            playerScore += count;
            playerHand = hand;
        }
        else if(x == CPU1) {
            cpu1Score += count;
            cpu1Hand = hand;
        }
        else if(x == CPU2) {
            cpu2Score += count;
            cpu2Hand = hand;
        }
        else if(x == CPU3) {
            cpu3Score += count;
            cpu3Hand = hand;
        }
    }

    private static void displayHand(int x) { //displays the hand of the selected player
        if(x==PLAYER)
            System.out.println(" Your hand: "+playerHand);
        if(x==CPU1)
            System.out.println("CPU 1 hand: "+hideCards(cpu1Hand));
        if(x==CPU2)
            System.out.println("CPU 2 hand: "+hideCards(cpu2Hand));
        if(x==CPU3)
            System.out.println("CPU 3 hand: "+hideCards(cpu3Hand));
    }

    private static String hideCards(String hand){ //replaces the cards in a hand with "X"
        String temp = "";
        for(int i=0; i<hand.length(); i++){
            if((i+1)%3==0&&i!=0)
                temp += " ";
            else
                temp += "X";
        }
        return temp;
    }

    private static String getCard() { //returns a card, which is a value followed by a suit
        return getValue() + getSuit();
    }

    private static String getSuit() { //returns a suit for getCard method
        int iSuit = (int) (Math.random() *NUM_SUITS)+1;

        if(iSuit==1)
            return HEARTS;
        else if(iSuit==2)
            return SPADES;
        else if(iSuit==3)
            return DIAMONDS;
        else if(iSuit==4)
            return CLUBS;
        return "";
    }

    private static String getValue() { //returns a value for getCard method
        int iValue = (int) (Math.random()*NUM_VALUES) + 1;

        if(iValue==1)
            return ACE;
        else if(iValue==10)
            return TEN;
        else if(iValue==11)
            return JACK;
        else if(iValue==12)
            return QUEEN;
        else if(iValue==13)
            return KING;
        else
            return "" + iValue;
    }
}
