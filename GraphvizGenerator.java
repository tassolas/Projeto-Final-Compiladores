import java.io.FileWriter;
import java.io.IOException;

public class GraphvizGenerator {

    private static int id = 0;

    public static void generate(Node root, String filename) {
        try {
            FileWriter writer = new FileWriter(filename);

            writer.write("digraph G {\n");

            id = 0;
            generateNode(root, writer);

            writer.write("}\n");
            writer.close();

            System.out.println("Arquivo DOT gerado: " + filename);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int generateNode(Node node, FileWriter writer)
            throws IOException {

        int currentId = id++;

        writer.write(
            "node" + currentId +
            " [label=\"" +
            node.getName().replace("\"", "\\\"") +
            "\"];\n"
        );

        for (Node child : node.getChildren()) {

            int childId = generateNode(child, writer);

            writer.write(
                "node" + currentId +
                " -> node" + childId +
                ";\n"
            );
        }

        return currentId;
    }
}