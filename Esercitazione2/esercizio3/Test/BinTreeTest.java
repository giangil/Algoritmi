package esercizio3.Test;

import java.io.IOException;

import esercizio3.*;

public class BinTreeTest {
	public static void main(String[] args) throws IOException{
		BinTree bt = new BinTree(0);
		String pathName = "C:\\Users\\Mauro\\workspace\\Algoritmi\\Algoritmi\\Esercitazione2\\esercizio3\\";
    String fileName = "input.txt";
		bt=BinTree.buildFromFile(pathName+fileName);
		BinTree.draw(bt, null);
		//BinTreeUtil.display(bt);
		//BinTreeUtil.postorderPrint(bt);
		//BinTreeUtil.mirrorInPlace(bt);
		System.out.println(BinTreeUtil.maxUnbalance(bt));
		
		//BinTree.draw(BinTreeUtil.find(30, bt), "copia");
		
	}
}
