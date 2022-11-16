package gameFiles;

public class GameTools {
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
}
