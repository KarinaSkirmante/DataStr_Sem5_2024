package datastr;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

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
	

	private ArrayList<MyVerticeNode> getNeighbours(Ttype element) throws Exception{
		//TODO
		if(element == null) throw new Exception("There is a problem with element");
		int indexOfElement = searchVerticeByElement(element);
		if(indexOfElement == -1) throw new Exception("Vertice is not in the graph");
		
		ArrayList<MyVerticeNode> result = new ArrayList<MyVerticeNode>();

		MyVerticeNode verticeNode = vertices[indexOfElement];
		
		MyEdgeNode tempE = verticeNode.getFirstEdgeNode();
		
		while(tempE!=null)
		{
			int indexOfNeighbour = tempE.getIndexOfNeighbour();
			MyVerticeNode verticeNodeOfNeighbour = vertices[indexOfNeighbour];
			result.add(verticeNodeOfNeighbour);
			
			//īsā forma
			//result.add(vertices[tempE.getIndexOfNeighbour()]);
			tempE = tempE.getNext();
		}
		return result;
		
	}
	
	private void clearVisited() throws Exception
	{
		if(isEmpty())  throw new Exception("Graph is empty");
		
		for(int i = 0; i < counter; i++) {
			vertices[i].setVisited(false);
		}
		
	}
	
	public ArrayList<Ttype> searchPathByDepth(Ttype elementFrom, Ttype elementTo) throws Exception
	{
		if(isEmpty())  throw new Exception("Graph is empty");
		int indexFrom = searchVerticeByElement(elementFrom);
		int indexTo = searchVerticeByElement(elementTo);
		
		if(indexFrom == -1 || indexTo == -1)
			throw new Exception("One or both vertices are not in the graph");
		
		
		clearVisited();
		ArrayList<Ttype> result = new ArrayList<Ttype>();
		
		boolean isFound = false;
		
		Stack<MyVerticeNode> stack = new Stack<MyVerticeNode>();
		
		MyVerticeNode verticeNodeFrom = vertices[searchVerticeByElement(elementFrom)];
		stack.push(verticeNodeFrom);
		
		do
		{
			MyVerticeNode tempVNode = stack.pop();
			tempVNode.setVisited(true);
			if(tempVNode.getElement().equals(elementTo)) {
				result.add((Ttype) tempVNode.getElement());
				isFound = true;
				
			}
			else
			{
				result.add((Ttype) tempVNode.getElement());
				ArrayList<MyVerticeNode> neighbours = getNeighbours((Ttype)tempVNode.getElement());
				
				for(MyVerticeNode tempNNode:neighbours)
				{
					if(!tempNNode.isVisited())
					{
						stack.push(tempNNode);
					}
				}
			}
	
		}while(!stack.isEmpty() && !isFound);
		
		
		if(!isFound) 
			throw new Exception("Path from " + elementFrom + " to " + elementTo + " doesn't exist");
		
		
		return result;
	
	}
	
	public void minimumSpanningTree(Ttype startElement) throws Exception{
		if(isEmpty())  throw new Exception("Graph is empty");
		if (startElement == null) throw new Exception("Start element should be not null");
		int indexOfStartedElement = searchVerticeByElement(startElement);
		if (indexOfStartedElement == -1) throw new Exception("Start element is not found in the graph");
		
		clearVisited();
		
		PriorityQueue<MyEdgeNode> edges = new PriorityQueue<>();
		ArrayList<MyEdgeNode> leaveEdges = new ArrayList<>();
		
		vertices[indexOfStartedElement].setVisited(true);
		
		MyEdgeNode tempE = vertices[indexOfStartedElement].getFirstEdgeNode();
		
		while(tempE!=null)
		{
			edges.add(tempE);
			tempE = tempE.getNext();
		}
		
		while(!edges.isEmpty())
		{
			MyEdgeNode shortestEdge = edges.poll();
			MyVerticeNode tempN = vertices[shortestEdge.getIndexOfNeighbour()];
			if(!tempN.isVisited())
			{
				leaveEdges.add(shortestEdge);
				vertices[shortestEdge.getIndexOfNeighbour()].setVisited(true);
				
				
				tempE = vertices[shortestEdge.getIndexOfNeighbour()].getFirstEdgeNode();
				
				while(tempE!=null) {
					
					if(!vertices[tempE.getIndexOfNeighbour()].isVisited())
						edges.add(tempE);
						
					tempE = tempE.getNext();	
				}
				
				
			}
	
		}
		
		for(MyEdgeNode tempEE: leaveEdges) {
			System.out.println(tempEE);
		}
		
		
		
		
		
		
		
		//notīrit isVisited iezīmes
		
		//no sakauma virsotnes ielikt prioritārajā rindā visas saites
		
		
	}
	
	
	

}
