package tree.binary;

import java.util.*;

public class BinaryTreeNode {
    
    // [!] Fields made public for exercise purposes only
    public String data;
    public BinaryTreeNode left, right;
    
    /**
     * Creates a new BinaryTreeNode that can be linked to
     * others to form a tree of arbitrary depth
     * @param s The data to store at this tree node
     */
    BinaryTreeNode (String s) {
        this.data = s;
        this.left = null;
        this.right = null;
    }
    
    public int transitions () {
        return transitions(this.left, this.data) + transitions(this.right, this.data);
    }
    
    private int transitions (BinaryTreeNode curr, String parentStr) {
        if (curr == null) { return 0; }
        int count = 0;
        if (!curr.data.equals(parentStr)) {
            count++;
        }
        count += transitions(curr.left, curr.data) + transitions(curr.right, curr.data);
        return count;
    }
    
    public int countSameParent () {
        return countSameParent(this.left, this.data) + countSameParent(this.right, this.data);
    }
    
    private int countSameParent (BinaryTreeNode curr, String parentStr) {
        if (curr == null) { return 0; }
        int count = 0;
        if (curr.data.equals(parentStr)) {
            count++;
        }
        count += countSameParent(curr.left, curr.data) + countSameParent(curr.right, curr.data);
        return count;
    }
    
    public void reverse () {
        reverse(this);
    }
    
    public int count (String s) {
        return count(this, s);
    }
    
    /**
     * Doubles the tree rooted at the node on which this method
     * is called, creating a duplicate of each node, storing the
     * duplicate at the left reference of the original, and then
     * moving any previous left-child from the original to the
     * left child of the duplicate.
     */
    public void doubleTree () {
        doubleTree(this);
    }
    
    private static void doubleTree (BinaryTreeNode n) {
        // Base case: node is null, return
        if (n == null) { return; }
        
        // Recursive cases: use postorder traversal
        // to start doubling at leaves
        doubleTree(n.left);
        doubleTree(n.right);
        
        // Finally, "visit" the current node by doubling
        // to its left child, and preserving its structure
        BinaryTreeNode oldLeft = n.left;
        n.left = new BinaryTreeNode(n.data);
        n.left.left = oldLeft;
    }
    
    public boolean treequal (BinaryTreeNode other) {
        return treequal(this, other);
    }
    
    /**
     * Given two Binary Trees rooted at the provided BinaryTreeNodes
     * n1 and n2, determines whether or not the two trees are
     * equivalent (i.e., have the same nodes with the same values in
     * the same locations in the tree).
     * @param n1 The root of tree 1
     * @param n2 The root of tree 2
     * @return Whether or not n1 and n2 represent the same tree
     */
    private static boolean treequal (BinaryTreeNode n1, BinaryTreeNode n2) {
        // Base case: either null, which will only return true if they both are
        if (n1 == null || n2 == null) {
            return n1 == n2;
        }
        
        // Return true only if the data agrees and both
        // subtrees are equivalent as well (preorder traversal)
        return n1.data.equals(n2.data) &&
               treequal(n1.left, n2.left) &&
               treequal(n1.right, n2.right);
    }
    
    private static void reverse (BinaryTreeNode n) {
        if (n == null) { return; }
        BinaryTreeNode temp = n.left;
        n.left = n.right;
        n.right = temp;
        reverse(n.left);
        reverse(n.right);
    }
    
    private static int count (BinaryTreeNode n, String s) {
        if (n == null) { return 0; }
        int result = (s.equals(n.data)) ? 1 : 0;
        return result + count(n.left, s) + count(n.right, s);
    }
    
    public ArrayList<String> getLeaves () {
        ArrayList<String> result = new ArrayList<>();
        getLeaves(this, result);
        return result;
    }
    
    private void getLeaves(BinaryTreeNode n, ArrayList<String> result) {
        if (n == null) { return; }
        if (n.left == null && n.right == null) {
            result.add(n.data);
        } else {
            getLeaves(n.left, result);
            getLeaves(n.right, result);
        }
    }
    
    /**
     * Returns the number of levels possessed by the tree rooted at this node, 
     * defined as the number of depths that the tree has at least 1 node in. 
     * E.g.,
     *      A       The tree rooted at A has 3 levels because
     *     / \      it has 3 depths with at least 1 node each
     *    B   C     The subtreetree rooted at B has 2 levels because
     *     \        it has 2 depths with at least 1 node each
     *      D
     * @return The number of levels of the tree rooted at this node
     */
    public int levels () {
        return levels(this);
    }
    
    private static int levels (BinaryTreeNode n) {
        if (n == null) { return 0; }
        return 1 + Math.max(levels(n.left), levels(n.right));
    }
    
}
