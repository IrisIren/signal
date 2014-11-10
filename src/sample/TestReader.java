package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestReader {
    public TestReader(String filePath) {
        this.filePath = filePath;
    }

    String filePath;

    public List<Double> read() throws IOException {
        List<Double> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            list.add(Double.valueOf(s));
        }
        return list;
    }
}
