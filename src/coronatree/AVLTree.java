package coronatree;

public class AVLTree {

    AVLNode root; 	// The tree root.
    int size;		// The size of the tree.

    /**
     * Construct an empty tree.
     */
    public AVLTree() {
       	root = new AVLNode(null);
       	size = 0;
    }
    
    /**
     * Returns the size of the tree.
     * 
     * @return the size of the tree.
     */
    public int size(){
    	return size;
    }
    
    /**
     * Returns the height of the tree.
     * Returns -1 if the tree is empty.
     * 
     * @return the height of the tree.
     */
    public int height(){
        if (size == 0)
            return -1;
        return root.height;
    }
    
    /**
     * Inserts into the tree; You may assume that every person has a unique ID number.
     * That is, no person will appear twice.
     * 
     * @param p - the person to insert.
     */
    public void insert(Person p) {
        AVLNode check = root;
        size++;
        if (size == 1){
            root = new AVLNode(p,null,null, null);
            return;
        }
        while ((p.id > check.data.id && check.right != null) || (p.id < check.data.id && check.left != null)){
            if (check.data.id < p.id)
                check = check.right;
            else
                check = check.left;
        }
        AVLNode n = new AVLNode(p,null,null, check);
        if (p.id > check.data.id){
            check.right = n;
        }else
            check.left = n;
        for (AVLNode q = n.parent; q != null; q = q.parent) {
            if (Math.abs(q.bf()) > 1)
                rebalance(q);
            else
                updateH1(q);
        }
    }

    public void rebalance(AVLNode x){
        if(x.bf() > 1){
            if (x.left.bf() < 0)
                leftR(x.left);
            rightR(x);
        }else {
            if (x.right.bf() > 0)
                rightR(x.right);
            leftR(x);
        }
    }

    public void leftR(AVLNode x){
        AVLNode z = x.right;
        AVLNode t23 = z.left;
        x.right = t23;
        if (z.left != null) {
            t23.parent = x;
        }
        z.parent = x.parent;
        if (x.parent != null) {
            if (x == x.parent.left)
                x.parent.left = z;
            if (x == x.parent.right)
                x.parent.right = z;
        }else
            root = z;
        z.left = x;
        x.parent = z;
        updateH1(x);
        updateH1(z);
        updateH(x.parent);
    }

    public void rightR(AVLNode x){
        AVLNode z = x.left;
        AVLNode t23 = z.right;
        x.left = t23;
        if (z.right != null) {
            t23.parent = x;
        }
        z.parent = x.parent;
        if (x.parent != null) {
            if (x == x.parent.right)
                x.parent.right = z;
            if (x == x.parent.left)
                x.parent.left = z;
        }else
            root = z;
        z.right = x;
        x.parent = z;
        updateH1(x);
        updateH1(z);
        updateH(x.parent);
    }

    public void updateH(AVLNode x){
        while (x != null){
            updateH1(x);
            x = x.parent;
        }
    }

    public void updateH1(AVLNode x){
        x.height = Math.max(x.getHeightLeft(), x.getHeightRight()) + 1;
    }

    /**
     * Search for a person in the tree.
     * 
     * @param p the person to search for.
     * @return true iff 'p' is an element in the tree.
     */
    public boolean search(Person p) {
        AVLNode i = root;
    	while (i.right != null || i.left != null){
            if (i.data.id == p.id)
                return true;
            if (i.data.id > p.id) {
                i = i.left;
            } else
                i = i.right;
        }
    	return false;
    }        
    static int j = 0;
    /**
     * Traverse the contents of this tree in an 'inorder' manner and return and array
     * containing the traversal of the tree.
     * 
     * @return a sorted array of the tree's content.
     */
    public Person[] inorder(){
        Person[] arr = new Person[size];
        AVLNode index = root;
        j = 0;
        inorder(index,arr);
        return arr;
    }

    public void inorder(AVLNode root, Person[] arr){
        if (root.left != null) {
            inorder(root.left, arr);
        }
        arr[j++] = root.data;
        if (root.right != null) {
            inorder(root.right, arr);
        }
    }

 
}
