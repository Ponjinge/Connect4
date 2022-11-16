package gameFiles;

import java.util.Scanner;

public class GameTools {
	
	public static int[][] dripDown( int[][] tab, int column , int turn) {
	    int i=0;
	    tab[i][column]=turn;
	    	while(tab[i+1][column]==0 && i<tab.length-2){
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
				if(input>=0 && input <=tab.length && isFull(tab, input)== false) {
					
					validInput = true;
				}
			}
		}
		return input;
		
	}
	
	public static int[][] tableReset(int[][] tab , int column){
	    int i=0;
		while(tab[i][column]==0){
			i++;
	    }
	    tab[i][column]=0;
	    return tab;
	}


	public static boolean isFull(int[][]tab, int column){
	    if(tab[0][column]>0){
	        return true;
	    }else{
	        return false;
	    }
	}
	
	public static void tabDisplay(int[][]tab) {
		for(int i=0;i<tab.length;i++) {
			for(int j=0; j<tab[0].length;j++) {
				System.out.print(tab[i][j]+" | ");
				
			}
			System.out.println("");
			System.out.print("---------------------------");
			System.out.println("");
		}
	}
	
}


