package chain.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class FileReader {

    public static Set<String> read(String filePath) {
        Set<String> content = new HashSet<>();
        Path path = Paths.get(filePath);

        try (InputStream in = Files.newInputStream(path);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(in))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.isEmpty()) {
                    content.add(currentLine);
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return content;
    }

}
