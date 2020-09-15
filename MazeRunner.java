import java.util.*;

public class MazeRunner {
    public static Scanner input = new Scanner(System.in);
    public static String direction;
    public static int userSteps=0;
    public  static String decision;
    public static Maze myMap = new Maze();

    public static void main (String [] args ) {
        intro();
        while (myMap.didIWin()==false) {
            userMove();
            if (direction.equalsIgnoreCase("R")||direction.equalsIgnoreCase("L")
                    || direction.equalsIgnoreCase("U")||direction.equalsIgnoreCase("D")) {
            navigatePit();
            System.out.println();
            }
            if (myMap.didIWin()==true) {
            System.out.println("Congratulation! You win the game and you have did in " + userSteps + " moves!");
            System.exit(0);
            }
        }
    }

    public static void intro() {
        System.out.println("Welcome to Maze Runner!");
        System.out.println("Here is your current position: ");
        myMap.printMap();
        for (int i=0;i<20;i++) System.out.print("-");
        System.out.println();
    }

    public static void userMove() {
        do {
            if (userSteps !=101){
                System.out.println("Where would you like?");
                direction = input.next();
            }
            if (direction.equalsIgnoreCase("R")||direction.equalsIgnoreCase("L")
            || direction.equalsIgnoreCase("U")||direction.equalsIgnoreCase("D")){
                movesMessage(++userSteps);
                if (myMap.canIMoveRight()==true && direction.equalsIgnoreCase("R")){
                    myMap.moveRight();
                } else if (myMap.canIMoveLeft()==true && direction.equalsIgnoreCase("L")){
                    myMap.moveLeft();
                } else if (myMap.canIMoveDown()==true && direction.equalsIgnoreCase("D")){
                    myMap.moveDown();
                } else if (myMap.canIMoveUp()==true && direction.equalsIgnoreCase("U")){
                    myMap.moveUp();
                } else {
                    if (userSteps !=101){
                    System.out.println("Sorry you hit the wall! Please try another direction.");
                    System.out.println("Where would you like? ");
                    direction=input.next();
                    movesMessage(userSteps);
                    }
                }
                myMap.printMap();
                break;
            }
        }while (direction.matches("[RLUD]")==false);

    }

    public static void movesMessage(int moves){
        switch (moves){
            case 50:
                System.out.println("Warning: You have made 50 moves, " +
                        "you have 50 remaining before the maze exit closes.");
                break;
            case 75:
                System.out.println("Alert! you have mad 75 moves, you only have 25 moves left to escape.");
                break;
            case 90:
                System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
                break;
            case 100:
                System.out.println("Oh no! You took long to escape, and now the maze exit is closed FOREVER>:[");
                break;
            case 101 :
                System.out.println("Sorry, but you didn't escape in time - you lose!");
                System.exit(0);
                break;
        }
    }

    public static void navigatePit() {
        direction.toUpperCase();
        if (myMap.isThereAPit(direction) == true) {
            System.out.println("Watch out! There's a pit ahead, jump it?");
            decision = input.next();
            if (decision.equalsIgnoreCase("YES") || decision.equalsIgnoreCase("Y"))
                myMap.jumpOverPit(direction);
            else {
                System.out.println("Sorry you don't jump, you lose! ");
                System.exit(0);
            }
        }
    }
}