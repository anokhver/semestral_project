package cz.cvut.anokhver.additional;

import java.io.File;

public class FileManagement {

    public static String createProperPath(String path){
        StringBuilder result = new StringBuilder(System.getProperty("user.dir"));
        String[] array_path = path.split("/");
        for(String i : array_path){
           result.append(File.separator).append(i);
        }

        return String.valueOf(result);
    }

}
