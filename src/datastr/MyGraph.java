package datastr;

import datastr.nodes.MyEdgeNode;
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
	public void addEdge(Ttype verticeFrom, Ttype verticeTo, float weight) throws Exception
	{
		if(verticeFrom == null || verticeTo == null || weight<=0 || weight >= 41000)
			throw new Exception("There is a problem with params");
		
		if(verticeFrom.equals(verticeTo))
			throw new Exception("It is not possible to create an edge to the same vertice");
		
		int indexFrom = searchVerticeByElement(verticeFrom);
		int indexTo = searchVerticeByElement(verticeTo);
		
		if(indexFrom == -1 || indexTo == -1)
			throw new Exception("One or both vertices are not in the graph");
		
		//TODO pārbaude, vai tads ceļs starp minetjaām virsotnēm jau neekesistē
		MyEdgeNode newEdgeNode = new MyEdgeNode(indexTo, weight);
		
		//sis būs pirmais ceļa bloks sai virsotnei
		if(vertices[indexFrom].getFirstEdgeNode() == null)
		{
			vertices[indexFrom].setFirstEdgeNode(newEdgeNode);
		}
		else
		{
			MyEdgeNode firstEdgeNode = vertices[indexFrom].getFirstEdgeNode();
			newEdgeNode.setNext(firstEdgeNode);
			firstEdgeNode.setPrevious(newEdgeNode);
			vertices[indexFrom].setFirstEdgeNode(newEdgeNode);
			
		}
	}
	//TODO
	//3. izprintet visas virsotnes ar visiem piesiastītajiem ceļiem
		//funkcijas deklarācija
		//pārbaude, vai grafs ir tukšs
		//iet caurti virsotņu mezglu mašīvām un katru izprintet kopā ar visiem ceļa blokiem
		
	public void print() throws Exception
	{
		if(isEmpty()) throw new Exception("Graph is empty");
		
		for(int i = 0; i < counter; i++) {
			System.out.print(vertices[i].getElement() + " -> ");
			MyEdgeNode tempEdge = vertices[i].getFirstEdgeNode();
			while(tempEdge!=null)
			{
				System.out.print(vertices[tempEdge.getIndexOfNeighbour()].getElement() 
						+ " " + tempEdge.getWeight()  + " km; ");
				tempEdge = tempEdge.getNext();
			}
			System.out.println();
		}
	}
	
	//4. Mainservisā izveidot karti, ar vismas 4 pilsetam un 6 ceļiem
	//pārabude, vai celš jau neeisistē
		//rediget ceļa garumu
		//rediget virsotnes nosaukumu 

}
