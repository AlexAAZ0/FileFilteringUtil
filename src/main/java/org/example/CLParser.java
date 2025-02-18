package org.example;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CLParser implements InputParser{

    private String[] args;

    CLParser(String[] args) throws IllegalArgumentException{
        if (args.length == 0) {
            throw new IllegalArgumentException("Number of arguments cannot be zero");
        }
        this.args = args;
    }

    public Info parse() throws IllegalArgumentException{
        List<String> listFiles = new ArrayList<>();
        boolean isOptions = true;
        String regEx = "^.+\\.txt$";
        Map<String, String> map = new Hashtable<>();

        for (int i = 0; i < args.length; i++) { // Проверка и анализ входных данных.
            if (isOptions) {
                if (map.containsKey(args[i])) {
                    throw new IllegalArgumentException("Double use of " + args[i] + " option");
                }
                if (args[i].equals("-a")) {
                    map.put("-a", "");
                    continue;
                }
                if (args[i].equals("-s")) {
                    if (map.containsKey("stat")) {
                        throw new IllegalArgumentException("-f and -s can only be used once.");
                    }
                    map.put("stat", "s");
                    continue;
                }
                if (args[i].equals("-f")) {
                    if (map.containsKey("stat")) {
                        throw new IllegalArgumentException("-f and -s can only be used once.");
                    }
                    map.put("stat", "f");
                    continue;
                }
                if (args[i].equals("-o")) {
                    if (args.length >= i + 2) {
                        map.put("-o", args[++i]);
                        continue;
                    }
                    throw new IllegalArgumentException("Incorrect argument after -o or -p: " + args[i]);
                }
                if (args[i].equals("-p")) {
                    if (args.length >= i + 2) {
                        map.put("-p", args[++i]);
                        continue;
                    }
                    throw new IllegalArgumentException("Incorrect argument after -o or -p: " + args[i]);
                }

                isOptions = false;
            }
            if (isOptions == false) {
                if (Pattern.matches(regEx, args[i])) {
                    if (listFiles.contains(args[i])) {
                        throw new IllegalArgumentException("The files can't be the same: " + args[i]);
                    }
                    listFiles.add(args[i]);
                    isOptions = false;
                    continue;
                }
                throw new IllegalArgumentException("Incorrect parameter: " + args[i]);
            }
        }

        return new Info(listFiles, map);
    }
}