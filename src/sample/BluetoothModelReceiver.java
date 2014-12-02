package sample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class BluetoothModelReceiver extends Thread {
    private BlockingQueue queue;
    private String filePath;

    public BluetoothModelReceiver(BlockingQueue queue, String filePath) {
        this.queue = queue;
        this.filePath = filePath;
    }

    private void addElement(Double el) {
        queue.add(el);
    }


    @Override
    public void run() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String s;
        try {
            while ((s = bufferedReader.readLine()) != null) {
                addElement(Double.valueOf(s));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
