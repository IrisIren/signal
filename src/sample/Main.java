package sample;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Double> ECGBuffer = new ArrayList<>();
        try {
            BluetoothModelReceiver receiver = new BluetoothModelReceiver(10, "D:\\ECG.txt");
            while (true) {
                receiver.doStep(ECGBuffer);
            }
        } catch (IOException e) {
        }
    }
}