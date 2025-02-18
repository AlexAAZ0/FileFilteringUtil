package org.example;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        args = new String[]{"-a", "-f", "-p", "tr", "t.txt", "t2.txt"};

        try {
            InputParser parser = new CLParser(args);
            Info info = parser.parse();

            FileReader fileReader = new FileReader(info.files());
            Filter filter = new Filter(fileReader.read());
            filter.filter();
            FileWriter fileWriter = new FileWriter(filter, info.options());
            fileWriter.write();


        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
        } catch (IOException exception) {
            System.out.println("An error occurred while working with files");
        } catch (Exception exception) {
            System.out.println("Something went wrong");
        }

    }
}