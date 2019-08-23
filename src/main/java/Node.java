import java.util.*;

public class Node {
    private List<Node> connections;
    private String name;
    private Set<Line> lines;

    public Node(String name) {
        this.name = name;
        connections = new ArrayList<>();
        lines = new HashSet<>();
    }

    public boolean addLink(Node node) {
        connections.add(node);
        if(this.lines.isEmpty() && node.getLines().isEmpty()) {
            //This link constitutes the first two nodes in a new line
            Line newLine = new Line(UUID.randomUUID());
            joinLine(newLine);
        }
        else {
            //We're joining two lines
            for (Line line : node.getLines()) {
                joinLine(line);
            }
        }

        return true;
    }

    private String getName() {
        return name;
    }

    public boolean joinLine(Line line) {
        if(isOnLine(line)) return true;
        this.lines.add(line);
        for(Node node : connections) {
            node.joinLine(line);
        }

        return true;
    }

    public boolean isOnLine(Line line) {
        return lines.contains(line);
    }

    public Set<Line> getLines() {
        return Collections.unmodifiableSet(lines);
    }
}
