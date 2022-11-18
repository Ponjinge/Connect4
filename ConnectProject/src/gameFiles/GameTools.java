package gameFiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GameTools {
	
	public static int randomNum(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	
	public static int[][] dripDown( int[][] tab, int column , int turn) {
	    int i=0;
	    tab[i][column]=turn;
	    	while(i<tab.length-1 && tab[i+1][column]==0){
	    		tab[i+1][column]=turn;
	    		tab[i][column]=0;
	    		i++;
	    	}
	        
	    
	    
	    return tab;
	}
	
	public static int userInput(int[][] tab) {
		int input=0;
		Scanner scan = new Scanner(System.in);
		boolean validInput = false;
		while(validInput == false) {
			if(scan.hasNextInt()) {
				input = scan.nextInt();
				if((input>=0 && input <=tab.length && isFull(tab, input)== false)||input==9) {
					
					validInput = true;
				}
			}
		}
		return input;
		
	}
	
	public static int userInputBinary() {
		int input=0;
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
	
	public static int[][] tableReset(int[][] tab , int column){
	    int pointer=0;
		while(pointer<tab.length && tab[pointer][column]==0){
			pointer=pointer+1;
	    }
		if(pointer<tab.length) {
			tab[pointer][column]=0;
		}
	    
	    return tab;
	}


	public static boolean isFull(int[][]tab, int column){
	    if(tab[0][column]>0){
	    	System.out.println("You can't go there. It's full!!!");
	    	return true;
	        
	    }else{
	        return false;
	    }
	}
	
	public static boolean isFullDrawChecker(int[][]tab, int column){
	    if(tab[0][column]>0){
	    	
	    	return true;
	        
	    }else{
	        return false;
	    }
	}
	
	public static void tabDisplay(int[][]tab) {
		for(int i=0;i<tab.length;i++) {
			System.out.print("| ");
			for(int j=0; j<tab[0].length;j++) {
				if(tab[i][j]==1) {
					System.out.print("X | ");
				}
				if(tab[i][j]==2) {
					System.out.print("O | ");
				}
				if(tab[i][j]==0) {
					System.out.print("  | ");
				}
				if(tab[i][j]==3) {
					System.out.print("! | ");
				}
			}
			System.out.println("");
			System.out.print("-----------------------------");
			System.out.println("");
		}
	}
	
	public static void saveFile(int[][] tab, int gameChoice, int npcLevel, int turn, int prevMove, String[] playerNames) { 
	    File save = new File("saveFile.txt"); 
	    if (save.delete()) { 
	      System.out.println("Quick save (quicksaves can only be loaded once and will be replaced)" );
	    } else {
	      System.out.println("No previous save detected, quick saving now (quicksaves can only be loaded once and will be replaced)");
	    }
	    
	    try {
	        save = new File("saveFile.txt");
	        if (save.createNewFile()) {
	          System.out.println("Quick save created");
	          FileWriter fw = new FileWriter(save.getAbsoluteFile());
	            BufferedWriter bw = new BufferedWriter(fw);

	            // Write in file
	            for(int i=0;i<tab.length;i++) {
	            	for(int j=0;j<tab[0].length;j++) {
	            		bw.write(tab[i][j]+"");
	            		bw.newLine();
	            	}
	            	
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

	            // Close connection
	            bw.close();
	        } else {
	        	
	          System.out.println("Error, failed to replace previous quicksave.");
	        }
	      } catch (IOException e) {
	        System.out.println("An error occurred, save has failed.");
	        
	      }
	  } 
	
	
	  
	
}


