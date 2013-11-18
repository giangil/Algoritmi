package Esercizio7;

import static java.lang.System.nanoTime;
import java.io.*;


public class FastSortingTest {
	private static PrintWriter out;
	

  static final int N = 2000001, STEP = 500000;
  static int[] array;
	 private static enum Algorithm{
		 MergeSortWithSingleAuxiliaryArray,
		 MergeSortNoMergeOnSorted,
		 MergeSortJavaAPIVersion, 	
		 QuickSortMidToExamine, 	
		 QuickSortHoare,
		 //QuickSortHoareWithInsSort,
		 //QuickSortHoareTailRecursionElimination,	
		 QuickSortCrescenzi
	 }
	public static void main(String[] args) throws IOException {
		String test = "tempiFast.csv";
	  FileWriter outFile = new FileWriter(test, false);
	  out = new PrintWriter(outFile);
	  out.println("arraySize;mergesort;mergesort ecologico;mergesort passo alternato;///");
	  System.out.println("arraySize | mergesort | mergesort ecologico | mergesort alternato | ////");
	  for(int n = STEP; n < N; n += STEP) {
	  	array = new int[n];
	  	RandomArrays.fillRandomNatArray(array, n, 9*n/10);
	  	out.print(n+";");
	  	System.out.println(n+";");
	  	for(Algorithm algo : Algorithm.values()){
	  	out.print(time(algo,array)+";");
	  	System.out.println(algo.name()+" tempo:"+time(algo,array)+"� ordinato: "+ordinato(array,n));
	  	}
	  	out.println();
	  	System.out.println();
	  	
	  	
	  }
	  out.close();

	}
	public static double time(Algorithm algo,int[]array){
		long  t0,t1;
		double diff;
		int[] a = array; 
		t0 = nanoTime();
		sort(algo,a);
		t1 = nanoTime();
		diff = (t1-t0)/1000000.0;
		return diff;
	}
	public static void sort(Algorithm algo,int[]array){
		switch(algo) {
		case MergeSortWithSingleAuxiliaryArray:FastSorting.MergeSortWithSingleAuxiliaryArray(array); break;
		case MergeSortNoMergeOnSorted:FastSorting.MergeSortNoMergeOnSorted(array); break;
		case MergeSortJavaAPIVersion:FastSorting.MergeSortJavaAPIVersion(array);break;
		case QuickSortMidToExamine:FastSorting.QuickSortMidToExamine(array); break;
		case QuickSortHoare:FastSorting.QuickSortHoare(array);break;
		case QuickSortCrescenzi:FastSorting.QuickSortCrescenzi(array);break;
		}
	}
	static boolean ordinato(int a[],int n) {
  	for(int i = 1; i < n-1; i++) if(a[i-1] > a[i]) return false;
	return true;
  }

}
