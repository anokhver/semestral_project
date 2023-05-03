package cz.cvut.anokhver;

import java.io.File;

public class FileManagement {

    public static String create_proper_path(String path)
    {
        StringBuilder result = new StringBuilder(System.getProperty("user.dir"));
        String[] array_path = path.split("/");
        for(String i : array_path){
           result.append(File.separator).append(i);
        }

        //System.out.println(String.valueOf(result));

        return String.valueOf(result);
    }
}
