package program;

import backpropagation.Backpropagation;

import javax.swing.*;

public class Main extends JFrame{

    public Main() {
        setTitle("Backpropagation");
        setResizable(true);
        this.setExtendedState( this.getExtendedState()| JFrame.MAXIMIZED_BOTH );


        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setSize(800, 465);
        setVisible(true);

        Backpropagation backpropagation = new Backpropagation();
        backpropagation.training();
    }

    public static void main(String args[]) {
        new Main();
    }
}
