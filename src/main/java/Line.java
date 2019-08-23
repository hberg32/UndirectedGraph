import java.util.UUID;

public class Line {
    private UUID lineId;

    Line(UUID id) {
        this.lineId = id;
    }

    private UUID getLineId() {
        return lineId;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Line) {
            return lineId.equals(((Line) obj).getLineId());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return lineId.hashCode();
    }
}
