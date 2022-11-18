package gameFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Main running code 
public class GameLogic extends ComputerLogic{
	public static void main(String[]args) throws InterruptedException {
		//Startup variables
		int newOrLoad = 0;
		int gameChoice=0;
		int npcLevel=0;
		//variables used to setup the game to a user's wishes 
		int turn=0;
		//int deciding who plays next
		int[][] tab = new int[6][7];
		//Our multidimensional array that is the backbone of the game
		int prevMove=0;
		//user's previous move
		String[] playerName= new String[3];
		playerName[1] = "P1";
		playerName[2] = "P2";
		// Names for both players
		
		System.out.println("NEW GAME(0)  OR  LOAD GAME(1)");
		newOrLoad=userInputBinary();
		// We ask the User whether he wants to start a new game or load a saved game
		if(newOrLoad==1) {
			try {
			      File save = new File("saveFile.txt");
			      Scanner myReader = new Scanner(save);
			      for(int i=0;i<tab.length;i++) {
		          	for(int j=0;j<tab[0].length;j++) {
		          		String data = myReader.nextLine();
		    	        tab[i][j]=Integer.parseInt(data);
		    	        
		          	}
			      }
			      
			      String data = myReader.nextLine();
			      gameChoice=Integer.parseInt(data);
			      data = myReader.nextLine();
			      npcLevel=Integer.parseInt(data);
			      data = myReader.nextLine();
			      turn=Integer.parseInt(data);
			      data = myReader.nextLine();
			      prevMove=Integer.parseInt(data);
			      data = myReader.nextLine();
			      playerName[1]=data;
			      data = myReader.nextLine();
			      playerName[2]=data;
			        
			        
			      
			      myReader.close();
			    } catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
			
			//We read the save and prepare the game, assigning all the variables to the information given in the save file
		}else {
			//New Game
			System.out.println("Local Multiplayer (0) OR Single Player (1)");
			gameChoice=userInputBinary();
			//User chooses whether they are playing alone or with someone locally 
			if(gameChoice==1) {
				//User is playing alone
				System.out.println("Choose your opponent:");
				System.out.println("Standard CPU : Beginner(1), Intermediate(2), Hard(Proof of concept)(3).");
				System.out.println("SECRET BOSSES: Quickman(4), Airman(5), Crashman(6)");
				npcLevel=userInput(tab);
				//User chooses a CPU difficulty level 
				if(npcLevel==4) {
					playerName[2] = "Quickman";
				}//Can freeze time and play twice before player can react
				if(npcLevel==5) {
					playerName[2] = "Airman";
				}//Can blow the top layer of pieces out their lanes 
				if(npcLevel==6) {
					playerName[2] = "Crashman";
					//Can explode a piece and destroy it before his turn
				}else {
					playerName[2] = "CPU";
				}//Regular player with increasing complexity 
				
			}
				
			
			System.out.println("Who Goes first? P1(0) or P2(1)");
			turn=userInputBinary()+1;
			//User chooses who starts
		}

		
		//Game Code 
		
		outer: while(checkConnect4(tab, playerName)==false) {
			//Main loop that will keep going until victory or a draw is forced
			tabDisplay(tab);
			System.out.println(playerName[turn]+"'s turn!");
			System.out.println("Choose a lane(0-6) "+playerName[turn]+" or Save and Exit (9)!!!");
			//Player is notified of his turn is asked to pick a lane to play on
			
			if(turn==2) {
				//Player 2's turn 
				if(gameChoice==1){
					dripDown(tab, Computerchoice(npcLevel, tab, prevMove, playerName), turn);
					//If player 2 is a computer, we ask the CPU for an input while giving them the necessary info to make a decision
			
				}else {
					int playerInput =userInput(tab);
					if(playerInput==9){
						saveFile(tab, gameChoice, npcLevel, turn, prevMove, playerName);
						System.out.println("GAME SAVED! SEE YOU LATER");
						break outer ;
						//Pressing 9 saves the game in a new file and ends the game loop
					}else {
						dripDown(tab,playerInput, turn);
						//We ask the player for an input 
					}
				}
				turn=1;
				//Now Player 2's turn is over, we swap to P1's turn
			}else {
				//P1's turn
				int playerInput = userInput(tab);
				if(playerInput==9){
					saveFile(tab, gameChoice, npcLevel, turn, prevMove, playerName);
					System.out.println("GAME SAVED! SEE YOU LATER");
					break outer ;
					//Save as seen previously
				}else {
					dripDown(tab,playerInput, turn);
					prevMove=playerInput;
					//This time we also store the player's decision to feed it to the CPU if level 2 is chosen
				}
				turn=2;
				//Switch turns
			}
			checkConnect4(tab, playerName);
			//We check to see if a connect 4 was made this turn
			boolean drawcondition=true;
			for(int i=0; i<tab.length; i++) {
				if(isFullDrawChecker(tab, i)==false) {
					drawcondition=false;
				}
				//Here we check each lane, we keep going until we find a lane that isn't full or until all lanes are found to be full
				
			}
			if(drawcondition==true) {
				System.out.println("DRAW , NO ONE WINS");
				break outer;
				//If the previous loop ends (ie all lanes are full ) then no more moves can be made and the game is declared a draw
			}
			
		}
		tabDisplay(tab);
		//We display the result , with ! representing the winning connect 4 
		
	}
}
