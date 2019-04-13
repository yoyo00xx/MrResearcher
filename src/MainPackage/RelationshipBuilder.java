package MainPackage;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;

/**
 *
 * @author st201565910
 */
public class RelationshipBuilder {

    public static void main(String[] args) {
        Graph graph = new MultiGraph("Tutorial 1");
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        // graph.addNode("D");

        graph.addEdge("AB", "A", "B");
        graph.addEdge("AC", "A", "C");
        for (int i = 0; i < 10; i++) {
            graph.addNode("" + i);
            graph.addEdge("" + i, "A", "" + i);

        }
        for (int i = 11; i < 21; i++) {
            graph.addNode("" + i);
            graph.addEdge("" + i, "B", "" + i);

        }
// graph.addEdge("CB", "C", "B");
        for (Node node : graph.getNodeSet()) {
            node.addAttribute("ui.label", node.getId());
        }
        graph.getNode("B").setAttribute("ui.class", "marked");

        String s = "graph {\n"
                + "	fill-mode: gradient-vertical; fill-color: gray, white, white, white, white, gray;\n"
                + "}\n"
                + "\n"
                + "node {\n"
                + "	size: 50px, 50px;\n"
                + "	shape: triangle;\n"
                + "text-size: 25px; padding: 20px;"
                + "	fill-color: gold;\n"
                + "text-style: bold; text-font: calibri;"
                + " stroke-mode: plain; stroke-color: #999; "
                + "shadow-mode: gradient-radial; shadow-width: 10px;"
                + " shadow-color: #999, white;"
                + " shadow-offset: 3px, -3px;\n"
                + "}\n"
                + "\n"
                //  + "node#A {\n"
                //  + "	fill-color: blue;\n"
                //  + "}\n"
                //  + "\n"
                + "node:clicked {\n"
                + "	fill-color: red;\n"
                + "}"
                + "edge { fill-color: darkred; shadow-mode: plain; shadow-width: 3px; shadow-color: #FC0; shadow-offset: 0px; size: 4px; }";

        graph.addAttribute("ui.stylesheet", s);

        Viewer v = graph.display();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(RelationshipBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        //  v.disableAutoLayout();

    }

    public static 
}
