package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import static java.lang.Math.pow;

public class ECGSignalHandler extends Thread {
    private BlockingQueue<Double> queue;
    private List<Double> derivative = new ArrayList<>();
    private List<Double> signal = new ArrayList<>();
    private List<Double> squaring = new ArrayList<>();
    private List<Double> movingWindowIntegration = new ArrayList<>();
    private List<Double> Peaki = new ArrayList<>();
    private List<Double> Peakifinal = new ArrayList<>();
    private boolean isRun;

    public ECGSignalHandler(BlockingQueue queue) {
        this.queue = queue;
    }

    public void handle() throws InterruptedException {
        while (isRun) {
            if (!queue.isEmpty()) {
                Double newD = queue.take();
                signal.add(newD);
            }
            setDerivative();
            setSquaring();
            for (int i = 0; i < signal.size(); i++) {
                System.out.println(signal.get(i));
            }
        }
    }

    public void setRun(boolean isRun) {
        this.isRun = isRun;
    }

    private void setDerivative() {
        //цикл, находим сглаженную производную несглаженного сигнала
        for (int i = 2; i < signal.size() - 2; i++) {
            derivative.add(0.1 * (2 * signal.get(i - 2)) - signal.get(i - 1) +
                    signal.get(i + 1) + 2 * signal.get(i + 2) / 0.002);
        }

//        for (int i = 0; i < listFirst.size(); i++)
        //squaring.add(signal.get(signal.size() - 1));
    }

    private void setSquaring() {
        for (int i = 0; i < (derivative.size() - 1); i++) {
            squaring.add(pow(derivative.get(i), 2));
        }
    }

    private void setMovingWindowIntegration() {
        double tQRS = 0.1;
        double Td = 0.002;
        int W = 50;

        for (int i = 1; i < (squaring.size() - 1); i++) {
            movingWindowIntegration.add(1 / W * (squaring.get(i - (W - i))));
            double max = 0;
            if (movingWindowIntegration.get(i) > max) max = movingWindowIntegration.get(i);
            Peaki.add(max);
        }
        Double SPKI = 0.0;
        SPKI = 0.125 * Peaki.get(1) + 0.875 * SPKI;
        Double NPKI = 0.0;
        NPKI = 0.125 * Peaki.get(1) + 0.875 * NPKI;
        Double ThresholdI1 = NPKI + 0.25 * (SPKI - NPKI);
        Double ThresholdI2 = 0.5 * ThresholdI1;
        for (int i = 0; i < Peaki.size(); i++) {
            if (Peaki.get(i) > ThresholdI1) {
                Peakifinal.add(Peaki.get(i));
            } else {

            }
        }
                /*for (int n = 1; n<(squaring.size()-1); n++) {
            for (int i = 1; i<W; i++) {
                Double sum = 0.0;
                sum = 1/W*(squaring.get(n - (W-i)))+sum;
            }
        }*/
    }

    @Override
    public void run() {
    }

}
