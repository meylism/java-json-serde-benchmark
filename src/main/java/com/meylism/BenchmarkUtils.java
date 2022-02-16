package com.meylism;

import org.apache.hadoop.io.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class BenchmarkUtils {
    public static Text loadJson(final String resourceName) throws IOException {
        final InputStream stream = BenchmarkUtils.class.getClass().getResourceAsStream("/json/" + resourceName);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        final String jsonText = reader.lines().collect(Collectors.joining(""));
        return new Text(jsonText.replaceAll("\\s+", ""));
    }
}
