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
    private List<Double> peaki = new ArrayList<>();
    private List<Double> peakifinal = new ArrayList<>();
    private boolean firstPeakFound = false;
    private final int integrationWindowWidth = 50;
    private int lastPeakIndex = 0;

    public ECGSignalHandler(BlockingQueue queue) {
        this.queue = queue;
        for (int i = 0; i < 5; i++)
            signal.add(0.0);
        for (int i = 0; i < integrationWindowWidth; i++)
            squaring.add(0.0);
        movingWindowIntegration.add(0.0);
    }

    public void run() {
        while (true) {
            if (!queue.isEmpty()) {
                Double newD = null;
                try {
                    newD = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                signal.add(newD);
            }
            updateDerivative();
            updateSquaring();
            updateMovingWindowIntegration();
            if (!firstPeakFound) {
                findFirstPeak();
            } else {
                //todo приватный метод определения порогов "открытия" окна интегрирования
                if (thresholdIsOpen()) {
                    findRRPeaks();
                }
            }
        }
    }

    private boolean thresholdIsOpen() {
        return false;  //если текущее значение >= порога, то return true
    }

    private void integrationWindowLeftThreshold() {
        //todo 10% от последнего пика
    }

    private void updateDerivative() {
        int n = signal.size() - 1;
        // 1/8 = 0
        //1.0/8.0 = 0.125

        derivative.add(1.0 / 8.0 * (2 * (signal.get(n)) + signal.get(n - 1) - signal.get(n - 3) - 2 * signal.get(n - 4)) / 0.002);
        //System.out.println(derivative.get(derivative.size()-1));
    }

    private void updateSquaring() {
        squaring.add(pow(derivative.get(derivative.size() - 1), 2));
        //System.out.println(squaring.get(squaring.size()-1));
    }

    private void updateMovingWindowIntegration() {
        int n = squaring.size() - 1;
        Double sum = 0.0;
        for (int i = n; i >= n - (integrationWindowWidth - 1); i--) {
            sum = sum + squaring.get(i); //sum += squaring.get(i);
        }
        sum /= integrationWindowWidth;
        movingWindowIntegration.add(sum);
        //System.out.println(sum);

    }

    private void findFirstPeak() {
        if (movingWindowIntegration.get(movingWindowIntegration.size() - 1) < (movingWindowIntegration.get(movingWindowIntegration.size() - 2))) {
            lastPeakIndex = movingWindowIntegration.size() - 2;
            firstPeakFound = true;
        }
        //вычисление производных
        //сравниваешь производную с нулем
        //если производнаяменьше нуля, выходим из цикла
        //и добавляем соответствующий (максимум функции интегрирования минус ширина окна) элемент в массив пиков
        //}

    }

    private void findRRPeaks() {
        //todo искать максимум функции и обновлять lastpeakindex
        integrationWindowLeftThreshold();//если нашли пик, 10% от нового пика
    }

    private void setMovingWindowIntegration1() {
        double tQRS = 0.1;
        double Td = 0.002;
        int W = 50;

        for (int i = 1; i < (squaring.size() - 1); i++) {
            movingWindowIntegration.add(1 / W * (squaring.get(i - (W - i))));
            double max = 0;
            if (movingWindowIntegration.get(i) > max) max = movingWindowIntegration.get(i);
            peaki.add(max);
        }
        Double SPKI = 0.0;
        SPKI = 0.125 * peaki.get(1) + 0.875 * SPKI;
        Double NPKI = 0.0;
        NPKI = 0.125 * peaki.get(1) + 0.875 * NPKI;
        Double ThresholdI1 = NPKI + 0.25 * (SPKI - NPKI);
        Double ThresholdI2 = 0.5 * ThresholdI1;
        for (int i = 0; i < peaki.size(); i++) {
            if (peaki.get(i) > ThresholdI1) {
                peakifinal.add(peaki.get(i));
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

}
