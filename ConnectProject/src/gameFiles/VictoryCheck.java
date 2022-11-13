package gameFiles;

public class VictoryCheck {
	public static void main(String[] args) {
		int[][] tab = new int[6][7];

		
		for(int i=0; i<tab.length; i++) {
			for(int j=0;j<tab[0].length;j++) {
				System.out.print(tab[i][j]+" ");
			}
			System.out.println();
		}
		
		
		
		outer: for(int i=0; i<tab.length; i++) {
			for(int j=0;j<tab[0].length;j++) {
				if(tab[i][j]>0) {
					
					if(i<tab.length-3) {
						if(tab[i][j]==tab[i+1][j] && tab[i+1][j]==tab[i+2][j] && tab[i+2][j]==tab[i+3][j]){
							if(tab[i][j]==1) {
								System.out.println("Victory for Red");
							
							}
						
							if(tab[i][j]==2) {
								System.out.println("Victory for Yellow");
							
							}
						
							break outer;
						
				
						}
					}
					if(j<tab[0].length-3) {
						if(tab[i][j]==tab[i][j+1] && tab[i][j+1]==tab[i][j+2] && tab[i][j+2]==tab[i][j+3]) {
							if(tab[i][j]==1) {
								System.out.println("Victory for Red");
							
							}
						
							if(tab[i][j]==2) {
								System.out.println("Victory for Yellow");
							
							}
						
							break outer;
						
				
					
						}
					}
					if(i<tab.length-3 && j<tab[0].length-3) {
						if(tab[i][j]==tab[i+1][j+1] && tab[i+1][j+1]==tab[i+2][j+2] && tab[i+2][j+2]==tab[i+3][j+3]) {
						
							if(tab[i][j]==1) {
								System.out.println("Victory for Red");
							
							}
						
							if(tab[i][j]==2) {
								System.out.println("Victory for Yellow");
							
							}
						
							break outer;
						
				
						}
					}
					if(i<tab.length-3 && j>=3) {
						if(tab[i][j]==tab[i+1][j-1] && tab[i+1][j-1]==tab[i+2][j-2] && tab[i+2][j-2]==tab[i+3][j-3]){
						
							if(tab[i][j]==1) {
								System.out.println("Victory for Red");
							
							}
						
							if(tab[i][j]==2) {
								System.out.println("Victory for Yellow");
							
							}
						
							break outer;
						
						}
					}
				}
			}
		}
	}
	
}
