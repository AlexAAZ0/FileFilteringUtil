package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    List<String> files;
    FileReader(List<String> files) {
        this.files = files;
    }

    public List<String> read() throws IOException { // Запись элементов в единый список.
        List<String> generalList = new ArrayList<>();

        for (String fileName : files) {
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    generalList.add(line);
                }
            }
        }

        return generalList;
    }
}