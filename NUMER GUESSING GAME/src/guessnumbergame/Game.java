package guessnumbergame;
import java.util.Random;
import java.util.Scanner;

class game{

    public int number;
    public int inputNumber;
    public int noOfGuesses=0;

     game (){
            Random rand=new Random();
        this.number= rand.nextInt(100);
    }
    void takeuserinput(){
        System.out.println("Guess the number");
        Scanner sc=new Scanner(System.in);
        inputNumber =sc.nextInt();

    }

    boolean iscorrectnumber(){
            noOfGuesses++;
        if (inputNumber == number){
            System.out.format("Yes! you guessed it was %d\nYou guessed it in %d Attempts",number , noOfGuesses );
            return true;
        } else if (inputNumber < number) {
            System.out.println("TOO Low....");
        } else if (inputNumber > number) {
            System.out.println("TOO High....");
        }
            return false;

    }

    public int getNoOfGuesses() {
        return noOfGuesses;
    }

    public void setNoOfGuesses(int noOfGuesses) {
        this.noOfGuesses = noOfGuesses;
    }
}

public class Game {
    public static void main(String[] args) {

        game g = new game();
        boolean b= false;
            while (!b) {
                g.takeuserinput();
                 b = g.iscorrectnumber();
            }

    }
}

