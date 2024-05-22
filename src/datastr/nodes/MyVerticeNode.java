package datastr.nodes;

public class MyVerticeNode<Ttype> {
	private Ttype element;
	private MyEdgeNode firstEdgeNode = null;
	private boolean isVisited = false;
	
	//get and set
	public Ttype getElement() {
		return element;
	}
	public void setElement(Ttype element) {
		if(element != null)
			this.element = element;
		else
			this.element = (Ttype)new Object();
	}
	public MyEdgeNode getFirstEdgeNode() {
		return firstEdgeNode;
	}
	public void setFirstEdgeNode(MyEdgeNode firstEdgeNode) {
		this.firstEdgeNode = firstEdgeNode;
	}

	
	
	public boolean isVisited() {
		return isVisited;
	}
	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
	//constructors
	public MyVerticeNode(Ttype element)
	{
		setElement(element);
	}
	
	//toString
	
	public String toString() {
		return ""+ element;
	}

}
