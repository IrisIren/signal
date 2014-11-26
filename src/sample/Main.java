package sample;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        //ArrayList<Double> ECGBuffer = new ArrayList<>();
        double ECGBuffer[] = new double[10000];
        double Filter[] = new double[10000];
        double SQR[] = new double[10000];
        double Td = 0.002;
        try {
            BluetoothModelReceiver receiver = new BluetoothModelReceiver(10, "D:\\ECG.txt");
            //создаем объект класса BMR и передаем ему задержку и файл со значениями
            /*while (true) {
                receiver.doStepWithLatency(ECGBuffer);
            }*/
            receiver.receiveECG(ECGBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        double eps = 0.0001;
        for (int i = 0; i < ECGBuffer.length; i++)
            System.out.println(ECGBuffer[i]);
        //System.out.println(ECGBuffer.length);

        for (int i = 1; i < ECGBuffer.length; i++)
            Filter[i] = (ECGBuffer[i] - ECGBuffer[i - 1]) / Td;

        for (int i = 0; i < ECGBuffer.length; i++) {
            SQR[i] = Math.pow(Filter[i], 2);
            System.out.println(SQR[i]);
        }
        System.out.println(SQR.length);



    }
}