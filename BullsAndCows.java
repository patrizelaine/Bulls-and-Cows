package project;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * Name: Patriz Elaine Daroy
 * Bronco ID: 012590838
 * Project 2
 */

public class BullsAndCows 
{
	public static String guess = "";	//global variable "guess" for user input
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		String repeat = "";	//String to repeat the game
		
		do {
		System.out.println("Welcome to \"Bulls and Cows\"");
		System.out.println("Which option do you want to play? 3, 4, or 5 digits?");
		int codeLength = input.nextInt();	//user inputs if they want 3, 4, 5 digits
		input.nextLine();
		String code = generateRandomCode(codeLength);	//number to be generated is created
		//System.out.println(code); //prints the generated number
		
		do
		{
		System.out.println("Enter guess: ");
		guess = input.nextLine();	//inputs user guess
		checkQuit(guess);	//checks if quit was typed by user
		checkLength(guess, code, codeLength);	//checks parameters of the user input
		
		System.out.println(checkBulls(guess,code,codeLength) + " bulls");		//
		System.out.println(checkCows(guess,code,codeLength) + " cows");
		
		}while(checkBulls(guess, code, codeLength) != codeLength);
		
		//code printed when the program quits
		System.out.println("Good job!");
		System.out.println("Do you want to continue?");
		repeat = input.nextLine();
		
		}while(repeat.equalsIgnoreCase("Yes") || repeat.equals("y"));
		
		System.out.println("Thanks for playing!");
		input.close();
	}
	
	public static void checkQuit(String g)	//method to check if the user types "quit"
	{
		if(g.equalsIgnoreCase("quit"))
		{
			System.out.println("Thanks for playing!");
			System.exit(0);
		}
	}
	
	public static void checkLength(String g, String code, int length)		//method that checks paramters for the input
	{
		Scanner in = new Scanner(System.in);
		do	//do while loop to keep checking parameters of input until the input is valid for the game
		{
		if(g.length() > code.length())	//checks if user inputted to many digits
		{
			System.out.println("Guess must be " + length + " digits.");
			System.out.println("Guess again");
			guess = in.nextLine();
			checkQuit(guess);
		} 
		if(g.length() == code.length()-1)	//adds zero to the string if valid
		{
			guess = addZero(guess);
		}
		if(g.length() <= code.length()-2)	//lets the user know that the input is too short, too many zeroes
		{
			System.out.println("Each digit must be different. Multiple zeroes.");
			System.out.println("Guess again");
			guess = in.nextLine();
			checkQuit(guess);
		}
		
		if(checkRepeat(guess)==-1)	//checks if there are repeated digits in the user input
		{
			System.out.println("Each digit must be different.");
			System.out.println("Guess again");
			guess = in.nextLine();
			checkQuit(guess);
		}
		}while(!(code.length()==guess.length()));//(checkRepeat(guess)==-1) || (guess.length() > code.length()) || (guess.length() < code.length() || (guess.length() == code.length()-2)));
}
	
	public static String addZero(String g)	//adds zero to the string
	{
		guess = "0" + g;
		System.out.println(guess);
		return guess;
	}
	
	public static int checkRepeat(String g)	//method checks for repeated digits in the string
	{
		for(int i=0; i<g.length(); i++)	//loops through the user input string 
		{
			for(int j=i+1; j<g.length(); j++) 
			{
				if(guess.charAt(i) == guess.charAt(j))	//
				{
					return -1;		
				}
			}
		}
		return 0;
	}	
	
	public static int checkBulls(String guess, String code, int length)	//checks how many bulls there are
	{
		int bulls = 0;
		
		for(int i=0; i<length; i++)	//loops to check if values at same indexes are equal
		{
			if(guess.charAt(i) == code.charAt(i))	
			{
				bulls++;		//bulls increment
			}
		}
		return bulls;
	}
	
	public static int checkCows(String guess, String code, int length)	//checks how many cows there are
	{
		int cows = 0;
		for(int i=0; i<code.length(); i++)	//loop through the code to see if values are equal
		{
			for(int j=0; j<code.length(); j++)
			{
				if((code.charAt(i)) == (guess.charAt(j)))
				{
					cows++;	//accounts for all instances that code and and guess have matching values
				}
			}
		}
		cows = cows-checkBulls(guess, code, length);	//subtracts bull instances from total instances to find cows
		return cows;
	}	
		
	public static String generateRandomCode(int codeLength)	//generates random number
	{
	    List<Integer> numbers = new ArrayList<>();
	    for(int i = 0; i < 10; i++){
	        numbers.add(i);
	    }

	    Collections.shuffle(numbers);

	    String code = "";
	    for(int i = 0; i < codeLength; i++){
	        code += numbers.get(i).toString();
	    }
	    //System.out.println(code);  // uncomment this to see the code during your development. 
		   return code;
	}
}