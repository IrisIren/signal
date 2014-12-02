package sample;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) {
        //queue
        BlockingQueue queue = new LinkedBlockingQueue();
        //string filename
        final String filepath = "D:\\ECG.txt";
        BluetoothModelReceiver bluetoothModelReciever = new BluetoothModelReceiver(queue, filepath);
        ECGSignalHandler ecgHandler = new ECGSignalHandler(queue);

        bluetoothModelReciever.start();
        ecgHandler.start();
    }
}