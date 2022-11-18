package gameFiles;

public class ComputerLogic extends VictoryCheck{ // We use this class for all the CPU/AI's decisions (note: we have daisy-chained all the classes in the project)
	public static int Computerchoice(int botLevel, int[][] tab, int userChoice, String[] playerNames, int turnCount ){
	    int choice =0;
		if(botLevel==1|| botLevel>=4){ //For the easy difficulty and the special bosses, the CPU will choose a random lane to play on 
	        choice = randomNum(0,tab[0].length-1);
	        if(turnCount%3==0){//Every 3 turns a special Boss will use it's ability
	        	if(botLevel==4) {
	        		dripDown(tab, choice, 2); //Quickman's ability: he plays once here before going to the general play function 
	        		System.out.println("Before you even realise it...Quickman HAS PLAYED TWICE!"); //Alert the user that an ability has been used
	        	}
	        	if(botLevel==5) {
	        		for(int i=0; i<tab[0].length;i++) {
		        		tableReset(tab, i); //Top level of the board is cleared, this is Airman's power
		        	}
	        		System.out.println("Airman sends a tornado onto the board! The top layer is removed!!!");
	        	}
	        	if(botLevel==6) {
	        		boolean replaced=false;
	        		while(replaced==false) {//Repeat until an appropriate position is found
	        			 int xPointer=randomNum(0,tab.length);
	        			 int yPointer=randomNum(0,tab[0].length);
	        			 //A random position on the board is chosen
	        			 
	        			 if(tab[xPointer][yPointer]==1) {
	        				 tab[xPointer][yPointer]=2;
	        				 //if the position is occupied by a piece that belongs to player 1, then it is replaced and the loop ends
	        				 replaced=true;
	        			 }
	        		}
	        		
	        		System.out.println("Crash man shoots! He destroyed one of your pieces and replaced it by his own!");
	        		//Announce to the user once it is done 
	        	}
	        }
	        
	        	
	        	
	        
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
	        //A recursive function that has unresolved pointer and loop issues 
	    }
	    if(botLevel==0){ 
	    // A revised version that limits the depth to 10, but there is an overuse of the Undo function (tableReset) that prevents it from functioning as intended
	    	int [][] tabClone = tab.clone();
	    	int[] res = new int[2];
	    	res[0]=0;
	    	res[1]=0;
	        level3v2Chooser(tabClone, playerNames, res);
	        choice = res[0];
	        
	    } 

	    return choice; //universal return of the choice of the CPU / AI 
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
            			tableReset(tab,j);//reset the board
            			break outer;
            			//if Player 1 can make a connect 4 after his move, CPU will change move, returning back to the previous loop
            		}
            		level3Chooser(tab, playerNames, choice);
            		//Function is recursive, we go deeper and deeper. Sends error as it goes on infinitely 
            		tableReset(tab,j);//once this position has been exhausted, we undo it to try another
            	}
        	}
        	tableReset(tab,i);//once this position has been exhausted, we undo it to try another
        }
		return choice;//Return the final decision 
	}
	
	public static int[] level3v2Chooser(int[][] tabClone, String[] playerNames, int res[]) {
		if(res[1]<10) {
			for(int i=0;i<tabClone[0].length;i++) {
				if(isFull(tabClone,i)==false) {
					dripDown(tabClone, i, 2);
		        	if(checkConnect4(tabClone, playerNames)==true) {
		        		res[0] = i;
		        		tableReset(tabClone, i);
		        		return res;
		        		//If CPU can make a connect 4 he will do it 
		        	}
				}
				
	        	
	        	outer:for(int j=0;j<tabClone[0].length;j++) {
	        		if(isFull(tabClone,j)==false) {
	        			dripDown(tabClone, j, 1);
	            		if(checkConnect4(tabClone, playerNames)==true) {
	            			tableReset(tabClone,j);
	            			break outer;
	            			//if Player 1 can make a connect 4 after his move, CPU will change move
	            		}
	            		res[1]=res[1]+1;
	            		level3v2Chooser(tabClone, playerNames, res);
	            		//Function is recursive, we go deeper and deeper. Sends error as it goes on until a depth of 10 
	            		tableReset(tabClone,j); 
	            	}
	        	}
	        	tableReset(tabClone,i);
	        }
		}
		return res;
	}
}
