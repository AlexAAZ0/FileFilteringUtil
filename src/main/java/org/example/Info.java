package org.example;

import java.util.List;
import java.util.Map;

public record Info(List<String> files, Map<String, String> options) {
}
