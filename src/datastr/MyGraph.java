package datastr;

import datastr.nodes.MyVerticeNode;

public class MyGraph<Ttype> {
	private MyVerticeNode[] vertices;
	private final int GRAPH_DEFAULT_SIZE = 10;
	private int size = GRAPH_DEFAULT_SIZE;
	private int counter = 0;
	
	public MyGraph() {
		vertices = new MyVerticeNode[size];
	}
	
	public MyGraph(int inputSize) {
		if(inputSize > 0) {
			size = inputSize;
		}
		vertices = new MyVerticeNode[size];
	}
	
	public boolean isEmpty() {
		return (counter==0);
	}
	
	public boolean isFull() {
		return (counter == size);
	}
	
	public int howManyElements() {
		return counter;
	}
	
	private void resize()
	{
		int newSize = (counter <= 100)? size * 2 : (int)(size * 1.5);
		MyVerticeNode[] verticesNew = new MyVerticeNode[newSize];
		for(int i = 0; i < size; i++) {
			vertices[i] = verticesNew[i];
		}
		
		vertices = verticesNew;
		System.gc();
		size = newSize;
	}
	
	
	//TODO
	//1. pievienot jaunu virsotni grafā
	public void addVertice(Ttype element) throws Exception{
		if(element == null) throw new Exception("There is a problem with param");
		if(isFull()) resize();
		
		//pārbaude, ka tāda virsotne eksistē
		if(searchVerticeByElement(element) != -1)
			throw new Exception("This vertice is already in the graph");
		
		MyVerticeNode newNode = new MyVerticeNode<Ttype>(element);
		vertices[counter++] = newNode;		
	}


	//1.5. atrast virsotni grafā un atgriezt ta indeksu
	private int searchVerticeByElement(Ttype element) {
		
		for(int i = 0; i < counter; i++) {
			if(vertices[i].getElement().equals(element))
			{
				return i;
			}
		}
		return -1;//tada virsotne neeksistē grafā
	}
	
	
	
	
	//2. pievienot ceļu no konkrētas virsotnes un kādu citu virsotni
	//funkcijas deklaracija
	//pārbaudam ienākošos parametrus
	//noskaidrot, via abas virstones eksistē grafā
	//ja eksistē, tad izveido jaunu ceļa mezglu
	//vai tas ir pirmais ceļa bloks
	//vai tas ir kārtejais
	
	//ja neeksistē, izmest izņēmumu
	
	
	
	
	
	//3. izprintet visas virsotnes ar visiem piesiastītajiem ceļiem
	//4. Mainservisā izveidot karti, ar vismas 4 pilsetam un 6 ceļiem
	

}
