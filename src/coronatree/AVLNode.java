package coronatree;

public class AVLNode {	
	Person data;		// The data in the node
	AVLNode parent;		// The parent
	AVLNode left;       // Left child
	AVLNode right;      // Right child
	int height;       	// Height

	/**
	 * A standard constructor, sets all pointers to null.
	 * 
	 * @param p - the data of the node.
	 */
    AVLNode(Person p) {
         data = p;
         parent = null;
         left = null;
         right = null;
         height = 0;
    }
    
    /**
     * A standard constructor
     * 
     * @param p - the data of the node.
     * @param lt - the left child.
     * @param rt - the right child.
     * @param parent - the parent.
     */
    AVLNode(Person p, AVLNode lt, AVLNode rt, AVLNode parent){
		data = p;
		left = lt;
		right = rt;
		this.parent = parent;
		height = Math.max(getHeightLeft(),getHeightRight()) + 1;
    }

    public int getHeightLeft(){
    	if (left == null)
    		return -1;
    	return left.height;
	}

	public int getHeightRight(){
		if (right == null)
			return -1;
		return right.height;
	}

    public int bf(){

    	return getHeightLeft() - getHeightRight();
	}

    public String toString(){
    	return this.data.toString();
    }

}
