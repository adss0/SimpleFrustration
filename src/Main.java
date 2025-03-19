import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Welcome to the Simple Frustration");
//        System.out.println("How many dices do you want to play with?Enter 1 or 2?");
//        int numberOfDice = scanner.nextInt();
//        while(numberOfDice > 2){
//            System.out.println("Please enter a number between 1 or 2");
//            numberOfDice = scanner.nextInt();
//        }
//        System.out.println("Select number of players 2 or 4");
//        int numberOfPlayers= scanner.nextInt();
//        while( numberOfPlayers != 4  && numberOfPlayers !=2 ){
//            System.out.println("Only 2 or 4 players can play.Please enter the correct number.");
//            numberOfPlayers =scanner.nextInt();
//        }
//
//        System.out.println("Select Board size of 18 or 36");
//        int boardSize = scanner.nextInt();
//        while(!(boardSize > 1)  ){
//            System.out.println("Board size can only be 18 or 36. Please enter the correct number.");
//            boardSize = scanner.nextInt();
//        }


       FacadeInterface game = new Facade();

       game.play(2, 2, 18, true, true);
    }

}
