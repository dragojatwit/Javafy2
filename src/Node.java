public class Node <T>
{
	public T elem; 
	private Node<T> father;
	private Node<T> child; 
	private Node<T> sibling;
 
    public Node(T elem)
    {
        this.elem = elem;
        setParent(null);
        setChild(null);
        setSibling(null);
    }

    
    public T getElem()
    {
    	return elem;
    }
    
    public void setElem(T elem)
    {
    	this.elem = elem;
    }
    
	/**
	 * @return the father
	 */
	public Node<T> getFather() {
		return father;
	}

	/**
	 * @param father the father to set
	 */
	public void setParent(Node<T> father) {
		this.father = father;
	}

	/**
	 * @return the child
	 */
	public Node<T> getChild() {
		return child;
	}

	/**
	 * @param child the child to set
	 */
	public void setChild(Node<T> child) {
		this.child = child;
	}

	/**
	 * @return the sibling
	 */
	public Node<T> getSibling() {
		return sibling;
	}

	/**
	 * @param sibling the sibling to set
	 */
	public void setSibling(Node<T> sibling) {
		this.sibling = sibling;
	}
    
    
}
