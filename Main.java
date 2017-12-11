import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.print("Enter a file name: ");
        @SuppressWarnings("resource")
        Scanner inputFileName = new Scanner(System.in);
        String fileName = inputFileName.nextLine();
        Scanner inputGraph = new Scanner(new File(fileName));
        int numberOfVertices = inputGraph.nextInt();
        inputGraph.nextLine();
        System.out.println("The number of vertices is " + numberOfVertices);
        ArrayList<Integer> vertices = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++) {
            vertices.add(i);
        }
        ArrayList<WeightedEdge> edges = new ArrayList<>();
        while(inputGraph.hasNextLine()) {
            String nextLine = inputGraph.nextLine();
            nextLine = nextLine.replaceAll(",", "");
            nextLine = nextLine.replaceAll("\\|", "");
            Scanner inputVertic = new Scanner(nextLine);
            while(inputVertic.hasNext()) {
                int u = inputVertic.nextInt();
                int v = inputVertic.nextInt();
                double weight = inputVertic.nextDouble();
                edges.add(new WeightedEdge(u, v, weight));
                edges.add(new WeightedEdge(v, u, weight));
            }
            inputVertic.close();
        }
        inputGraph.close();
        WeightedGraph<Integer> graph = new WeightedGraph<>(edges, vertices);
        graph.printWeightedEdges();

        WeightedGraph<Integer>.MST tree = graph.getMinimumSpanningTree();
        System.out.println("Total weight is " + tree.getTotalWeight());
        tree.printTree();

    }









}