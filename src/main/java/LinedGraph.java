import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LinedGraph implements UndirectedGraph {
    private Map<String, Node> allNodes;

    public LinedGraph() {
        allNodes = new HashMap<>();
    }

    @Override
    public void add(String[] nodes) {
        //We'll keep node connections in alphabetical order so we don't have to worry about edge direction in the allNodes collection
        String from;
        String to;
        int i = nodes[0].compareTo(nodes[1]);
        if(i == 0) return;
        if(i > 0) {
            from = nodes[1].trim();
            to = nodes[0].trim();
        }
        else {
            from = nodes[0].trim();
            to = nodes[1].trim();
        }

        Node fromNode = allNodes.get(from);
        Node toNode = allNodes.get(to);
        if(fromNode == null) {
            fromNode = new Node(from);
            allNodes.put(from, fromNode);
        }
        if(toNode == null) {
            toNode = new Node(to);
            allNodes.put(to, toNode);
        }
        fromNode.addLink(toNode);
        toNode.addLink(fromNode);

    }

    @Override
    public boolean isConnected(String from, String to) {
        if(from == null || to == null) return false;
        Node fromNode = allNodes.get(from);
        Node toNode = allNodes.get(to);

        if(from == to) return true;
        if(fromNode == null || fromNode.getLines().isEmpty()) {
            return false; //From node isn't connected to anything
        }
        else if(toNode == null || toNode.getLines().isEmpty()) {
            return false; //To node isn't connected to anything
        }

        //Nodes sharing any lines are connected
        return !Collections.disjoint(fromNode.getLines(), toNode.getLines());
    }
}
