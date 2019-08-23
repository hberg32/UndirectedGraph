import java.util.UUID;

public class Line {
    private Node start;
    private Node end;
    private UUID lineId;

    public Line(UUID id) {
        this.lineId = id;
    }
}
