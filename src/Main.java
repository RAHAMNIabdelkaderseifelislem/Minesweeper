import java.util.Scanner;
//So busy to create main Menu
public class Main {
	static int intErrorTrap (int x, int y){
		 
        int max, min;
 
        if (x < y) {
            min = x;
            max = y;
        } else {
            min = y;
            max = x;
        }
 
        int input;
        boolean loopEnd;
 
        do {
 
            System.out.println("Please enter an integer between " + min + " to " + max + ".");
            Scanner userInput = new Scanner(System.in); //Player inputs a guess
 
            try 
            {
                input = userInput.nextInt();
 
                if(input > max) //Input is too high
                {
                    loopEnd = false;
                    System.out.println("Input is invalid.");
                    return -1;
                }
 
                else if(input < min) //Input is too low
                {
                    loopEnd = false;
                    System.out.println("Input is invalid.");
                    return -1;
                }
 
                else //Input is within acceptable range
                {
                    loopEnd = true;
                    System.out.println(input + " is a valid input.");
                    return input;
 
                }
            }
 
            catch (Exception e)
            {
                loopEnd = false;
                userInput.next();
                System.out.println("Input is invalid.");
                return 0;
            }
 
        } while (loopEnd == false);
    }
 
 
    public static void main(String[] args) {
 
    	System.out.println ("Enter width.");
    	int x = intErrorTrap (0,60);
 
    	System.out.println ("Enter height.");
    	int y = intErrorTrap(0,30);
 
    	System.out.println ("Enter number of mines.");
    	int d = intErrorTrap (0,100);
 
        new Minesweeper(x, y, d); //Suggested: (60, 30, 15)
    } 
 
}
