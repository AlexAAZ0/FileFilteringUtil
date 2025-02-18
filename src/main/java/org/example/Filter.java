package org.example;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    private List<String> elements;

    private List<String> stringList;
    private List<String> integerList;
    private List<String> doubleList;

    Filter(List<String> elements) {
        this.elements = elements;
        stringList = new ArrayList<>();
        integerList = new ArrayList<>();
        doubleList = new ArrayList<>();
    }

    public void filter() { // Фильтрация данных по спискам.
        for (int i = 0; i < elements.size(); i++) {
            if (isInteger(elements.get(i))) {
                integerList.add(elements.get(i));
            } else if (isDouble(elements.get(i))) {
                doubleList.add(elements.get(i));
            } else {
                stringList.add(elements.get(i));
            }
        }
    }

    private boolean isInteger(String s) { // Проверка строки на целочисленное значение.
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDouble(String s) { // Проверка строки на дробное значение.
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public List<String> getStringList() {
        return stringList;
    }

    public List<String> getIntegerList() {
        return integerList;
    }

    public List<String> getDoubleList() {
        return doubleList;
    }
}
