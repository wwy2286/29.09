import java.util.List;

public class ShortestPathTree extends Tree {
    private double[] cost; // cost[v] is the cost from v to source

    /** Construct a path */
    public ShortestPathTree(int source, int[] parent,
                            List<Integer> searchOrder, double[] cost) {
        super(source, parent, searchOrder);
        this.cost = cost;
    }

    /** Return the cost for a path from the root to vertex v */
    public double getCost(int v) {
        return cost[v];
    }

    /** Print paths from all vertices to the source */
    public void printAllPaths() {
        System.out.println("All shortest paths from "
                + vertices.get(getRoot()) + " are:");
        for (int i = 0; i < cost.length; i++) {
            printPath(i); // Print a path from i to the source
            System.out.println("(cost: " + cost[i] + ")"); // Path cost
        }
    }
}