package program;

import backpropagation.Backpropagation;
import sun.rmi.runtime.Log;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends JFrame {
    public static ArrayList<int[]> letters = new ArrayList<int[]>();

    public Main() {
        int[] letterA1 = new int[]{-1, -1, 1, 1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, 1, -1, 1, -1, -1, -1, -1, 1, -1, 1, -1, -1, -1, 1, 1, 1, 1, 1, -1, -1, 1, -1, -1, -1, 1, -1, -1, 1, -1, -1, -1, 1, -1, 1, 1, 1, -1, 1, 1, 1, 1, -1, -1, 0, 0, 0, 0};


//        setTitle("Backpropagation");
//        setResizable(true);
//        this.setExtendedState( this.getExtendedState()| JFrame.MAXIMIZED_BOTH );


//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setSize(800, 465);
//        setVisible(true);

//        letters.add(letterA1);

        letterReader();
        Backpropagation backpropagation = new Backpropagation(letters);
        backpropagation.training();
        System.out.println(Arrays.toString(backpropagation.recognition(letterA1)));
    }

    //metodo para ler os arquivos da pasta /training set
    public static void letterReader() {

        File files[];
        File folder = new File("training_set");
        files = folder.listFiles();

        for (int i = 0; i < files.length; i++) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(files[i].getPath()));
                String row = new String();
                while (br.ready()) { //lê o arquivo enquanto houver linhas
                    row += br.readLine().trim();
                }
                br.close();

                int[] letter = new int[70];
                for (int j = 0; j < 63; j++) {
                    Character currentChar = row.charAt(j);
                    if (currentChar.equals('.')) {
                        letter[j] = -1;
                    } else if (currentChar.equals('#')) {
                        letter[j] = 1;
                    } else {
                        letter[j] = 0;
                    }
                }

                for (int j = 63; j < 70; j++) {
                    Character currentChar = row.charAt(j);
                    if (currentChar.equals('.'))
                        letter[j] = 0;
                    else
                        letter[j] = 1;
                }
                letters.add(letter);
            } catch (IOException ioe) {
            }
        }
    }

    public static void main(String args[]) {
        new Main();
    }
}
