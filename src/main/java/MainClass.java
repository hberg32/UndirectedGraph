import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainClass {
    private UndirectedGraph ugraph;

    public static void main(String[] args) {
        if(args.length != 2) {
            throw new RuntimeException("Usage: MainClass city1 city2");
        }
        new MainClass().doTheThing(args);
    }

    private void doTheThing(String[] args) {
        ugraph = new LinedGraph();

        try {
            URL cityFile = this.getClass().getClassLoader().getResource("cities.txt");
            if(cityFile == null) {
                throw new RuntimeException("The input file 'cities.txt' could not be found on the classpath");
            }
            Files.readAllLines(Paths.get(cityFile.toURI()))
                    .forEach(line -> ugraph.add(line.split(",")));
        }
        catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        if(ugraph.isConnected(args[0], args[1])) {
            System.out.printf("Cities '%s' and '%s' are connected.\n", args[0], args[1]);
        }
        else {
            System.out.printf("Cities '%s' and '%s' are not connected.\n", args[0], args[1]);
        }

    }
}
