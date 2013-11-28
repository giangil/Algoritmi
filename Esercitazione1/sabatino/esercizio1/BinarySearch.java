package sabatino.esercizio1;

public class BinarySearch {
	
	/**
	 * cerca un elemento in un array ordinato, con restituzione della posizione 
	 * (Pre-condizione: array a deve essere ordinato)
	 * @param x intero da cercare
	 * @param a array di interi, ordinato, in cui eseguire la ricerca
	 * @param inf limite inferiore della porzione di array in cui eseguire la ricerca
	 * @param sup limite superiore della porzione di array in cui eseguire la ricerca
	 * //ciao! inf e sup non sono parametri del metodo find! (volevo fare una prova)
	 * @return posizione dell'elemento cercato, se non presente viene restituito l'inverso della posizione in cui potrebbe essere inserito
	 * */
	public static int find(int x,int[]a){
		int inf = 0;
		int sup = a.length-1;
		while(inf<=sup){
			int i = (inf+sup)/2;
			if(x<a[i])sup=i-1;
			else if(x>a[i])inf=i+1;
			else return i;
		}
		return -1;	
	}
		
	public static boolean isPresent(int x, int[]a){
		int inf = 0;
		int sup = a.length-1;
		while(inf<=sup){
			int i = (inf+sup)/2;
			if(x<a[i])sup=i-1;
			else if(x>a[i])inf=i+1;
			else return true;
		}
		return false;
	}
		
	public static int findRecursively(int x, int[]a){
		return findRecursively(x,a,0,a.length-1);
	}
	static int findRecursively(int x, int[]a, int inf, int sup){
		if(inf<=sup){
			int i = (inf+sup)/2;
			if(x<a[i])return findRecursively(x,a,inf,i-1);
			else if(x>a[i])return  findRecursively(x,a,i+1,sup);
			else return i;
		}
		return -1;
	}
}
