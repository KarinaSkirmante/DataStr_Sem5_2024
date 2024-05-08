package datastr.nodes;

public class MyVerticeNode<Ttype> {
	private Ttype element;
	private MyEdgeNode firstEdgeNode = null;
	
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
