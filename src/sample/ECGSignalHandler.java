package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import static java.lang.Math.pow;

public class ECGSignalHandler extends Thread {
    private BlockingQueue<Double> queue;
    private List<Double> Derivative1 = new ArrayList<>();
    private List<Double> Derivative = new ArrayList<>();
    private List<Double> Squaring = new ArrayList<>();
    private List<Double> MovingWindowIntegration = new ArrayList<>();
    private boolean isRun;

    public ECGSignalHandler(BlockingQueue queue) {
        this.queue = queue;
    }

    public void handle() throws InterruptedException {
        while (isRun) {
            Double newD = queue.take();
            Derivative.add(newD);
            //TODO проверять длину листа и удалять
            //TODO счетчик
            for (int i = 0; i < queue.size(); i++) {
                int t = queue.size();
                if (t > 0) queue.remove();
            }
            while (newD != null) {
                //       int t = incrementExact(int i); TODO посчитать количество элементов в массиве производной
            }

            // for (int i=0; i < newD.)

        }
    }
    //TODO вызываем методы обработки

    public void setRun(boolean isRun) {
        this.isRun = isRun;
    }

    private void setDerivative() {
        //цикл, находим сглаженную производную несглаженного сигнала
        for (int i = 2; i < Derivative.size() - 2; i++) {
            Derivative1.add(0.1 * (2 * Derivative.get(i - 2)) - Derivative.get(i - 1) +
                    Derivative.get(i + 1) + 2 * Derivative.get(i + 2) / 0.002);
        }

//        for (int i = 0; i < listFirst.size(); i++)
        //Squaring.add(Derivative.get(Derivative.size() - 1));
    }

    private void setSquaring() {
        for (int i = 0; i < Derivative.size(); i++) {
            Squaring.add(pow(Derivative1.get(i), 2));
        }

    }

    private void setMovingWindowIntegration() {

    }

    @Override
    public void run() {
    }

}
