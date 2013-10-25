package esercizio4;

import static java.lang.Math.max;


public class BinaryTree {

  protected class Node {
    protected Integer element;
    protected Node left, right;

    Node(int element) {
      this.element = element;
      left = right = null;
    }

    Node(int element, Node left, Node right) {
      this.element = element;
      this.left = left;
      this.right = right;
    }

    boolean isLeaf() {
      return left == null && right == null;
    }
  }
  
  /** abbozzo di classe, messa solo
   *  per poter realizzare il metodo find,
   *  non usata -- come si potrebbe --
   *  negli altri esercizi
   *  
   * @author elio
   *
   */
  public class NodeReference {
    private Node node;
    
    private NodeReference(Node node) {
      this.node = node;
    }
    
    public int getElement() {
      return node.element;
    }
    
    public void setElement(int e) {
      node.element = e;
    }
  }

  protected Node root;

  public BinaryTree() {
    root = null;
  }

  public void display() {
    display(root, 0);
  }

  protected void display(Node node, int k) {
    if(node != null) {
      display(node.right, k+1);
      for(int i = 0; i < k; i++) System.out.print("   ");
      System.out.println(node.element);
      display(node.left, k+1);
    }
  }

  public boolean isEmpty() {
    return root == null;
  }

  /**
   * inserisce un elemento nell'albero,
   * nella posizione indicata da path;
   * se il path termina su un nodo gia' esistente,
   * sovrascrive il valore dell'elemento;
   * se il path termina su un nodo nullo,
   * oppure se si raggiunge un nodo nullo
   * prima che il path sia finito,
   * l'elemento viene inserito creando un nuovo nodo
   * in quella posizione (vedi slides)
   * @param element
   * @param path
   */
  public void add(int element, String path) {
    root = add(element, path, root);
  }

  protected Node add(int elem, String path, Node nd) {
  	 if(nd==null) return new Node(elem);
     else {
     	if(path.length()==0) nd.element=elem;
     	else{
     		char go = path.charAt(0);
     		String nextPath = path.substring(1);
     		if(go=='L') nd.left= add(elem,nextPath,nd.left);
     		else if(go=='R') nd.right=add(elem,nextPath,nd.right);
     		else throw new IllegalArgumentException("stringa non corretta");
     	}
       return nd;
     }
  }

  /** TODO
   * Scrive gli elementi in preordine sulla consolle
   */
  public void printPreOrder() {
    if(root == null) System.out.println("albero vuoto");
    printPreOrder(root);
    System.out.println();
  }

  protected void printPreOrder(Node node) {
    if(node==null) return;
    printPreOrder(node.left);
  	printPreOrder(node.right);
  	System.out.println(node.element);
    		
  }

  public void printInOrder() {
  
  }

  protected void printInOrder(Node node) {

  }


  public void printPostOrder() {
  
  }

  protected void printPostOrder(Node node) {

  }

  /** altezza dell'albero
   *  (altezza dell'albero vuoto = -1) 
   * @return
   */
  public int height() {
    return height(root);
  }

  protected int height(Node node) {
  	return node==null?-1:1+max(height(node.left),height(node.right));
  }
  
  /** somma dei valori degli elementi
   * (somma di un albero vuoto = 0,
   *  oppure IllegalStateException)
   * @return
   */
  public int sum() {
    return sum(root);
  }

  private int sum(Node nd) {
  	return nd==null ? 0: nd.element+sum(nd.left)+sum(nd.right);
  }

  /** numero dei nodi
   * (numero dei nodi di un albero vuoto = 0)
   * @return
   */
  public int size() {
    return size(root);
  }

  private int size(Node nd) {
  	return nd==null ? 0 :size(nd.left)+size(nd.right)+1;
  }

  /** numero delle foglie */
  public int numberOfLeaves() {
    return numberOfLeaves(root);
  }

  private int numberOfLeaves(Node nd) {
  	if(nd==null) return 0;
    else if (nd.isLeaf()) return 1;
    else return numberOfLeaves(nd.left)+numberOfLeaves(nd.right);
  }

/** da' come risultato true se l'elemento x si trova in this,
    false altrimenti  */
  public boolean search(int x) {
    return search(x,root);
  }

  /** realizzare con una sola istruzione */
  protected boolean search(int x, Node nd) {
  	return (nd==null) ? false : (nd.element==x) ? true :  (search(x,nd.left)|| search(x,nd.right));
  }
//TODO mirrorInPlace
  protected void mirrorInPlace(Node node) {

  }

  public void mirrorInPlace() {
    
  }

  public void increment() {
    increment(root);
  }

  private void increment(Node node) {
  	if(node!=null){
    	node.element++;
    	increment(node.left);
    	increment(node.right);
    }
  }

/* costruisce un nuovo albero privo dei nodi
   di livello >= h
 */
  public BinaryTree trimmed(int h) {
    BinaryTree trimmedTree = new BinaryTree();
    trimmedTree.root=trimmed(h,root);
    return trimmedTree;
  }

  /*  TODO realizzare con una sola istruzione */
  protected Node trimmed(int h, Node nd){
  	if(nd==null || h==0) return null;
    else return new Node(nd.element,trimmed(h-1,nd.left),trimmed(h-1,nd.right));
  }


/**
  elimina dall'albero i nodi di livello >= h:
  realizzazione con la tecnica "naturale":
  il caso di h<=0 sulla radice viene
  trattato come caso particolare nella
  procedura "wrapper"
*/
  public void trim(int h) {
    trim(h, root);
  }
  
 // PRECOND: h >= 1
  protected void trim1(int h, Node nd) {
  	if(nd!=null){
  		if(h==1){
  			nd.left=nd.right=null;
  		}else{
  			trim(h-1,nd.left);
  			trim(h-1,nd.right);
  		}
  	}
  }


/** in alternativa:
  realizzazione con la tecnica usata nel libro di testo
*/

  private Node trim(int h, Node nd) {
  	 if(nd==null || h==0)return null;
     else{
     	nd.left=trim(h-1,nd.left);
     	nd.right=trim(h-1,nd.right);
     	return nd;
     }
  }

 /** costruisce e restituisce un nuovo albero speculare di t
  * 
  * @return speculare di t
  */
  public BinaryTree mirror() {
    return null;
  }

  // definire il metodo protected corrispondente,
  // analogamente ai metodi precedenti

  public boolean equalTo(BinaryTree t) {
    return false; // si richiami areEqual
  }

  // si completi il metodo, richiamando areEqual
  public boolean equals(Object ob) {
    if(ob == null) return false;
    if(getClass() != ob.getClass()) return false;
    return false;
  }

  protected boolean areEqual(Node node1, Node node2) {
    return false;
  }

  // crea un nuovo albero binario copia di this
  public BinaryTree copy() {
    return null;
  }
  
  protected Node copy(Node nd) {
    // realizzare con una istruzione
    return null;
  }  

  private Node find(int x, Node nd) {
    if(nd == null || nd.element == x) return nd;
    Node ris = find(x, nd.left);
    if(ris == null) ris = find(x, nd.right);
    return ris;
  }
  
  public NodeReference find(int x) {
    return new NodeReference(find(x, root));
  }

  /** restituisce true se l'albero � completamente bilanciato
   *  false altrimenti (vedi definizione su libro di testo)
   * @return
   */
  public boolean isCompletelyBalanced() {
    return false;
  }

  private int isCB(Node nd) {
    return -1000;
  }

  /** restituisce true se l'albero � 1-bilanciato
   *  false altrimenti (vedi definizione su libro di testo);
   *  metodo da realizzare a novembre/dicembre
   * @return
   */  
  public boolean is1Balanced() {
    return false;
  }  

  private int is1Balanced(Node node) {
    return -1000;
  }

  /** numero dei nodi a livello liv */
  public int numNodesAtLevel(int liv) {
    return numNodesAtLevel(root, liv);
  }

  protected int numNodesAtLevel(Node nd, int lev) {
    return -1000;
  }


  private class BoolNode {
    boolean found; // fatto
    Node node;

    BoolNode(boolean found, Node node) {
      this.found = found;
      this.node = node;
    }
  }

/** Esercizio opzionale:
   elimina il sottoalbero di radice x;
   se l'elemento x e' presente piu' volte,
   elimina uno solo dei sottoalberi di radice x
   (il primo che trova);
   se l'eliminazione e' stata effettuata,
   restituisce true;
   se invece l'elemento x non e' presente,
   restituisce false
 */
  public boolean removeSubtree(int x) {
    BoolNode ris = removeSubtree(x, root);
    root = ris.node;
    return ris.found;
  }

  protected BoolNode removeSubtree(int x, Node nd) {
    return null;
  }

  /* Esercizio 3.13 pag. 99 libro di testo
   * Restituisce il riferimento al nodo (o a uno dei nodi,
   * se ne esistono pi� d'uno) U tale che:
   * il rapporto fra il numero dei nodi del sottoalbero
   * di radice U (quindi incluso il nodo stesso) e l'altezza (+1)
   * dello stesso sia massimo;
   * in questo esercizio all'altezza si somma il valore 1
   * altrimenti per le foglie il rapporto sarebbe 1/0 = infinito,
   * e l'esercizio sarebbe banale: il risultato sarebbe
   * una qualunque foglia.
   * 
   * Per ragioni di debugging,
   * il metodo pu� (anzi, per ora deve), prima di restituire il riferimento al nodo,
   * scrivere sulla console il valore del rapporto per quel nodo.
   * 
   * NOTA BENE: Si richiede che l'algoritmo sia lineare nel numero
   * dei nodi, visitando quindi l'albero una volta sola.
   * 
   * Si puo' definire una classe privata ausiliaria,
   * analogamente a quanto proposto per qualche esercizio precedente
   * @return
   * 
   * Naturalmente per fare "il vero lavoro"
   * occorre definire un metodo ricorsivo protected o private,
   * richiamato dal metodo pubblico
   */  
  public void printCentralNodes() {
    System.out.print("nodi centrali o nodi-cardine: ");
    central(root,0);
    System.out.println();
  }

  private int central(Node nd, int h) {
    return -1000;
  }
  
  
  public int maxElem() {
    if(root == null) throw new IllegalStateException("albero vuoto");
    return maxElem(root);
  }
  
  private static int max3(int x, int y, int z) {
    return max(x, max(y, z));
  }
  
  private int maxElem(Node nd) {
    return -1000;
  }
  
  /* Esercizio 3.13 pag. 99 libro di testo
   * Restituisce il riferimento al nodo (o a uno dei nodi,
   * se ne esistono pi� d'uno) v tale che:
   * il rapporto fra il numero dei nodi del sottoalbero
   * di radice v (quindi incluso il nodo stesso) e l'altezza + 1
   * dello stesso sia massimo;
   * in questo esercizio si considera l'altezza incrementata di 1,
   * altrimenti per le foglie il rapporto sarebbe 1/0 = infinito,
   * e l'esercizio sarebbe banale: il risultato sarebbe
   * una qualunque foglia.
   * E' come se l'altezza fosse definita 0 per l'albero vuoto,
   * 1 per le foglie, ecc.
   *
   * Per ragioni di debugging,
   * il metodo pu� (e anzi, per ora deve), prima di restituire il riferimento al nodo,
   * scrivere sulla console il valore del rapporto per quel nodo.
   *
   * NOTA BENE: Si richiede che l'algoritmo sia lineare nel numero
   * dei nodi, visitando quindi l'albero una volta sola.
   *
   * Si puo' definire una classe privata ausiliaria,
   * analogamente a quanto proposto per qualche esercizio precedente
   *
   *
   * Naturalmente per fare "il vero lavoro"
   * occorre definire un metodo ricorsivo protected o private,
   * richiamato dal metodo pubblico
   */
  public NodeReference maxDescendantsHeightRatio() {
    return null;
  } 
  
  /**
   * Esercizio 3.14 libro di testo
   * scrive sulla consolle i valori dei nodi v che soddisfano
   * alla seguente condizione:
   * la somma dei valori degli antenati di v (v incluso)
   * � uguale alla somma dei valori dei discendenti di v (v incluso).
   * Nota: si definisca un opportuno metodo protetto o privato ...
   */
  
  public void printEquiNodes() {
    System.out.print("nodi equi: ");
    // ...
    System.out.println();    
  }  
}

