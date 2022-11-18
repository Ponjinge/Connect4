package gameFiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameTools {
	
	public static int randomNum(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}//Random integer between the max and the min, note : the max will never be chosen
	
	public static int[][] dripDown( int[][] tab, int column , int turn) {
	    int i=0;
	    tab[i][column]=turn;
	    	while(i<tab.length-1 && tab[i+1][column]==0){//we continue while there is an empty space below
	    		tab[i+1][column]=turn; //space below is given the piece value
	    		tab[i][column]=0;//current space is now empty again
	    		i++;
	    	}//This tool puts pieces down the lanes, they fall until finding another piece right below them
	        
	    
	    
	    return tab;
	}
	
	public static int userInput(int[][] tab) {
		//userInput is used to receive play info from  players
		int input=0;
		@SuppressWarnings("resource")//adding a "try" ruins our while loop , ignore the warnings
		Scanner scan = new Scanner(System.in);//Create a new scanner for input
		boolean validInput = false;
		while(validInput == false) {//keep going until a valid integer is entered 
			if(scan.hasNextInt()) {
				input = scan.nextInt();
				if((input>=0 && input <=tab.length && isFull(tab, input)== false)||input==9) {
					//9 is also a valid input to pause the game outside of the normal column choices
					validInput = true;
				}
			}
		}
		return input;
		
	}
	
	public static int userInputBinary() { //Same function as the previous userInput but this time it only accepts 0 or 1 (usefull for game startup)
		int input=0; 
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in); 
		boolean validInput = false;
		while(validInput == false) {
			if(scan.hasNextInt()) {
				input = scan.nextInt();
				if(input==0 || input ==1 ) {
					
					validInput = true;
				}
			}
		}
		return input;
		
	}
	
	public static int[][] tableReset(int[][] tab , int column){ //Used to undo a move 
	    int pointer=0;
		while(pointer<tab.length && tab[pointer][column]==0){
			pointer=pointer+1;
	    }
		if(pointer<tab.length) { 
			tab[pointer][column]=0;
		}
		// Some extra padding was added to  enable use on empty lanes, useful for troubleshooting
	    
	    return tab;
	}


	public static boolean isFull(int[][]tab, int column){//Check if a lane/column is full 
	    if(tab[0][column]>0){
	    	System.out.println("You can't go there. It's full!!!");//Warn the user so they understand why the game is still waiting for an input
	    	return true;
	        
	    }else{
	        return false;
	    }
	}
	
	public static boolean isFullDrawChecker(int[][]tab, int column){//Same class as previously but without the message, used internally for checking for a draw
	    if(tab[0][column]>0){
	    	
	    	return true;
	        
	    }else{
	        return false;
	    }
	}
	
	public static void tabDisplay(int[][]tab) {
		//This displays our board
		System.out.println("| 0 | 1 | 2 | 3 | 4 | 5 | 6 |");
		for(int i=0;i<tab.length;i++) {
			System.out.print("| ");
			for(int j=0; j<tab[0].length;j++) {
				if(tab[i][j]==1) {
					System.out.print("X | ");//Player 1's pieces are X
				}
				if(tab[i][j]==2) {
					System.out.print("O | ");//Player 2's are O 
				}
				if(tab[i][j]==0) {
					System.out.print("  | ");//Empty spaces
				}
				if(tab[i][j]==3) {
					System.out.print("! | ");//Highlighted pieces when there is a connect 4 
				}
			}
			System.out.println("");
			System.out.print("-----------------------------");//Aesthetic grid lines
			System.out.println("");
		}
	}
	
	public static void saveFile(int[][] tab, int gameChoice, int npcLevel, int turn, int prevMove, String[] playerNames, String song) { 
	    File save = new File("saveFile.txt"); //new file type to save our data in 
	    if (save.delete()) { //if a save already exists, we delete it so we can replace it
	      System.out.println("Quick save (quicksaves can only be loaded once and will be replaced)" );
	    } else {
	      System.out.println("No previous save detected, quick saving now (quicksaves can only be loaded once and will be replaced)");
	    }//Notifying the user of save state
	    
	    try {
	        save = new File("saveFile.txt");//we create the save file
	        if (save.createNewFile()) {
	          System.out.println("Quick save created");//Notify user of save state
	          FileWriter fw = new FileWriter(save.getAbsoluteFile());
	            BufferedWriter bw = new BufferedWriter(fw); // we create a writer to write our save data into the new file

	            // Write in file
	            for(int i=0;i<tab.length;i++) {
	            	for(int j=0;j<tab[0].length;j++) {
	            		bw.write(tab[i][j]+"");
	            		bw.newLine();
	            	}// We write our board into the file
	            	
	            }
	            bw.write(gameChoice+"");
	            bw.newLine();
	            bw.write(npcLevel+"");
	            bw.newLine();
	            bw.write(turn+"");
	            bw.newLine();
	            bw.write(prevMove+"");
	            bw.newLine();
	            bw.write(playerNames[1]+"");
	            bw.newLine();
	            bw.write(playerNames[2]+"");
	            bw.newLine();
	            bw.write(song);
	            //followed by the remaining important variables
	            
	            bw.close();
	           // Close connection with the writer
	        } else {
	        	
	          System.out.println("Error, failed to replace previous quicksave.");
	        }
	      } catch (IOException e) {
	        System.out.println("An error occurred, save has failed.");
	        //Error and exception messages in case an error occurs during the save process 
	      }
	} 
	
	public static void playMusic(String musicLocation, int loopCount) throws LineUnavailableException {
		//Music theme player
		try {
			File musicPath= new File(musicLocation);
			if(musicPath.exists()) {
			
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip enemyTheme = AudioSystem.getClip();
				enemyTheme.open(audioInput);
				enemyTheme.start();
				enemyTheme.loop(loopCount);
				//Creates a new music clip to be played on loop
			}else {
				System.out.println("Can't find file");
			}
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
	
	  
	



