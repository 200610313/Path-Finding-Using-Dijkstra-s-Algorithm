/**
 * Implements the structure of a Binary Search Tree
 */
public class BST {

    public Node createNewNode(String k, int j){
        Node a = new Node();
        a.data = k;
        a.loc = j;
        a.left = null;
        a.right = null;
        return a;
    }

    /**
     *Inserts a new node into the Binary Search Tree
     *
     * @param node The current node
     * @param val Name of the establishment
     * @param l Vertex location of the establishment
     * @return returns the new node to be included in the tree
     */
    public Node insert(Node node, String val, int l){
        if(node == null){//if empty tree
            return createNewNode(val, l);
        }

        if (val.compareTo(node.data)<0){
            //if added is less than the compared node, put to left
            node.left = insert(node.left, val, l);
        }
        else if(val.compareTo(node.data)>0){
            //if added is greater, put to right
            node.right = insert(node.right, val, l);
        }
        return node;
    }

    /**
     * Returns the loc info of the node.
     *
     * @param node The current node
     * @param val Name of the establishment
     * @return returns the vertex location of the name
     */

    public int getVertex(Node node, String val){
        boolean isFound = false;

        int loc = -1;

        while (node != null){
            if (val.compareTo(node.data)<0){//if alphabetically less than, search for the left child
                node = node.left;
            }
            else if (val.compareTo(node.data)>0){//if alphabetically more than,
                node = node.right;
            }
            else if (val.equals(node.data)){//if equals
                loc = node.loc;
                break;
            }
        }
        return loc;
    }

    /**
     * Prints the info of each node in the tree.
     * d is used to retrieve information on the distance of each node's loc to source
     *
     * @param node The current node
     * @param d An instance of Dijkstra's algorithm
     */

    public void printInorder(Node node, dijkstra d){
        if (node==null){
            return;
        }
        printInorder(node.left, d);
        System.out.println(node.data+" | "+d.getIntersection(node.loc)+ " ");
        printInorder(node.right, d);
    }
}
