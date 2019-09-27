import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class LinedGraphTest {
    @Test
    public void EmptyGraph() {
        UndirectedGraph ugraph = new LinedGraph();
        assertThat(ugraph.isConnected("Point-A", "Point-B"), equalTo(false));
    }

    @Test
    public void YouCantGetThereFromHere() {
        UndirectedGraph ugraph = new LinedGraph();
        ugraph.add(new String[]{"Point-A", "Point-B"});
        ugraph.add(new String[]{"MiddleOf", "NoWhere"});
        assertThat(ugraph.isConnected("Point-A", "NoWhere"), equalTo(false));
    }

    @Test
    public void SingleConnection() {
        UndirectedGraph ugraph = new LinedGraph();
        ugraph.add(new String[]{"Point-A", "Point-B"});
        assertThat(ugraph.isConnected("Point-A", "Point-B"), equalTo(true));
    }

    @Test
    public void SelfConnection() {
        UndirectedGraph ugraph = new LinedGraph();
        ugraph.add(new String[]{"Point-A", "Point-A"});
        assertThat(ugraph.isConnected("Point-A", "Point-B"), equalTo(false));
        assertThat(ugraph.isConnected("Point-A", "Point-A"), equalTo(true));
    }

    @Test
    public void TransitiveConnection() {
        UndirectedGraph ugraph = new LinedGraph();
        ugraph.add(new String[]{"Point-A", "Point-C"});
        ugraph.add(new String[]{"Point-C", "Point-D"});
        assertThat(ugraph.isConnected("Point-A", "Point-D"), equalTo(true));
    }

    @Test
    public void Loop() {
        UndirectedGraph ugraph = new LinedGraph();
        ugraph.add(new String[]{"Point-A", "Point-C"});
        ugraph.add(new String[]{"Point-C", "Point-D"});
        ugraph.add(new String[]{"Point-C", "Point-A"});
        assertThat(ugraph.isConnected("Point-A", "Point-D"), equalTo(true));
    }

    @Test
    public void ParallelRoutes() {
        //In this test there are multiple routes from the source to the destination
        UndirectedGraph ugraph = new LinedGraph();
        ugraph.add(new String[]{"Point-A", "Point-B"});
        ugraph.add(new String[]{"Point-C", "Point-B"});
        ugraph.add(new String[]{"Point-E", "Point-A"});
        ugraph.add(new String[]{"Point-E", "Point-C"});
        assertThat(ugraph.isConnected("Point-A", "Point-C"), equalTo(true));
    }

    @Test
    public void RailroadMerger() {
        UndirectedGraph ugraph = new LinedGraph();
        //Add nodes for railroad 1
        ugraph.add(new String[]{"Point-A", "Point-C"});
        ugraph.add(new String[]{"Point-C", "Point-D"});
        ugraph.add(new String[]{"Point-C", "Point-B"});
        //Add nodes for railroad 2
        ugraph.add(new String[]{"Point-F", "Point-G"});
        ugraph.add(new String[]{"Point-H", "Point-F"});
        ugraph.add(new String[]{"Point-G", "Point-I"});

        //Add the connecting city to ensure lines are joined
        assertThat(ugraph.isConnected("Point-A", "Point-F"), equalTo(false));
        ugraph.add(new String[]{"Point-G", "Point-C"});
        assertThat(ugraph.isConnected("Point-A", "Point-F"), equalTo(true));
    }

    @Test
    public void StarShapedGraph() {
        UndirectedGraph ugraph = new LinedGraph();
        ugraph.add(new String[]{"Point-A", "Point-C"});
        ugraph.add(new String[]{"Point-C", "Point-D"});
        ugraph.add(new String[]{"Point-C", "Point-B"});
        assertThat(ugraph.isConnected("Point-A", "Point-B"), equalTo(true));
    }
}
