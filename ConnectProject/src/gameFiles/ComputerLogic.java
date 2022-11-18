package gameFiles;

public class ComputerLogic extends VictoryCheck{
	public static int Computerchoice(int botLevel, int[][] tab, int userChoice, String[] playerNames){
	    int choice =0;
		if(botLevel==1){
	        choice = randomNum(0,tab[0].length-1);
	        
	    }
	    if(botLevel==2){
	        int decider = randomNum(0,2);
	        //We take a random int to decide if the computer will play to our left or to our right
	        if(decider==0){
	            if(userChoice==tab[0].length-1){
	                choice=tab.length-1;
	                //if the player picks the rightmost lane, computer cannot play to their right
	            }else{
	                choice = userChoice+1;
	                //we are safe to go to the right in all other cases
	            }
	            
	        } else{ //Alternative direction
	            if(userChoice!=0){
	            	choice = userChoice-1;
	            	//We can't go left to the leftmost lane
	            }
	        }
	    }
	    if(botLevel==3){
	        choice = level3Chooser(tab, playerNames, choice);
	        
	    }
	    if(botLevel==4){
	        
	        
	    } 
	    if(botLevel==5){
	        
	        
	    } 
	    if(botLevel==6){
	        
	        
	    }
	    return choice;
	}
	
	public static int level3Chooser(int[][] tab, String[] playerNames, int choice) {
		for(int i=0;i<tab[0].length;i++) {
			if(isFull(tab,i)==false) {
				dripDown(tab, i, 2);
	        	if(checkConnect4(tab, playerNames)==true) {
	        		choice = i;
	        		tableReset(tab, i);
	        		return choice;
	        		//If CPU can make a connect 4 he will do it 
	        	}
			}
			
        	
        	outer:for(int j=0;j<tab[0].length;j++) {
        		if(isFull(tab,j)==false) {
        			dripDown(tab, j, 1);
            		if(checkConnect4(tab, playerNames)==true) {
            			tableReset(tab,j);
            			break outer;
            			//if Player 1 can make a connect 4 after his move, CPU will change move
            		}
            		level3Chooser(tab, playerNames, choice);
            		//Function is recursive, we go deeper and deeper. Sends error as it goes on infinitely 
            		tableReset(tab,j);
            	}
        	}
        	tableReset(tab,i);
        }
		return choice;
	}
}
