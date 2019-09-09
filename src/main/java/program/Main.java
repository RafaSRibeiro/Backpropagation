package program;

import javax.swing.*;

public class Main extends JFrame{

    public Main() {
        setTitle("Lexic Analizer");
        setResizable(true);
        this.setExtendedState( this.getExtendedState()| JFrame.MAXIMIZED_BOTH );


        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setSize(800, 465);
        setVisible(true);
    }

    public static void main(String args[]) {
        new Main();
    }
}
