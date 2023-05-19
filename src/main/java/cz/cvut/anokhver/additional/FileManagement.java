package cz.cvut.anokhver.additional;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * Class to create proper path for loading different files
 *
 * @author Veronika
 */
public class FileManagement {

    public static String createProperPath(String path) {
        StringBuilder result = new StringBuilder();
        String[] array_path = path.split("/");
        for (String i : array_path) {
            result.append(File.separator).append(i);
        }

        return String.valueOf(result);
    }

    public static InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = FileManagement.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

    public static void createFolderIfNotExists(String folderPath) {
        Path path = Paths.get(createProperPath(folderPath));
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Folder already exists: " + folderPath);
        }
    }


}
