import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.Stream;
import java.io.IOException;

public class Sample {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Enter the name of the file");
            return;
        }

        if (args.length > 1) {
            System.out.println("You must enter only the one file name");
            return;
        }

        String fileNameInput = args[0];
        String currentDirectory = Paths.get("").toAbsolutePath().toString();
        String stringPathToFile = currentDirectory + "/" + fileNameInput;
        Path pathToFile = Paths.get(stringPathToFile);

        if (!Files.exists(pathToFile)) {
            System.out.println("File with provided name does not exist, try another one");
            return;
        }

        long numberOfLines;

        try (Stream<String> fileStream = Files.lines(pathToFile)) {
            numberOfLines = fileStream.count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (numberOfLines == 0) {
            System.out.println("File with name " + fileNameInput + " does not have lines.");
            return;
        }

        if (numberOfLines == 1) {
            System.out.println("File with name " + fileNameInput + " has one line.");
            return;
        }

        if (numberOfLines > 1) {
            System.out.println("File with name " + fileNameInput + " has " + numberOfLines + " number of lines");
            return;
        }
    }
}