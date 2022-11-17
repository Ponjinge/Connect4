package gameFiles;
import java.util.Scanner;


public class GameLogic extends VictoryCheck{
	public static void main(String[]args) throws InterruptedException {
		
		int newOrLoad = 0;
		int gameChoice=0;
		int npcLevel=0;
		int turn=0;
		int[][] tab = new int[6][7];
		int prevMove=0;
		String[] playerName= new String[3];
		playerName[1] = "P1";
		playerName[2] = "P2";
		System.out.println("NEW GAME(0)  OR  LOAD GAME(1)");
		newOrLoad=userInput(tab);
		if(newOrLoad==1) {
			fileReader(tab, gameChoice, npcLevel, turn, prevMove, playerName);
		}else {
			
			System.out.println("Local Multiplayer (0) OR Single Player (1)");
			gameChoice=userInput(tab);
			//get Player name
			if(gameChoice==1) {
				System.out.println("Choose your opponent:");
				System.out.println("Standard CPU : Beginner(1), Intermediate(2), Hard(3).");
				System.out.println("SECRET BOSSES: Quickman(4), Airman(5), Crashman(6)");
				npcLevel=userInput(tab);
				if(npcLevel==4) {
					playerName[2] = "Quickman";
				}
				if(npcLevel==5) {
					playerName[2] = "Airman";
				}
				if(npcLevel==6) {
					playerName[2] = "Crashman";
				}else {
					playerName[2] = "CPU";
				}
				
			}
				
			//Make turn random later, for now we assume the player 1 starts every time;
			turn=1;
		}
		saveFile(tab, gameChoice, npcLevel, turn, prevMove, playerName);
		
		//Game
		
		outer: while(checkConnect4(tab, playerName)==false) {
			tabDisplay(tab);
			System.out.println(playerName[turn]+"'s turn!");
			System.out.println("Choose a lane "+playerName[turn]+"!!!");
			//Look for input
			if(turn==2) {
				if(gameChoice==1){
					//dripDown(tab, Computerchoice(npcLevel, tab, prevMove), turn);
					
			
				}else {
					int playerInput =userInput(tab);
					if(playerInput==9){
						saveFile(tab, gameChoice, npcLevel, turn, prevMove, playerName);
						System.out.println("GAME SAVED! SEE YOU LATER");
						break outer ;
					}else {
						dripDown(tab,prevMove, turn);
					}
				}
				turn=1;
			}else {
				int playerInput = userInput(tab);
				if(playerInput==9){
					saveFile(tab, gameChoice, npcLevel, turn, prevMove, playerName);
					System.out.println("GAME SAVED! SEE YOU LATER");
					break outer ;
				}else {
					dripDown(tab,prevMove, turn);
				}
				turn=2;
				
			}
			checkConnect4(tab, playerName);
		}
			
		
	}
}
