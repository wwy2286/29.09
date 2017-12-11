import java.util.List;

public class MST extends Tree {
    private double totalWeight; // Total weight of all edges in the tree

    public MST(int root, int[] parent, List<Integer> searchOrder,
               double totalWeight) {
        super(root, parent, searchOrder);
        this.totalWeight = totalWeight;
    }

    public double getTotalWeight() {
        return totalWeight;
    }
}