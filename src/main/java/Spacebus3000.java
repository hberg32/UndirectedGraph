import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Spacebus3000 {
    private UndirectedGraph ugraph;

    public static void main(String[] args) {
        if(args.length != 3) {
            throw new RuntimeException("Usage: Spacebus3000 filename city1 city2");
        }
        new Spacebus3000().doTheThing(args);
    }

    private void doTheThing(String[] args) {
        ugraph = new LinedGraph();

        try {
            URL cityFile = this.getClass().getClassLoader().getResource(args[0]);
            if(cityFile == null) {
                throw new RuntimeException("The input file 'cities.txt' could not be found on the classpath");
            }
            Files.readAllLines(Paths.get(cityFile.toURI()))
                    .forEach(line -> ugraph.add(line.split(",")));
        }
        catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        if(ugraph.isConnected(args[1], args[2])) {
            System.out.printf("Cities '%s' and '%s' are connected.\n", args[1], args[2]);
        }
        else {
            System.out.printf("Cities '%s' and '%s' are not connected.\n", args[1], args[2]);
        }

    }
}
