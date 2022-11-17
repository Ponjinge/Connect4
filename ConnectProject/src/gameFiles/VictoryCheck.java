package gameFiles;

public class VictoryCheck extends GameTools{
	public static boolean checkConnect4 (int[][] tab, String[] playerNames) {
		
		
		
		for(int i=0; i<tab.length; i++) {
			for(int j=0;j<tab[0].length;j++) {
				if(tab[i][j]>0) {
					
					if(i<tab.length-3) {
						if(tab[i][j]==tab[i+1][j] && tab[i+1][j]==tab[i+2][j] && tab[i+2][j]==tab[i+3][j]){
							for(int k=1; k<=2; k++) {
								if(tab[i][j]==k) {
									tab[i][j]=3;
									tab[i+1][j]=3;
									tab[i+2][j]=3;
									tab[i+3][j]=3;
									System.out.println("Victory for "+playerNames[k]);
								}
							}
							return true;
						
				
						}
					}
					if(j<tab[0].length-3) {
						if(tab[i][j]==tab[i][j+1] && tab[i][j+1]==tab[i][j+2] && tab[i][j+2]==tab[i][j+3]) {
							for(int k=1; k<=2; k++) {
								if(tab[i][j]==k) {
									tab[i][j]=3;
									tab[i][j+1]=3;
									tab[i][j+2]=3;
									tab[i][j+3]=3;
									System.out.println("Victory for "+playerNames[k]);
								}
							}
							return true;
						
						}
					}
					if(i<tab.length-3 && j<tab[0].length-3) {
						if(tab[i][j]==tab[i+1][j+1] && tab[i+1][j+1]==tab[i+2][j+2] && tab[i+2][j+2]==tab[i+3][j+3]) {
							for(int k=1; k<=2; k++) {
								if(tab[i][j]==k) {
									tab[i][j]=3;
									tab[i+1][j+1]=3;
									tab[i+2][j+2]=3;
									tab[i+3][j+3]=3;
									System.out.println("Victory for "+playerNames[k]);
								}
							}
							return true;
						
				
						}
					}
					if(i<tab.length-3 && j>=3) {
						if(tab[i][j]==tab[i+1][j-1] && tab[i+1][j-1]==tab[i+2][j-2] && tab[i+2][j-2]==tab[i+3][j-3]){
							for(int k=1; k<=2; k++) {
								if(tab[i][j]==k) {
									tab[i][j]=3;
									tab[i+1][j-1]=3;
									tab[i+2][j-2]=3;
									tab[i+3][j-3]=3;
									System.out.println("Victory for "+playerNames[k]);
								}
							}
							return true;
						
						}
					}
				}
			}
		}
		return false;
	}
	
}
