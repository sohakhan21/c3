package gamecode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;


public class project13 {
	// Only 6 mistakes are allowed until the user loses.
		private static final int LOSE_CONDITION = 3;
		
		 
		// * Finally I write code to pull a random word from a dictionary.
		public static void main(String[] args){
			//I will read in the entire file into a comma separated string
			//named text.
			String text = "";
			//Try-catch statements are mandatory around the file reader
			try{
				//Create a new file reader and pass it the file to read in.
				//I put this file in the project folder next to src and bin.
				//This file contains a huge list of English words, one word
				//per line of text.
				FileReader fr = new FileReader("D:\\anmol\\gamecode.txt");
	                    //Read in each line of the file into the line variable.
	                    //If line is ever null, that means we have reached the end
	                    //of the file.
	                    try ( //Create a new buffered reader for reading in the file.
	                            BufferedReader bReader = new BufferedReader(fr)) {
	                        //Read in each line of the file into the line variable.
	                       
	                        String line = bReader.readLine();
	                        while(line != null){
	                           
	                            if(!line.contains("'")){
	                                
	                                text += line+",";
	                            }
	                            line = bReader.readLine();
	                        }
	                    }
			}
			catch(IOException e){
			    //There was a problem!
				System.out.println("ERROR: There was a problem reading the file.\n"+
			    e.getMessage());
				System.exit(1);
			}

			//Split the text on commas into a string array.
			String[] englishWords = text.split(",");
			//Create a random number generator to get a new random string.
			Random rand = new Random();

			//in the array. We go one less because the last word is blank since
			//every word was followed by a comma.
			int randNum = rand.nextInt(englishWords.length-1);
			//This is the word the user will try to guess
			String wordToGuess = englishWords[randNum];
			
			//to create an array with one element for each of the 26 
			//letters in the English alphabet.
			//This will track the letters guessed so far.
			String[] lettersGuessed = new String[6];
			//Current index into letters Guessed
			int lettersIndex = 0;
			
			//Count up the number of mistakes the user has made.
			int mistakes = 0;
			
			//Create a scanner to read in user input
			Scanner in = new Scanner(System.in);
			
			//to track whether or not the user has guessed the word.
			boolean youWon = false;

			//to create a temporary variable to store the user's guess.
			CharSequence guess = "";

			/* This is the main loop where the user will make guesses
			 * and we will update the hangman display.
			 */
			while(mistakes < LOSE_CONDITION && !youWon){
				//Print the hangman so far:
				//Display the word to guess
				displayWord(wordToGuess, lettersGuessed);
				//Display the letters guessed so far.
				System.out.println("You have guessed:"+Arrays.toString(lettersGuessed));
				//Wait for the user's input
				guess = in.next();
				//Add the user's guess to the list of letters guessed.
				lettersGuessed[lettersIndex] = guess.toString();
				lettersIndex = lettersIndex + 1;
				//Update the number of mistakes.
				if(!wordToGuess.contains(guess)){
					mistakes = mistakes + 1;
					System.out.println("Wrong. The letter "+guess.toString()+" is not in the word.");
				}
				//Check to see if the user won
				youWon = gameWon(wordToGuess, lettersGuessed);
			}

			//to let the user know how the game turned out.
			if(youWon){
				System.out.println("You win! The word is "+wordToGuess);
			}else{
				System.out.println("You lose! The word is "+wordToGuess);
			}
		}
		
		
		public static void displayWord(String wordToGuess, String[] lettersGuessed){
			System.out.print("Word to Guess: ");
			//Loop over each letter in wordToGuess
			for(int i = 0; i < wordToGuess.length(); i++){
				/* if the letters Guessed contains the character at index i
				 * then print the letter
				 * otherwise print the given underscore.
				 */
				char temp = wordToGuess.charAt(i);
				if(stringArrayContains(lettersGuessed, temp)){
					System.out.print(wordToGuess.charAt(i)+" ");
				}else{
					System.out.print("_ ");
				}
			}
			System.out.println("\n");
		}
		
		
		/* Pre: word To Guess is a string. letters Guessed is a string array.
		 * Post: Returns true if every letter in wordToGuess is in the 
		 * lettersGuessed array.
		 */
		public static boolean gameWon(String wordToGuess, String[] lettersGuessed){
			//have it loop over each letter in word To Guess
			for(int i = 0; i < wordToGuess.length(); i++){
				/* if the letters Guessed doesn,t contain the character at index i
				 * then return false
				 */
				char temp = wordToGuess.charAt(i);
				if(!stringArrayContains(lettersGuessed, temp)){
					return false;
				}
			}
			return true;
		}
		
		
		/* Pre: strArray is a string array,where letter is a character.
		 * Post: returns true if the found letter is an element of the string array.
		 * returns false otherwise.
		 */
		public static boolean stringArrayContains(String[] strArray, char letter){
	            for (String strArray1 : strArray) {
	                //The char has to be converted to a String.
	                if (strArray1 != null && strArray1.equalsIgnoreCase(String.valueOf(letter))) {
	                    return true;
	                }
	            }
			return false;
	        }
	}


