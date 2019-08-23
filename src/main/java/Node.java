import java.util.*;

class Node {
    private List<Node> connections;
    private String name;
    private Set<Line> lines;

    Node(String name) {
        this.name = name;
        connections = new ArrayList<>();
        lines = new HashSet<>();
    }

    void addLink(Node node) {
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
    }

    private String getName() {
        return name;
    }

    private void joinLine(Line line) {
        if(isOnLine(line)) return;
        this.lines.add(line);
        for(Node node : connections) {
            node.joinLine(line);
        }

    }

    private boolean isOnLine(Line line) {
        return lines.contains(line);
    }

    Set<Line> getLines() {
        return Collections.unmodifiableSet(lines);
    }
}
