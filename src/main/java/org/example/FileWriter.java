package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class FileWriter {

    private List<String> integerList;
    private List<String> doubleList;
    private List<String> stringList;

    private Map<String, String> options;

    public FileWriter(Filter filter, Map<String, String> options) {
        integerList = filter.getIntegerList();
        doubleList = filter.getDoubleList();
        stringList = filter.getStringList();

        this.options = options;
    }

    public void write() throws IOException{
        if (!integerList.isEmpty()) {
            writeList(integerList, Types.integers, options);
        }
        if (!doubleList.isEmpty()) {
            writeList(doubleList, Types.doubles, options);
        }
        if (!stringList.isEmpty()) {
            writeList(stringList, Types.strings, options);
        }



    }

    private void writeList(List<String> list, Types type, Map<String, String> options) throws IOException {
        StringBuilder path = new StringBuilder();

        if (options.keySet().contains("-o")) {
            path.append(options.get("-o")).append("/");
        }
        if (options.keySet().contains("-p")) {
            path.append(options.get("-p"));
        }
        path.append(type).append(".txt");

        boolean append = false;
        if (options.keySet().contains("-a")) {
            append = true;
        }

        int count = 0;
        try (FileOutputStream writer = new FileOutputStream(String.valueOf(path), append)) {
            for (String elem : list) {
                String newElem = elem + "\n";
                byte[] buffer = newElem.getBytes();
                count++;
                writer.write(buffer);
            }
        }



        if (options.containsKey("stat")) {
            System.out.println(type + ": " + count); // Записанное количество элементов.
        }
    }

}
