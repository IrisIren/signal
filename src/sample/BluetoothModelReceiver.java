package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Ирина on 11.11.2014.
 */
public class BluetoothModelReceiver {

    private long period;
    private ArrayList<Double> ECGStorage;

    public BluetoothModelReceiver(long period, String filePath) throws IOException {
        this.period = period;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String s;
            ECGStorage = new ArrayList<>();

            while ((s = bufferedReader.readLine()) != null) {
                ECGStorage.add(Double.valueOf(s));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doStep(ArrayList arrayList) {
        //с заданной задержкой брать из testreader кусок информации и класть ее в очередьs
        long now = System.currentTimeMillis();
        //вызываем функцию receiveECGPortion()
        receiveECGPortion(arrayList);
        long after = System.currentTimeMillis();
        long t = after - now;
        if (t < period) //вызвать задержку
            try {
                Thread.sleep(period - t);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
    }

    private void receiveECGPortion(ArrayList arrayList) {
        Iterator it = ECGStorage.iterator();
        if (it.hasNext()) {
            Object obj = it.next();
            System.out.println(obj);
            arrayList.add(obj);
            it.remove();
        }
    }
}