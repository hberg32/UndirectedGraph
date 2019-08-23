import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class LinedGraphTest {
    @Test
    public void EmptyGraph() {
        UndirectedGraph ugraph = new LinedGraph();
        assertThat(ugraph.isConnected("Boston", "Hartford"), equalTo(false));
    }

    @Test
    public void YouCantGetThereFromHere() {
        UndirectedGraph ugraph = new LinedGraph();
        ugraph.add(new String[]{"Boston", "Hartford"});
        ugraph.add(new String[]{"MiddleOf", "NoWhere"});
        assertThat(ugraph.isConnected("Boston", "NoWhere"), equalTo(false));
    }

    @Test
    public void SingleConnection() {
        UndirectedGraph ugraph = new LinedGraph();
        ugraph.add(new String[]{"Boston", "Hartford"});
        assertThat(ugraph.isConnected("Boston", "Hartford"), equalTo(true));
    }

    @Test
    public void TransitiveConnection() {
        UndirectedGraph ugraph = new LinedGraph();
        ugraph.add(new String[]{"Boston", "New York"});
        ugraph.add(new String[]{"New York", "Philadelphia"});
        assertThat(ugraph.isConnected("Boston", "Philadelphia"), equalTo(true));
    }

    @Test
    public void Loop() {
        UndirectedGraph ugraph = new LinedGraph();
        ugraph.add(new String[]{"Boston", "New York"});
        ugraph.add(new String[]{"New York", "Philadelphia"});
        ugraph.add(new String[]{"New York", "Boston"});
        assertThat(ugraph.isConnected("Boston", "Philadelphia"), equalTo(true));
    }

    @Test
    public void ParallelRoutes() {
        UndirectedGraph ugraph = new LinedGraph();
        ugraph.add(new String[]{"Boston", "Hartford"});
        ugraph.add(new String[]{"New York", "Hartford"});
        ugraph.add(new String[]{"New Haven", "Boston"});
        ugraph.add(new String[]{"New Haven", "New York"});
        assertThat(ugraph.isConnected("Boston", "New York"), equalTo(true));
    }

    @Test
    public void RailroadMerger() {
        UndirectedGraph ugraph = new LinedGraph();
        //Add nodes for railroad 1
        ugraph.add(new String[]{"Boston", "New York"});
        ugraph.add(new String[]{"New York", "Philadelphia"});
        ugraph.add(new String[]{"New York", "Hartford"});
        //Add nodes for railroad 2
        ugraph.add(new String[]{"Los Angeles", "San Francisco"});
        ugraph.add(new String[]{"San Diego", "Los Angeles"});
        ugraph.add(new String[]{"San Franciso", "Seattle"});

        //Add the connecting city to ensure lines are joined
        assertThat(ugraph.isConnected("Boston", "Los Angeles"), equalTo(false));
        ugraph.add(new String[]{"San Francisco", "New York"});
        assertThat(ugraph.isConnected("Boston", "Los Angeles"), equalTo(true));
    }

    @Test
    public void StarShapedGraph() {
        UndirectedGraph ugraph = new LinedGraph();
        ugraph.add(new String[]{"Boston", "New York"});
        ugraph.add(new String[]{"New York", "Philadelphia"});
        ugraph.add(new String[]{"New York", "Hartford"});
        assertThat(ugraph.isConnected("Boston", "Hartford"), equalTo(true));
    }
}
