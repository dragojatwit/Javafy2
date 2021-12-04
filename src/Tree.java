/*
 * https://stackoverflow.com/questions/43920857/n-ary-tree-depth-and-degree-algorithm
 */
public class Tree <T>
{
	public Node<T> root;
	
	public Tree() 
	{
		root = null;
	}
	
	public boolean IsEmpty()
	{
		if(root == null)
		{
			return true;
		}
		return false;
	}
	
	public Node<T> search(T key){
		  return searchAux(this.root, key);
		}

		private Node<T> searchAux(Node<T> node, T key){
			if(node == null) 
		        return null;
		    if (node.getElem() == key)
		        return node;
		    if (node.getChild() != null) {
		        Node<T> foundNode = searchAux(node.getChild(), key);
		        if (foundNode != null)
		            return foundNode;
		    } 
		    return searchAux(node.getSibling(), key);
		}

		public void insert(T father_key, T child_key){
		  if(IsEmpty())
		    this.root = new Node<T>(child_key);
		  else{
		    Node<T> node = new Node<T>(child_key);
		    Node<T> father = search(father_key);
		    if(father.getChild() == null){
		      father.setChild(node);
		      node.setParent(father);
		     }else{
		     if (father.getChild() != null){
		       Node<T> brother = father.getChild(); 
		       while(brother.getSibling() != null){
		         brother = brother.getSibling(); 
		       }
		       brother.setSibling(node);
		       node.setParent(father);
		      }
		    }
		  }
		}
}
