package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Ирина on 11.11.2014.
 */
public class __old__BluetoothModelReceiver {

    private long period;
    //private ArrayList<Double> ECGStorage;
    private double ECGStorage[];
    //private Iterator currentMeasure;
    int i = 0;

    public __old__BluetoothModelReceiver(long period, String filePath) throws IOException {
        this.period = period;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String s;
            ECGStorage = new double[10000];
            // index = ECGStorage[i];

            while ((s = bufferedReader.readLine()) != null) {
                ECGStorage[i] = Double.valueOf(s);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void doStepWithLatency(ArrayList<Double> arrayList) {
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
    }*/

    public void receiveECG(double ECGBuffer[]) {
        /*Iterator it = ECGStorage.iterator();
        if (it.hasNext()) {
            Object obj = it.next();
            System.out.println(obj);
            arrayList.add(obj);
            it.remove();
        }*/
        for (i = 0; i < ECGBuffer.length; i++)
            ECGBuffer[i] = ECGStorage[i];

    }
}