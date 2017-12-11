import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGraph<V> implements Graph<V> {
    protected List<V> vertices = new ArrayList<V>(); // Store vertices
    protected List<List<Integer>> neighbors = new ArrayList<List<Integer>>(); // Adjacency
    // lists

    /** Construct an empty graph */
    protected AbstractGraph() {
    }

    /** Construct a graph from edges and vertices stored in arrays */
    protected AbstractGraph(int[][] edges, V[] vertices) {
        for (int i = 0; i < vertices.length; i++)
            this.vertices.add(vertices[i]);

        createAdjacencyLists(edges, vertices.length);
    }

    /** Construct a graph from edges and vertices stored in List */
    protected AbstractGraph(List<Edge> edges, List<V> vertices) {
        for (int i = 0; i < vertices.size(); i++)
            this.vertices.add(vertices.get(i));

        createAdjacencyLists(edges, vertices.size());
    }

    /** Construct a graph for integer vertices 0, 1, 2 and edge list */
    @SuppressWarnings("unchecked")
    protected AbstractGraph(List<Edge> edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++)
            vertices.add((V) (new Integer(i))); // vertices is {0, 1, ...}

        createAdjacencyLists(edges, numberOfVertices);
    }

    /** Construct a graph from integer vertices 0, 1, and edge array */
    @SuppressWarnings("unchecked")
    protected AbstractGraph(int[][] edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++)
            vertices.add((V) (new Integer(i))); // vertices is {0, 1, ...}

        createAdjacencyLists(edges, numberOfVertices);
    }

    /** Create adjacency lists for each vertex */
    private void createAdjacencyLists(int[][] edges, int numberOfVertices) {
        // Create a linked list
        for (int i = 0; i < numberOfVertices; i++) {
            neighbors.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            neighbors.get(u).add(v);
        }
    }

    /** Create adjacency lists for each vertex */
    private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) {
        // Create a linked list for each vertex
        for (int i = 0; i < numberOfVertices; i++) {
            neighbors.add(new ArrayList<Integer>());
        }

        for (Edge edge : edges) {
            neighbors.get(edge.u).add(edge.v);
        }
    }

    public boolean isCyclic() {
        for (int i = 0; i < vertices.size(); i++) {
            if(isCyclic(i)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCyclic(int v) {
        List<Integer> searchOrder = new ArrayList<Integer>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }

        boolean[] isVisited = new boolean[vertices.size()];

        return isCyclic(v, v, parent, searchOrder, isVisited);

    }

    private boolean isCyclic(int first, int v, int[] parent, List<Integer> searchOrder, boolean[] isVisited) {
        searchOrder.add(v);
        isVisited[v] = true;

        for (int i : neighbors.get(v)) {
            if (!isVisited[i]) {
                parent[i] = v;
                int[] newParent = java.util.Arrays.copyOf(parent, parent.length);
                boolean[] newIsVisited = java.util.Arrays.copyOf(isVisited, parent.length);
                @SuppressWarnings("unchecked")
                List<Integer> newSearchOrder = (List<Integer>) ((ArrayList<Integer>)searchOrder).clone();
                if(isCyclic(first, i, newParent, newSearchOrder, newIsVisited)) {
                    return true;
                }
            } else if(first == i) {
                if(searchOrder.size() > 2) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    /** Return the number of vertices in the graph */
    public int getSize() {
        return vertices.size();
    }

    @Override
    /** Return the vertices in the graph */
    public List<V> getVertices() {
        return vertices;
    }

    @Override
    /** Return the object for the specified vertex */
    public V getVertex(int index) {
        return vertices.get(index);
    }

    @Override
    /** Return the index for the specified vertex object */
    public int getIndex(V v) {
        return vertices.indexOf(v);
    }

    @Override
    /** Return the neighbors of the specified vertex */
    public List<Integer> getNeighbors(int index) {
        return neighbors.get(index);
    }

    @Override
    /** Return the degree for a specified vertex */
    public int getDegree(int v) {
        return neighbors.get(v).size();
    }

    @Override
    /** Print the edges */
    public void printEdges() {
        for (int u = 0; u < neighbors.size(); u++) {
            System.out.print(getVertex(u) + " (" + u + "): ");
            for (int j = 0; j < neighbors.get(u).size(); j++) {
                System.out.print("(" + u + ", " + neighbors.get(u).get(j)
                        + ") ");
            }
            System.out.println();
        }
    }

    @Override
    /** Clear graph */
    public void clear() {
        vertices.clear();
        neighbors.clear();
    }

    @Override
    /** Add a vertex to the graph */
    public void addVertex(V vertex) {
        vertices.add(vertex);
        neighbors.add(new ArrayList<Integer>());
    }

    @Override
    /** Add an edge to the graph */
    public void addEdge(int u, int v) {
        neighbors.get(u).add(v);
        neighbors.get(v).add(u);
    }

    /** Edge inner class inside the AbstractGraph class */
    public static class Edge {
        public int u; // Starting vertex of the edge
        public int v; // Ending vertex of the edge

        /** Construct an edge for (u, v) */
        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    @Override
    /** Obtain a DFS tree starting from vertex v */
    /** To be discussed in Section 27.6 */
    public Tree dfs(int v) {
        List<Integer> searchOrder = new ArrayList<Integer>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++)
            parent[i] = -1; // Initialize parent[i] to -1

        // Mark visited vertices
        boolean[] isVisited = new boolean[vertices.size()];

        // Recursively search
        dfs(v, parent, searchOrder, isVisited);

        // Return a search tree
        return new Tree(v, parent, searchOrder);
    }

    /** Recursive method for DFS search */
    private void dfs(int v, int[] parent, List<Integer> searchOrder,
                     boolean[] isVisited) {
        // Store the visited vertex
        searchOrder.add(v);
        isVisited[v] = true; // Vertex v visited

        for (int i : neighbors.get(v)) {
            if (!isVisited[i]) {
                parent[i] = v; // The parent of vertex i is v
                dfs(i, parent, searchOrder, isVisited); // Recursive search
            }
        }
    }

    @Override
    /** Starting bfs search from vertex v */
    /** To be discussed in Section 27.7 */
    public Tree bfs(int v) {
        List<Integer> searchOrder = new ArrayList<Integer>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++)
            parent[i] = -1; // Initialize parent[i] to -1

        java.util.LinkedList<Integer> queue = new java.util.LinkedList<Integer>(); // list
        // used
        // as
        // a
        // queue
        boolean[] isVisited = new boolean[vertices.size()];
        queue.offer(v); // Enqueue v
        isVisited[v] = true; // Mark it visited

        while (!queue.isEmpty()) {
            int u = queue.poll(); // Dequeue to u
            searchOrder.add(u); // u searched
            for (int w : neighbors.get(u)) {
                if (!isVisited[w]) {
                    queue.offer(w); // Enqueue w
                    parent[w] = u; // The parent of w is u
                    isVisited[w] = true; // Mark it visited
                }
            }
        }

        return new Tree(v, parent, searchOrder);
    }

    /** Tree inner class inside the AbstractGraph class */
    /** To be discussed in Section 27.5 */

}