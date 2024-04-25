import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the file: ");
        String fileName = scanner.nextLine();

        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            int numberOfVertices = Integer.parseInt(fileScanner.nextLine().trim());
            WeightedGraph graph = new WeightedGraph(numberOfVertices);

            while (fileScanner.hasNextLine()) {
                String[] triplets = fileScanner.nextLine().split("\\|");
                for (String triplet : triplets) {
                    String[] values = triplet.trim().split(",");
                    int u = Integer.parseInt(values[0].trim());
                    int v = Integer.parseInt(values[1].trim());
                    int weight = Integer.parseInt(values[2].trim());
                    graph.addEdge(u, v, weight);
                }
            }

            System.out.println("All edges in the graph:");
            graph.printWeightedEdges();

            WeightedGraph.MST tree = graph.getMinimumSpanningTree();
            System.out.println("Total weight of minimum spanning tree: " + tree.getTotalWeight());
            System.out.println("Minimum spanning tree:");
            tree.printTree();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid format in the file.");
        }
    }
}
