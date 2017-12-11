import java.util.List;

public class Tree  {
    private int root; // The root of the tree
    private int[] parent; // Store the parent of each vertex
    private List<Integer> searchOrder; // Store the search order

    /** Construct a tree with root, parent, and searchOrder */
    public Tree(int root, int[] parent, List<Integer> searchOrder) {
        this.root = root;
        this.parent = parent;
        this.searchOrder = searchOrder;
    }

    /** Return the root of the tree */
    public int getRoot() {
        return root;
    }

    /** Return the parent of vertex v */
    public int getParent(int v) {
        return parent[v];
    }

    /** Return an array representing search order */
    public List<Integer> getSearchOrder() {
        return searchOrder;
    }

    /** Return number of vertices found */
    public int getNumberOfVerticesFound() {
        return searchOrder.size();
    }

    /** Return the path of vertices from a vertex to the root */
    public List<V> getPath(int index) {
        ArrayList<V> path = new ArrayList<V>();

        do {
            path.add(vertices.get(index));
            index = parent[index];
        } while (index != -1);

        return path;
    }

    /** Print a path from the root to vertex v */
    public void printPath(int index) {
        List<V> path = getPath(index);
        System.out.print("A path from " + vertices.get(root) + " to "
                + vertices.get(index) + ": ");
        for (int i = path.size() - 1; i >= 0; i--)
            System.out.print(path.get(i) + " ");
    }

    /** Print the whole tree */
    public void printTree() {
        System.out.println("Root is: " + vertices.get(root));
        System.out.print("Edges: ");
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] != -1) {
                // Display an edge
                System.out.print("(" + vertices.get(parent[i]) + ", "
                        + vertices.get(i) + ") ");
            }
        }
        System.out.println();
    }
}