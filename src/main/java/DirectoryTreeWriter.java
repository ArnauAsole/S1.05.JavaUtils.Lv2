import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class DirectoryTreeWriter {

    public static void writeTreeToFile(String path, File outputFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writeRecursive(new File(path), writer, "");
            System.out.println("Output writted in: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeRecursive(File dir, BufferedWriter writer, String indent) throws IOException {
        if (!dir.exists() || !dir.isDirectory()) {
            writer.write("Isn't a valid directory: " + dir.getAbsolutePath());
            return;
        }

        File[] files = dir.listFiles();
        if (files == null) return;

        Arrays.sort(files);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (File file : files) {
            String type = file.isDirectory() ? "D" : "F";
            String modified = sdf.format(new Date(file.lastModified()));
            writer.write(indent + type + " - " + file.getName() + " (" + modified + ")");
            writer.newLine();

            if (file.isDirectory()) {
                writeRecursive(file, writer, indent + "  ");
            }
        }
    }
}
