import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);

            String inputDir = props.getProperty("input.directory");
            String outputPath = props.getProperty("output.file");

            File outputFile = new File(outputPath);
            outputFile.getParentFile().mkdirs();  // Crear directoris si cal

            DirectoryTreeWriter.writeTreeToFile(inputDir, outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}