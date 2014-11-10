package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    List<Integer> list = new ArrayList<Integer>();
    List<Double> list1;
    List<Integer> list2 = new ArrayList<Integer>();

    //    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//    }
    void dif() {
        try {
            list1 = new TestReader("D:\\ECG.txt").read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            newList.add(list.get(i) - list.get(i + 1));
            newList.remove(0);
        }
    }


    public static void main(String[] args) {
        //   launch(args);
        Main main = new Main();
        main.dif();
    }


    //  List int[]A = new List();

}