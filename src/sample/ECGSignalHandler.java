package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import static java.lang.Math.pow;

public class ECGSignalHandler extends Thread {
    private BlockingQueue<Double> queue;
    private List<Double> derivative = new ArrayList<>();
    private List<Double> signal = new ArrayList<>();
    private List<Double> Squaring = new ArrayList<>();
    private List<Double> MovingWindowIntegration = new ArrayList<>();
    private boolean isRun;

    public ECGSignalHandler(BlockingQueue queue) {
        this.queue = queue;
    }

    public void handle() throws InterruptedException {
        while(true) {
            if (!queue.isEmpty()) {
                Double newD = queue.take();
                signal.add(newD);
            }
            setDerivative();
            setSquaring();
            for (int i = 0; i < signal.size(); ++i) {
                System.out.println(signal.get(i));
            }
        }
          /*  while (newD != null) {
                //       int t = incrementExact(int i); TODO посчитать количество элементов в массиве производной
            }

            // for (int i=0; i < newD.)*/

    }
    //TODO вызываем методы обработки

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
        //Squaring.add(signal.get(signal.size() - 1));
    }

    private void setSquaring() {
        for (int i = 0; i < signal.size(); i++) {
            Squaring.add(pow(derivative.get(i), 2));
        }

    }

    private void setMovingWindowIntegration() {

    }

    @Override
    public void run() {
    }

}
