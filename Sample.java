import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.Stream;
import java.io.IOException;

public class Sample {
    public static void main(final String[] args) throws IOException {
        if (args.length == 0) {
            throw new IOException("Enter the name of the file");
        }

        if (args.length > 1) {
            throw new IOException("You must enter only the one file name");
        }

        final Path pathToFile = Paths.get(Paths.get("").toAbsolutePath().toString() + "/" + args[0]);

        if (!Files.exists(pathToFile)) {
            throw new IOException("File with provided name does not exist, try another one");
        }

        long numberOfLines;

        try (Stream<String> fileStream = Files.lines(pathToFile)) {
            numberOfLines = fileStream.count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (numberOfLines == 0) {
            System.out.println("File with name " + args[0] + " does not have lines.");
            return;
        }

        if (numberOfLines == 1) {
            System.out.println("File with name " + args[0] + " has one line.");
            return;
        }

        System.out.println("File with name " + args[0] + " has " + numberOfLines + " lines");
    }
}

