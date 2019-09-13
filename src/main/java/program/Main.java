package program;

import backpropagation.Backpropagation;
import sun.rmi.runtime.Log;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends JFrame{

    public Main() {
//        setTitle("Backpropagation");
//        setResizable(true);
//        this.setExtendedState( this.getExtendedState()| JFrame.MAXIMIZED_BOTH );


//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setSize(800, 465);
//        setVisible(true);

        ArrayList<int[]> letters = new ArrayList<int[]>();
        int[] letterA1 = new int[]{-1, -1, 1, 1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, 1, -1, 1, -1, -1, -1, -1, 1, -1, 1, -1, -1, -1, 1, 1, 1, 1, 1, -1, -1, 1, -1, -1, -1, 1, -1, -1, 1, -1, -1, -1, 1, -1, 1, 1, 1, -1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0};
        letters.add(letterA1);

        Backpropagation backpropagation = new Backpropagation(letters);
        backpropagation.training();
        System.out.println(Arrays.toString(backpropagation.recognition(letterA1)));
    }

    public static void main(String args[]) {
        new Main();
    }
}
