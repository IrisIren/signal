package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ECGSignalHandler extends Thread {
    private BlockingQueue<Double> queue;
    private List<Double> listFirst = new ArrayList<>();//TODO rename variable
    private List<Double> listSecond = new ArrayList<>();//TODO rename variable
    private List<Double> thirdFirst = new ArrayList<>();//TODO rename variable
    private boolean isRun;

    public ECGSignalHandler(BlockingQueue queue) {
        this.queue = queue;
    }

    public void handle() throws InterruptedException {
        while (isRun) {
            Double newD = queue.take();
            listFirst.add(newD);
            //TODO проверять длину листа и удалять
            //TODO счетчик
            //TODO вызываем методы обработки
        }
    }

    public void setRun(boolean isRun) {
        this.isRun = isRun;
    }

    private void firstEval() {
//        for (int i = 0; i < listFirst.size(); i++) {
        listSecond.add(listFirst.get(listFirst.size() - 1));
//        }
    }

    @Override
    public void run() {
    }

}
